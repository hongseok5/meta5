package com.example.meta5.service;

import com.example.meta5.dao.TermMapper;
import com.example.meta5.dao.WordRepository;
import com.example.meta5.entity.Word;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TermService {

    private TermMapper repository;

    public TermService(TermMapper termMapper){

        this.repository = termMapper;
    }

    public List<Map<String, String>> findAll() {

        return repository.getAllTerms();
    }

    public Word save(Word  body) {
        return null;
    }
}
