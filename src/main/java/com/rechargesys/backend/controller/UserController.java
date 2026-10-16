package com.rechargesys.backend.controller;

import com.rechargesys.backend.entity.User;
import com.rechargesys.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserWithPathVariable(
            @PathVariable String id,
            @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // PUT /api/users (with id in request body, Next.js backward compatibility)
    @PutMapping
    public ResponseEntity<?> updateUserWithBodyId(@RequestBody User user) {
        if (user.getId() == null || user.getId().isBlank()) {
            return ResponseEntity.badRequest().body("User id is required in request body");
        }
        return ResponseEntity.ok(userService.updateUser(user.getId(), user));
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserWithPathVariable(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    // DELETE /api/users?id=...
    @DeleteMapping
    public ResponseEntity<?> deleteUserWithQueryParam(@RequestParam(required = false) String id) {
        if (id == null || id.isBlank()) {
            return ResponseEntity.badRequest().body("Query parameter 'id' is required");
        }
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}

