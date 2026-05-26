package com.example.authdemo;

import com.example.authdemo.entity.User;
import com.example.authdemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByEmail("shreya@gmail.com").isEmpty()) {

            User user = new User();

            user.setName("Shreya");
            user.setEmail("shreya@gmail.com");
            user.setPassword("12345");

            userRepository.save(user);

            System.out.println("Test user inserted");
        }
    }
}