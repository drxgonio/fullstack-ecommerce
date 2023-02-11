package com.example.ecm.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.example.ecm.dao.PasswordForgotTokenRepository;
import com.example.ecm.dao.RefreshTokenRepository;
import com.example.ecm.dao.UserRepository;
import com.example.ecm.error.exception.InvalidArgumentException;
import com.example.ecm.error.exception.ResourceNotFoundException;
import com.example.ecm.error.exception.TokenRefreshException;
import com.example.ecm.model.entity.PasswordForgotToken;
import com.example.ecm.model.entity.RefreshToken;
import com.example.ecm.model.entity.User;
import com.example.ecm.model.event.OnPasswordForgotRequestEvent;
import com.example.ecm.model.request.user.PasswordForgotValidateRequest;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RefreshTokenService {

  private static final int EXPIRY_DATE = 60 * 24;
  @Value("${jwt.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordForgotTokenRepository passwordForgotTokenRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private ApplicationEventPublisher eventPublisher;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public void createPasswordResetToken(String email) {
    User user = userService.findByEmail(email);
    if (Objects.isNull(user)) {
      return;
    }

    PasswordForgotToken passwordForgotToken = passwordForgotTokenRepository.findByUser(user)
        .orElse(null);
    if (Objects.isNull(passwordForgotToken)) {
      passwordForgotToken = new PasswordForgotToken();
      passwordForgotToken.setUser(user);
    }

    String token = UUID.randomUUID().toString();
    passwordForgotToken.setToken(token);
    passwordForgotToken.setExpiryDate(calculateExpiryDate());
    passwordForgotTokenRepository.save(passwordForgotToken);

    eventPublisher.publishEvent(new OnPasswordForgotRequestEvent(user, token));
  }

  public RefreshToken createRefreshToken(Long userId) {
    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setUser(userRepository.findById(userId).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public void validateForgotPasswordConfirm(String token) {
    PasswordForgotToken passwordForgotToken = passwordForgotTokenRepository.findByToken(token)
        .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

    checkTokenExpire(passwordForgotToken.getExpiryDate());
  }

  public void validateForgotPassword(PasswordForgotValidateRequest passwordForgotValidateRequest) {
    PasswordForgotToken passwordForgotToken = passwordForgotTokenRepository.findByToken(passwordForgotValidateRequest.getToken())
        .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

    User user = passwordForgotToken.getUser();

    if (Objects.isNull(user)) {
      throw new ResourceNotFoundException("User not found");
    }

    checkTokenExpire(passwordForgotToken.getExpiryDate());

    if (passwordEncoder.matches(passwordForgotValidateRequest.getNewPassword(), user.getPassword())) {
      return;
    }

    user.setPassword(passwordEncoder.encode(passwordForgotValidateRequest.getNewPassword()));
    userService.saveUser(user);
    passwordForgotTokenRepository.delete(passwordForgotToken);
  }

  private Date calculateExpiryDate() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(Calendar.MINUTE, RefreshTokenService.EXPIRY_DATE);
    return new Date(cal.getTime().getTime());
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  public void validateEmail(String token) {
    RefreshToken verificationToken = refreshTokenRepository.findByToken(token)
        .orElseThrow(() -> new ResourceNotFoundException("Null verification token"));


    User user = verificationToken.getUser();

    if (Objects.isNull(user)) {
      throw new ResourceNotFoundException("User not found");
    }

    checkTokenExpire(verificationToken);

    user.setEmailVerified(1);
    refreshTokenRepository.delete(verificationToken);
    userService.saveUser(user);
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }

  private void checkTokenExpire(RefreshToken refreshToken) {
    if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(refreshToken);
      throw new TokenRefreshException(refreshToken.getToken(), "Refresh token was expired. Please make a new signin request");
    }
  }

  private void checkTokenExpire(Date date) {
    if ((date.getTime() - Calendar.getInstance().getTime().getTime()) <= 0) {
      throw new InvalidArgumentException("Token is expired");
    }

  }
}
