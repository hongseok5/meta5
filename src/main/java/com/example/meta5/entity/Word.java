package com.example.meta5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Word {

    @Id
    private String wordEngName;

    private String wordName;

    private String wordEngFullName;

    private String wordType;

        private String domainName;


}
