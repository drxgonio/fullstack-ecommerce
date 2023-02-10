package com.example.ecm.model.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
  private String token_type = "bearer";

  @JsonProperty("access_token")
  private String token;
  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("expires_in")
  private Instant expiresIn;
  private String scope = "read,write";

  public JwtResponse(String token, String refreshToken, Instant expiresIn) {
    this.token= token;
    this.refreshToken = refreshToken;
    this.expiresIn = expiresIn;
  }
}
