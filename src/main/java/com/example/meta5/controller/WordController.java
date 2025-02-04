package com.example.meta5.controller;

import com.example.meta5.dto.LoginRequest;
import com.example.meta5.dto.WordInput;
import com.example.meta5.entity.User;
import com.example.meta5.entity.Word;
import com.example.meta5.framework.common.GenericPagingResponse;
import com.example.meta5.framework.security.dto.CustomUserDetails;
import com.example.meta5.service.UserService;
import com.example.meta5.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class WordController {


    private WordService wordService;

    @Autowired
    public WordController(WordService wordService){
        this.wordService = wordService;

    }
    @SuppressWarnings("unused")
    @MutationMapping
    public Word createWord(@Argument WordInput input) {
        // 변수명도 맞춰줘야 한다.
        return wordService.save(input);
    }

    @QueryMapping(name = "allWords")
    @SuppressWarnings("unused")
    public List<Word> allWords(){

        try{
e
            return wordService.findAll();
        } catch(Exception e){
            log.error("", e);
            return null;
        }

    }

}
