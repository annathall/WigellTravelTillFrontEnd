package com.thall.wigelltravel.controllers;
import com.thall.wigelltravel.entities.Authorities;
import com.thall.wigelltravel.entities.User;
import com.thall.wigelltravel.services.AuthoritiesService;
import com.thall.wigelltravel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class LoginController {

    private final UserService userService;
    private final AuthoritiesService authoritiesService;

    public LoginController(UserService userService, AuthoritiesService authoritiesService) {
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody User u) {
        System.out.println("Stating authentication process");

        User user = userService.findByUsername(u.getUsername());
        System.out.println("Fetched user: " + user);

        if (user != null && u.getPassword().equals(user.getPassword())) {
            Optional<Authorities> authorities = authoritiesService.findByUser(user);
            System.out.println("Authorities: " + authorities.map(Authorities::getAuthority).orElse("No authorities"));

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("authorities", authorities.map(Authorities::getAuthority).orElse(null)); // Include authorities in the response

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Invalid username or password"));
        }
    }
}
