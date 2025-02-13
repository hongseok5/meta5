package com.example.meta5.controller;

import com.example.meta5.entity.DomainInfo;
import com.example.meta5.entity.Term;
import com.example.meta5.entity.Word;
import com.example.meta5.framework.common.GenericPagingResponse;
import com.example.meta5.service.TermService;
import com.example.meta5.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("term")
public class TermController {


    private TermService termService;

    @Autowired
    public TermController(TermService termService){
        this.termService = termService;

    }

    @GetMapping
    public GenericPagingResponse<Term> getTerms( @RequestParam(name = "page", required = false, defaultValue = "0") int page
                                                ,@RequestParam(name = "size", required = false, defaultValue = "10") int size){
        GenericPagingResponse<Term> terms = termService.findAll(page,size);

        return terms;
    }

    @PostMapping
    public Term newTerm(@RequestBody Term term){
        try{
            return termService.newTerm(term);
        }catch(Exception e){
            log.error("", e);
            return null;
        }
    }

    @PutMapping
    public Term deleteTerm (@RequestBody Term term){

        try{
            return termService.editTerm(term);
        } catch (Exception e){
            return null;
        }
    }

    @DeleteMapping
    public boolean deleteDomain(@RequestBody Term term){

        return termService.deleteTerm(term);
    }
}
