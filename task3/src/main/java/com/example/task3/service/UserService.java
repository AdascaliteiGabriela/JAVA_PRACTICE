package com.example.task3.service;


import com.example.task3.model.Role;
import com.example.task3.model.Users;
import com.example.task3.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users register(String username, String password) {


        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }


        Users user = new Users();
        user.setUsername(username);


        user.setPassword(passwordEncoder.encode(password));

        user.setRole(Role.ROLE_USER);

        return userRepository.save(user);
    }

    public Users createUserWithRole(String username, String password, Role role) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }


}
