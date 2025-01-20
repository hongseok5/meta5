package com.example.meta5.dto;

//import org.springframework.data.jpa.repository.Meta;

import com.example.meta5.entity.MetaObject;

public interface Inputable {

    public MetaObject toMetaObject();
}
