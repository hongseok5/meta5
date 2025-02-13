package com.example.meta5.dao;

import com.example.meta5.entity.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @Test
    @Transactional
    public void testFindWordByEngName() {
        // 실제 DB에서 조회할 단어의 영문 이름
        String wordEngName = "ACCNT";

        // 실제 DB에서 데이터를 조회
        Word word = wordRepository.findById(wordEngName).orElse(null);

        // 테스트: 조회된 결과가 null이 아니어야 함
        assertNotNull(word);
        System.out.println("Word found: " + word.getWordName());
    }
}
