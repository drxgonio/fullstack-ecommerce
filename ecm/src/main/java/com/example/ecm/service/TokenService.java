package com.example.ecm.service;

import com.example.ecm.model.entity.User;
import com.example.ecm.model.request.user.PasswordForgotValidateRequest;


public interface TokenService {

    void createEmailConfirmToken(User user);

    void createPasswordResetToken(String email);

    void validateEmail(String token);

    void validateForgotPasswordConfirm(String token);

    void validateForgotPassword(PasswordForgotValidateRequest passwordForgotValidateRequest);
}
