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
import org.springframework.security.config.http.SessionCreationPolicy;
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

//    @Override
//    public void configure(final HttpSecurity http) throws Exception {
//        http.cors().and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(securityConstants.getWhitelist()).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .opaqueToken(opaqueToken -> opaqueToken
//                                .introspector(introspector())
//                        )
//                );
//    }
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authz) -> authz
            .anyRequest().permitAll()
        )
        .httpBasic(Customizer.withDefaults());
    return http.build();
}
}
