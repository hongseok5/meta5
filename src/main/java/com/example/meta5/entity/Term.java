package com.example.meta5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Term {

    @Id
    @Column(name="TERM_ENG_NAME")
    private String termEngName;

    @Column(name="TERM_NAME")
    private String termName;

    @ManyToOne
    @JoinColumn(name="domainName")
    private DomainInfo domainInfo;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TermWordInfo> termWordInfos;
}
