package com.example.meta5.dao;

import com.example.meta5.entity.Term;
import com.example.meta5.entity.TermWordInfo;
import com.example.meta5.entity.Word;
import com.example.meta5.entity.pk.TermWordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TermWordInfoRepository extends JpaRepository<TermWordInfo, TermWordId> {

    // 특정 Term에 속한 Word 목록 조회
    List<TermWordInfo> findByTerm(Term term);

    // 특정 Word에 속한 Term 목록 조회
    List<TermWordInfo> findByWord(Word word);

    // 특정 Term과 Word로 TermWordInfo 조회
    Optional<TermWordInfo> findById(TermWordId id);

    // 특정 Term에 속한 Word 중 특정 순번을 가진 엔티티 조회
    Optional<TermWordInfo> findByTermAndWordSeq(Term term, int wordSeq);
}
