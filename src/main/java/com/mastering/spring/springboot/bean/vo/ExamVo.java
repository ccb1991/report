package com.mastering.spring.springboot.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExamVo {
    private Integer currentMoonAge;
    private Integer currentScore;
    private List<QuestionResponse> questionResponses;
}
