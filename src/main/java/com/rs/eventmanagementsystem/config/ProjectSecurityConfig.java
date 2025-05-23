package com.rs.eventmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/displayEvents").authenticated()
                .requestMatchers("/displayProfile").authenticated()
                .requestMatchers("/updateEvent").authenticated()
                .requestMatchers("/joinEvent").authenticated()
                .requestMatchers("/leaveEvent").authenticated()
                .requestMatchers("/createNotification").authenticated()
                .requestMatchers("/getNotifications").authenticated()
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/admin/**").hasRole("Admin")
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/public/**").permitAll())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true).failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utilisation de NoOpPasswordEncoder pour désactiver le hachage
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }
}
