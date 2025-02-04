package com.example.meta5.dao;

import com.example.meta5.entity.DomainInfo;
import com.example.meta5.entity.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableInfo, String> {
}
