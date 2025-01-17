package com.example.meta5.dao;

import com.example.meta5.entity.DomainInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<DomainInfo, String> {

    Page<DomainInfo> findAll(Pageable pageble);
}
