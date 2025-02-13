package com.example.meta5.service;

import com.example.meta5.dto.LoginRequest;
import com.example.meta5.dto.WordInput;
import com.example.meta5.entity.Word;
import com.example.meta5.dao.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    public Word updateWord(WordInput body) {

        return repository.save(body.toWord());
    }

    public boolean deleteWord(String id) {
        try{
            repository.deleteById(id);
            return true;
        } catch(Exception e){
            log.error("", e);
            return false;
        }

    }

}
