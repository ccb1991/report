package com.mastering.spring.springboot.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class Score {
    private List<QuestionDomainVo> questionDomainVos;
}
