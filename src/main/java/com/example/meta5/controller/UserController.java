package com.example.meta5.controller;

import com.example.meta5.dto.LoginRequest;
import com.example.meta5.entity.User;
import com.example.meta5.framework.common.GenericPagingResponse;
import com.example.meta5.framework.security.dto.CustomUserDetails;
import com.example.meta5.framework.security.util.JwtTokenUtil;
import com.example.meta5.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    public UserController(UserService userService, JwtTokenUtil tokenUtil, AuthenticationManager authManager){
        this.userService = userService;
        this.jwtTokenUtil = tokenUtil;
        this.authenticationManager = authManager;
    }

    @PostMapping("/signin")
    public Map<String, String> login(@RequestBody LoginRequest body){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
        try{
            Authentication authentication = authenticationManager.authenticate( authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String jwtToken = jwtTokenUtil.generateToken(userDetails);
            Map<String, String> result = new HashMap<>();
            result.put("token","Bearer " + jwtToken);

            return  result;
        } catch(Exception e){
            return null;
        }

    }

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody LoginRequest body){
        Map result = new HashMap<String, String>();

        try{
            userService.save(body);
            result.put("result", "success");
            return result;
        } catch(Exception e){
            log.error("", e);
            result.put("result", "fail");
            return result;
        }

    }

    @GetMapping
    public ResponseEntity<GenericPagingResponse<User>> getAllUsers(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page
            ,@RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        return ResponseEntity.ok(userService.getAllUsers(page, size));

    }
}
