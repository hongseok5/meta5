package com.example.meta5.entity.pk;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TableInfoPk implements Serializable {
    private String systemId;
    private String bizId;
    private String tableId;
}
