package com.mastering.spring.springboot.bean.vo.exam;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse{
    private   Integer id;
    private   Integer age;
    private   String describe;
    private List<AnswerResponse> answerList;
}
