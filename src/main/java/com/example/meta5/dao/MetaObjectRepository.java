package com.example.meta5.dao;

import com.example.meta5.entity.DomainInfo;
import com.example.meta5.entity.MetaObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MetaObjectRepository extends JpaRepository<MetaObject, String> {


}
