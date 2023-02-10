package com.example.ecm.api;

import java.util.List;
import java.util.stream.Collectors;


import com.example.ecm.dao.UserRepository;
import com.example.ecm.model.entity.User;
import com.example.ecm.model.entity.RefreshToken;
import com.example.ecm.model.request.user.JwtResponse;
import com.example.ecm.model.request.user.LoginUser;
import com.example.ecm.model.request.user.RegisterUserRequest;
import com.example.ecm.model.response.user.UserResponse;
import com.example.ecm.security.JwtUtils;
import com.example.ecm.security.UserDetailsImpl;
import com.example.ecm.service.RefreshTokenService;
import com.example.ecm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @Autowired
  RefreshTokenService refreshTokenService;


  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping(
      path = "/signin",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<?> authenticateUser(@RequestBody MultiValueMap paramMap ) {

    LoginUser loginUser= new LoginUser();
    loginUser.setUsername("a@gmail.com");
    loginUser.setPassword("123456");
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), refreshToken.getExpiryDate()));
  }

  @PostMapping(value = "/account/registration")
  public ResponseEntity<UserResponse> createUser(@RequestBody @Validated RegisterUserRequest registerUserRequest) {


    User a = userService.findByEmail(registerUserRequest.getEmail());
    if(a == null){
      User user = new User();
      user.setEmail(registerUserRequest.getEmail());
      user.setPassword(encoder.encode(registerUserRequest.getPassword()));
      user.setEmailVerified(0);
      User sa = userService.saveUser(user);
      refreshTokenService.createRefreshToken(sa.getId());
    }
    return new ResponseEntity<>(new UserResponse(), HttpStatus.OK);
  }

}