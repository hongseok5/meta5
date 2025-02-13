package com.example.meta5.service;

import com.example.meta5.dao.TermRepository;
import com.example.meta5.dao.WordRepository;
import com.example.meta5.entity.DomainInfo;
import com.example.meta5.entity.Term;
import com.example.meta5.entity.Word;
import com.example.meta5.framework.common.GenericPagingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TermService {

    private TermRepository repository;

    public TermService(TermRepository termMapper){

        this.repository = termMapper;
    }

    public GenericPagingResponse<Term> findAll(int page, int size) {
        Page<Term> terms = repository.findAll(PageRequest.of(page, size));
        return new GenericPagingResponse<>(terms);
    }

    public Term newTerm(Term term) {
        Term saved = repository.save(term);
        repository.flush(); // 여기서 DB에 반영되며 예외 발생 시 try-catch 내에서 처리됨
        return saved;
    }

    public Term editTerm(Term term) {
        return repository.save(term);
    }

    public boolean deleteTerm(Term term) {
        try{
            repository.deleteById(term.getTermEngName());
            return true;
        }catch (Exception e){
            log.error("", e);
            return false;
        }
    }
}
