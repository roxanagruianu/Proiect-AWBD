package com.proiect.awbd.SecurityC;

import com.proiect.awbd.Services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/register", "/css/**", "/js/**", "/register-doctor").permitAll()
                        .requestMatchers("/admin/**", "/utilizatori/**", "/roluri/**").hasRole("ADMIN")
                        .requestMatchers("/clinici/**").hasAnyRole("ADMIN", "DOCTOR", "PACIENT")
                        .requestMatchers("/recenzii/**").hasAnyRole("PACIENT", "DOCTOR", "ADMIN")
                        .requestMatchers("/programari/**").hasAnyRole("PACIENT", "DOCTOR", "ADMIN")
                        .requestMatchers("/diagnostice/**", "/retete/**").hasAnyRole("PACIENT", "DOCTOR", "ADMIN")
                        .requestMatchers("/doctor/**",   "/pacienti/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers( "/recenzii/doctor/**").hasAnyRole("PACIENT", "ADMIN")
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
