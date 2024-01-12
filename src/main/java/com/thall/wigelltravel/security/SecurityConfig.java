package com.thall.wigelltravel.security;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails pelle = User.builder()
                .username("pelle")
                .password(passwordEncoder().encode("pelle"))
                .roles("USER")
                .build();

        UserDetails maja = User.builder()
                .username("maja")
                .password(passwordEncoder().encode("maja"))
                .roles("ADMIN", "USER")
                .build();


        return new InMemoryUserDetailsManager(pelle, maja );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/login", HttpMethod.POST.toString())).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/login", HttpMethod.OPTIONS.toString())).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/customers", HttpMethod.GET.toString())).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/customers", HttpMethod.POST.toString())).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/customers/{id}", HttpMethod.PUT.toString())).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/customers/{id}", HttpMethod.DELETE.toString())).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/destinations", HttpMethod.GET.toString())).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/destinations", HttpMethod.POST.toString())).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/destinations/{id}", HttpMethod.PUT.toString())).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/destinations/{id}", HttpMethod.DELETE.toString())).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/trips", HttpMethod.GET.toString())).hasAnyRole("USER", "ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/bookings", HttpMethod.GET.toString())).hasAnyRole("ADMIN", "USER")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/bookings/{id}", HttpMethod.GET.toString())).hasRole("USER")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/bookings", HttpMethod.POST.toString())).hasRole("USER")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/bookings/{id}", HttpMethod.PUT.toString())).hasRole("USER")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/findByDestination/{id}", HttpMethod.GET.toString())).hasAnyRole("USER,", "ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/**", HttpMethod.OPTIONS.toString())).permitAll()
                                .anyRequest().authenticated()
                )

                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();

    }
}