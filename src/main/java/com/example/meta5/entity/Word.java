package com.example.meta5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Word {

    @Id
    private String wordEngName;

    private String wordName;

    private String wordEngFullName;

    private String wordType;

    @ManyToOne
    @JoinColumn(name = "DOMAIN_NAME", referencedColumnName = "DOMAIN_NAME", nullable = true)
    private DomainInfo domainInfo;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TermWordInfo> termWordInfos = new ArrayList<>();
    // 양방향 바인딩은 선택이다. 단어가 사용된 용어등을 검색하는데는 편해지지만 필수는 아니다.

    public String getDomainName() {
        return domainInfo != null ? domainInfo.getDomainName() : null;
    }
}
