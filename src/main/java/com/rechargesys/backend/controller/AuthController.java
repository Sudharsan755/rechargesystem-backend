package com.rechargesys.backend.controller;

import com.rechargesys.backend.dto.AuthResponse;
import com.rechargesys.backend.dto.LoginRequest;
import com.rechargesys.backend.entity.User;
import com.rechargesys.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getPhone() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "Phone and password are required", null));
        }

        // Admin check
        if ("0000000000".equals(loginRequest.getPhone()) && "0000".equals(loginRequest.getPassword())) {
            User adminUser = new User("admin-id", "Admin", "0000000000", "0000");
            return ResponseEntity.ok(new AuthResponse(true, "Admin login successful", adminUser));
        }

        Optional<User> userOpt = userService.authenticate(loginRequest.getPhone(), loginRequest.getPassword());
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(new AuthResponse(true, "Login successful", userOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(false, "Invalid credentials or user not found", null));
        }
    }

    // POST /api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody User user) {
        try {
            User created = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AuthResponse(true, "Signup successful", created));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, ex.getMessage(), null));
        }
    }
}

