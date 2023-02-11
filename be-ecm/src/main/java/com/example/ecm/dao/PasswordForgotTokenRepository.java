package com.example.ecm.dao;

import com.example.ecm.model.entity.PasswordForgotToken;
import com.example.ecm.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordForgotTokenRepository extends CrudRepository<PasswordForgotToken, Long> {
    Optional<PasswordForgotToken> findByToken(String token);

    Optional<PasswordForgotToken> findByUser(User user);
}
