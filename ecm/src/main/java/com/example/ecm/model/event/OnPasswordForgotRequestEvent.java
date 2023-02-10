package com.example.ecm.model.event;


import com.example.ecm.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnPasswordForgotRequestEvent extends ApplicationEvent {

    private User user;
    private String token;

    public OnPasswordForgotRequestEvent(User user, String token) {
        super(user);
        this.user = user;
        this.token = token;
    }
}
