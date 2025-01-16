//package com.tutorial.Tutorial.config;
//
//import com.tutorial.Tutorial.entity.RoleType;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final PasswordEncoderConfig passwordEncoder;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(request ->
//                request.requestMatchers("/api/clients/register", "/api/clients/login").permitAll()
//                        .requestMatchers("/api/notifications/send", "/api/tutorials/**", "/category/**").authenticated()
//                        .requestMatchers("/api/clients/emails").hasAuthority("ADMIN")
//                        .anyRequest().authenticated()
//        ).formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("user")
//                .password(passwordEncoder.passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        var admin = User.withUsername("admin")
//                .password(passwordEncoder.passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        var author = User.withUsername("author")
//                .password(passwordEncoder.passwordEncoder().encode("author"))
//                .roles("AUTHOR")
//                .build();
//
//        userDetailsService.createUser(user);
//        userDetailsService.createUser(admin);
//        userDetailsService.createUser(author);
//
//        return userDetailsService;
//
//    }
//
//
//}
