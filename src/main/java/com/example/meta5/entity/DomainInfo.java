package com.example.meta5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "domain_info")
public class DomainInfo {

    @Id
    @Column(name = "DOMAIN_NAME", nullable = false, length = 255)
    private String domainName;

    @Column(name = "DATA_TYPE", nullable = false, length = 255)
    private String dataType;

    @Column(name = "DOMAIN_TYPE", nullable = false, length = 255)
    private String domainType;

    @Column(name = "DOMAIN_TYPE_DTL", length = 255)
    private String domainTypeDtl;

    @Column(name = "DATA_LENGTH", nullable = false)
    private int dataLength;

    @Column(name = "REMARK", nullable = true)
    private String remark;
}
