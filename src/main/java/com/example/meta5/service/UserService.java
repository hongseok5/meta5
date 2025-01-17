package com.example.meta5.service;

import com.example.meta5.dao.UserRepository;
import com.example.meta5.dto.LoginRequest;
import com.example.meta5.entity.User;
import com.example.meta5.framework.common.GenericPagingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(LoginRequest body){
        User user = new User();
        user.setUsername(body.getUsername());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user.setRole("ADMIN");
        User result = userRepository.save(user);
//                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)).compact();
        return result;
    };

    public GenericPagingResponse<User> getAllUsers(int page, int size) {
        Page<User> userList = userRepository.findAll(PageRequest.of(page, size));
        return new GenericPagingResponse<>(userList);
    }


}
