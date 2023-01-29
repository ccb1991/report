package com.mastering.spring.springboot.bean.vo;

import lombok.Data;

@Data
public class DomainScore {
    private Integer score;
    private DomainType domainType;

    public DomainScore(){}

    public DomainScore(Integer score, DomainType domainType){
        this.score = score;
        this.domainType=domainType;
    }

    public void setDomain(String domain){
        this.domainType=DomainType.valueOf(domain);
    }
}
