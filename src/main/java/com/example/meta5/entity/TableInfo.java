package com.example.meta5.entity;

import com.example.meta5.entity.pk.TableInfoPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "table_info")
public class TableInfo {

    @EmbeddedId
    private TableInfoPk tableInfoPk;

    private String tableName;
    private String inputType;
    private String deleteType;
    private String comment;

}
