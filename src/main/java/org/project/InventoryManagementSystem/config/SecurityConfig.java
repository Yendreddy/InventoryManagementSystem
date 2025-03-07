package org.project.InventoryManagementSystem.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.client.oidc.authentication.OidcIdTokenDecoderFactory;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.project.InventoryManagementSystem.repository.UserRepository;
import org.project.InventoryManagementSystem.entity.User1;
import org.project.InventoryManagementSystem.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.Map;

import static org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration.jwtDecoder;

@Configuration
public class SecurityConfig {

    @Autowired
    UserRepository userRepository;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration googleRegistration = ClientRegistration.withRegistrationId("google")
                .clientId("964397597901-390c598hckpt9qnmf21mdee3p01r71ge.apps.googleusercontent.com")
                .clientSecret("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")
                .scope("openid", "profile", "email")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName("sub")
                .clientName("Google")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();

        return new InMemoryClientRegistrationRepository(googleRegistration);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/categories/save", "/categories/update/{category_id}", "/categories/delete/{category_id}", "/products/save", "/products/update/{product_id}", "/products/delete/{product_id}").hasRole("ADMIN")
                .requestMatchers("/customers/save", "/customers/update/{customer_id}", "/customers/delete/{customer_id}", "/orders/save", "/orders/update/{order_id}", "/orders/delete/{order_id}").hasRole("USER")
                .requestMatchers("/customers/get", "/customers/get/{customer_id}", "/categories/get", "/categories/get/{category_id}", "/products/get", "/products/get/{product_id}", "/orders/get", "/orders/get/{order_id}").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        )
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(oAuth2UserService())
                        )
                );
        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return request -> {
            OAuth2User oAuth2User = delegate.loadUser(request);
            Map<String, Object> attributes = oAuth2User.getAttributes();
            String email = (String) attributes.get("email");

            User1 user = userRepository.findByUsername(email).orElseGet(() -> {
                User1 newUser = new User1();
                newUser.setUsername(email);
                newUser.setRole(Role.USER);
                return userRepository.save(newUser);
            });

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())),
                    attributes,
                    "name"
            );
        };
    }
}