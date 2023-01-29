package com.mastering.spring.springboot.bean.vo;

import com.mastering.spring.springboot.bean.exception.DomainTypeError;
import lombok.Data;

import java.util.List;

@Data
public class SubmitExamInfo {
    private Integer moonAge;
    private List<DomainAge> domainAges;
    private Integer currentScore;
    private List<SubmitAnswers> answers;

    public Integer getAgeByDomain(DomainType domainType) throws DomainTypeError {
        for(DomainAge d: domainAges){
            if (d.getDomainType().equals(domainType)){
                return d.getMoonAge();
            }
        }
        throw new DomainTypeError(String.format("未找到%s关联月龄",
                domainType.toString()));
    }
}
