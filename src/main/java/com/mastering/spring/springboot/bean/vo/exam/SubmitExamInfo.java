package com.mastering.spring.springboot.bean.vo.exam;

import com.mastering.spring.springboot.bean.exception.EnumTypeError;
import com.mastering.spring.springboot.bean.vo.DomainAge;
import com.mastering.spring.springboot.bean.vo.DomainType;
import com.mastering.spring.springboot.bean.vo.score.Score;
import lombok.Data;

import java.util.List;

@Data
public class SubmitExamInfo {
    private Integer moonAge;
    private List<DomainAge> domainAges;
    private Score currentScore;
    private List<SubmitAnswers> answers;

    /**
     * 根据领域获取当前领域题目关联的月领
     * @param domainType
     * @return
     * @throws EnumTypeError
     */
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
