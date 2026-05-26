package com.example.authdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public Map<String, Object> getUser() {

        return Map.of(
                "id", 1,
                "name", "Shreya",
                "email", "shreya@gmail.com"
        );
    }
}