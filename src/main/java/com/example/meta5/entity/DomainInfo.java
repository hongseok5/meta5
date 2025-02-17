package com.example.meta5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 도메인을 단어에 매핑시키는가 용어에 매핑시키는가?
 * 통합코드는 단어에 개별코드는 용어에?
 */
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

    @Override
    public boolean equals(Object domainInfo){
        if(this == domainInfo) return true;
        if (domainInfo == null || getClass() != domainInfo.getClass()) return false;
        DomainInfo that = (DomainInfo) domainInfo;
        return Objects.equals(domainName , that.domainName);

    }
}
