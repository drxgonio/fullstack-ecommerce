package com.example.ecm.model.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ValidateEmailRequest {
    @NotBlank
    String token;
}
