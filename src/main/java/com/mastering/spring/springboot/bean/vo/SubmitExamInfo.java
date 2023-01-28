package com.mastering.spring.springboot.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class SubmitExamInfo {
    private Integer moonAge;
    private Integer currentMoonAge;
    private List<SubmitAnswers> answers;
}
