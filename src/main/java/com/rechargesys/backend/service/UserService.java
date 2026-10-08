package com.rechargesys.backend.service;

import com.rechargesys.backend.entity.User;
import com.rechargesys.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public User createUser(User user) {
        if (user.getPhone() != null && userRepository.existsByPhone(user.getPhone())) {
            throw new IllegalArgumentException("Phone number already registered: " + user.getPhone());
        }
        return userRepository.save(user);
    }

    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(existing -> {
            if (updatedUser.getName() != null) {
                existing.setName(updatedUser.getName());
            }
            if (updatedUser.getPhone() != null) {
                existing.setPhone(updatedUser.getPhone());
            }
            if (updatedUser.getPassword() != null) {
                existing.setPassword(updatedUser.getPassword());
            }
            return userRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    public User deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        userRepository.delete(user);
        return user;
    }

    public Optional<User> authenticate(String phone, String password) {
        return userRepository.findByPhoneAndPassword(phone, password);
    }
}

