package com.example.meta5.entity;

import com.example.meta5.entity.pk.TermWordId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TermWordInfo {

    @EmbeddedId
    private TermWordId id;

    @ManyToOne
    @MapsId("termEngName")
    @JoinColumn(name = "TERM_ENG_NAME")
    private Term term;

    @ManyToOne
    @MapsId("wordEngName")
    @JoinColumn(name = "WORD_ENG_NAME")
    private Word word;

    @Column(name = "WORD_SEQ", insertable = false, updatable = false)
    private int wordSeq;
}
