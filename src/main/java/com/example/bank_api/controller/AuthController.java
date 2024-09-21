package com.example.bank_api.controller;

import com.example.bank_api.model.User;
import com.example.bank_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, String>> loginWithQueryParams(@RequestParam String appUser, @RequestParam String appPassword) {
        Optional<User> user = userService.login(appUser, appPassword);

        if (user.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", user.get().getId().toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
