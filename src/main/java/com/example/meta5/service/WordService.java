package com.example.meta5.service;

import com.example.meta5.dto.LoginRequest;
import com.example.meta5.dto.WordInput;
import com.example.meta5.entity.Word;
import com.example.meta5.dao.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private WordRepository repository;

    public WordService(WordRepository repository){

        this.repository = repository;
    }

    public List<Word> findAll() {
        return repository.findAll();
    }

    public Word save(WordInput body) {
        return repository.save(body.toWord());
    }
}
