package com.example.meta5.dao;

import com.example.meta5.dto.WordInput;
import com.example.meta5.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, String> {

    //public Word save(WordInput word);

}
