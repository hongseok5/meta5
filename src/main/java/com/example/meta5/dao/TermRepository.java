package com.example.meta5.dao;

import com.example.meta5.entity.DomainInfo;
import com.example.meta5.entity.Term;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository  extends JpaRepository<Term, String> {


    Page<Term> findAll(Pageable pageble);
}
