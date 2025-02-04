package com.example.meta5.controller;

import com.example.meta5.entity.Word;
import com.example.meta5.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("term")
public class TermController {


    private WordService wordService;

    @Autowired
    public TermController(WordService wordService){
        this.wordService = wordService;

    }


}
