package com.example.meta5.controller;

import com.example.meta5.dao.MetaObjectRepository;
import com.example.meta5.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class TestController {

    @Autowired
    MetaObjectRepository metaObjectRepository;




    @GetMapping("/test")
    public Map<String, String> getTestMsg(){
        Map<String, String> response = new HashMap<>();
        response.put("msg", "hello");
        return response;
    }

    @PostMapping("/metaobject")
    public Map<String, String> insertMeta(@RequestBody User user){
        Map result = new HashMap<String, String>();
        try{
            metaObjectRepository.save(user.toMetaObject());
            result.put("result", "success");
        }catch(Exception e){
            log.error("", e);
            result.put("result", "fail");
        }

        return result;
    }
}
