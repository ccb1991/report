package com.mastering.spring.springboot.bean.vo;

import lombok.Data;

@Data
public class DomainAge {
    private Integer moonAge;
    private DomainType domainType;

    public DomainAge(){}

    public DomainAge(Integer moonAge,DomainType domainType){
        this.moonAge=moonAge;
        this.domainType=domainType;
    }

    public void setDomain(String domain){
        this.domainType=DomainType.valueOf(domain);
    }
}
