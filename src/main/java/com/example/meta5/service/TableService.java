package com.example.meta5.service;

import com.example.meta5.dao.TableRepository;
import com.example.meta5.entity.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository repository;

    public List<TableInfo> findAll(){

        return repository.findAll();
    }
}
