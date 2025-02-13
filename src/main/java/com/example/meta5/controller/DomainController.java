package com.example.meta5.controller;

import com.example.meta5.entity.DomainInfo;
import com.example.meta5.framework.common.GenericPagingResponse;
import com.example.meta5.framework.security.util.JwtTokenUtil;
import com.example.meta5.service.DomainService;
import com.example.meta5.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/domain")
public class DomainController {

    private DomainService domainService;



    @Autowired
    public DomainController(DomainService domainService){

        this.domainService = domainService;

    }

    @GetMapping("/list")
    public GenericPagingResponse<DomainInfo> getDomainList(
             @RequestParam(name = "page", required = false, defaultValue = "0") int page
            ,@RequestParam(name = "size", required = false, defaultValue = "10") int size){
        GenericPagingResponse<DomainInfo> domainlist = domainService.findAll(page, size);
        return domainlist;
    }

    @PostMapping
    public DomainInfo newDomain(@RequestBody DomainInfo domainInfo){

        try{
            return domainService.newDomain(domainInfo);
        } catch (Exception e){
            return new DomainInfo();
        }
    }

    @PutMapping
    public DomainInfo editDomain(@RequestBody DomainInfo domainInfo){

        try{
            return domainService.editDomain(domainInfo);
        } catch (Exception e){
            return new DomainInfo();
        }
    }

    @DeleteMapping
    public boolean deleteDomain(@RequestBody DomainInfo domainInfo){

        return domainService.deleteDomain(domainInfo);
    }
}
