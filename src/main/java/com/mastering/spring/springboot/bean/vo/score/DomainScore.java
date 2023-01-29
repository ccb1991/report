package com.mastering.spring.springboot.bean.vo.score;

import com.mastering.spring.springboot.bean.vo.DomainType;
import lombok.Data;

import java.util.Objects;

@Data
public class DomainScore {
    private Integer score=0;
    private DomainType domainType;

    public DomainScore(){}

    public DomainScore(DomainType domainType){
        this.domainType=domainType;
    }

    public DomainScore(Integer score, DomainType domainType){
        this.score = score;
        this.domainType=domainType;
    }

    public void setDomain(String domain){
        this.domainType=DomainType.valueOf(domain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainScore that = (DomainScore) o;
        return domainType == that.domainType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainType);
    }
}
