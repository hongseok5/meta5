package com.example.meta5.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TermWordId implements Serializable {

    @Column(name = "TERM_ENG_NAME")
    private String termEngName;

    @Column(name = "WORD_ENG_NAME")
    private String wordEngName;

    @Column(name = "WORD_SEQ")
    private int wordSeq;
}