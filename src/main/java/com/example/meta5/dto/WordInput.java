package com.example.meta5.dto;

import com.example.meta5.entity.DomainInfo;
import com.example.meta5.entity.Word;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordInput {
    private String wordEngName;
    private String wordName;
    private String wordEngFullName;
    private String wordType;
    private String domainName;

    public Word toWord(){
        Word word = new Word();
        word.setWordName(this.wordName);
        word.setWordEngName(this.wordEngName);
        word.setWordType(this.wordType);
        word.setWordEngFullName(this.wordEngFullName);
        DomainInfo domain = new DomainInfo();
        domain.setDomainName(domainName);
        word.setDomainInfo(domain);
        return word;
    }
}