package com.example.meta5.dao;

import com.example.meta5.entity.Term;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TermMapper {

    @Select("SELECT * FROM TERM_INFO")
    List<Map<String, String>> getAllTerms();
}
