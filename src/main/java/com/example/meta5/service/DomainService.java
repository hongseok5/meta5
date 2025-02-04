package com.example.meta5.service;

import com.example.meta5.dao.DomainRepository;
import com.example.meta5.entity.DomainInfo;
import com.example.meta5.framework.common.GenericPagingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    private DomainRepository repository;

    @Autowired
    public DomainService(DomainRepository repository) {
        this.repository = repository;
    }

    public GenericPagingResponse<DomainInfo> findAll(int page, int size){
        Page<DomainInfo> domainList = repository.findAll(PageRequest.of(page, size));
        return new GenericPagingResponse<>(domainList);
    }

    public DomainInfo newDomain(DomainInfo info){
        DomainInfo domain = repository.save(info);
        return domain;
    }
}
