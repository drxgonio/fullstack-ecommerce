package com.example.ecm.security;

import com.example.ecm.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @Autowired
  private SecurityConstants securityConstants;

  @Bean
  public OpaqueTokenIntrospector introspector() {
    return new CustomAuthoritiesOpaqueTokenIntrospector(
        restTemplateBuilder,
        securityConstants.getClientId(),
        securityConstants.getClientPassword(),
        securityConstants.getConnectionTimeout(),
        securityConstants.getReadTimeout(),
        securityConstants.getAuthUrl(),
        securityConstants.getAuthUsername()
    );
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf().disable()
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
            .anyRequest().permitAll());

        // register OAuth2 resource server
       // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)

        // register OAuth2 client
       // .oauth2Client(Customizer.withDefaults());

    return http.build();
  }
}
