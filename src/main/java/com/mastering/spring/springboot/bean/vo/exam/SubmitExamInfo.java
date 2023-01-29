package com.mastering.spring.springboot.bean.vo.exam;

import com.mastering.spring.springboot.bean.exception.EnumTypeError;
import com.mastering.spring.springboot.bean.vo.DomainAge;
import com.mastering.spring.springboot.bean.vo.DomainType;
import lombok.Data;

import java.util.List;

@Data
public class SubmitExamInfo {
    private Integer moonAge;
    private List<DomainAge> domainAges;
    private Integer currentScore;
    private List<SubmitAnswers> answers;

    public Integer getAgeByDomain(DomainType domainType) throws EnumTypeError {
        for(DomainAge d: domainAges){
            if (d.getDomainType().equals(domainType)){
                return d.getMoonAge();
            }
        }
        throw new EnumTypeError(String.format("未找到%s关联月龄",
                domainType.toString()));
    }
}
