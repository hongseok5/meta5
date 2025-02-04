package com.example.meta5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name="DATA_TYPE")
    private String dataType;

    //@Column(name="DATA_LENGTH")
    //private String dataLength;
}
