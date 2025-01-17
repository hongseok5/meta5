package com.example.meta5.framework.security.service;

import com.example.meta5.dao.UserRepository;
import com.example.meta5.dto.UserDto;
import com.example.meta5.entity.User;
import com.example.meta5.framework.security.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // UserDetails 객체로 변환하여 반환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // 암호화된 비밀번호 사용해야 함
                .roles(user.getRole()) // 역할 지정
                .build();
    }
    */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 데이터베이스에서 이메일을 기반으로 사용자 정보를 조회
        User user = userRepository.findByUsername(email);
        if(user == null){
            // 사용자가 없을 경우 예외처리
            throw new UsernameNotFoundException(email + " 이메일로 가입된 사용자가 없습니다");
        }

        // 사용자의 권한 목록을 생성
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));

        // CustomUserDetails 객체를 생성하여 반환
        CustomUserDetails customUserDetails =
                new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities);

        return customUserDetails;
    }
}

