package com.example.ecm.service;


import com.example.ecm.model.entity.User;
import com.example.ecm.model.request.user.PasswordResetRequest;
import com.example.ecm.model.request.user.RegisterUserRequest;
import com.example.ecm.model.request.user.UpdateUserAddressRequest;
import com.example.ecm.model.request.user.UpdateUserRequest;
import com.example.ecm.model.response.user.UserResponse;

public interface UserService {
    User register(RegisterUserRequest registerUserRequest);

    UserResponse fetchUser();

    User getUser();

    User saveUser(User user);

    User findByEmail(String email);

    boolean userExists(String email);

    UserResponse updateUser(UpdateUserRequest updateUserRequest);

    UserResponse updateUserAddress(UpdateUserAddressRequest updateUserAddressRequest);

    void resetPassword(PasswordResetRequest passwordResetRequest);

    Boolean getVerificationStatus();
}
