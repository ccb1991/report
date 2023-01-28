package com.mastering.spring.springboot.bean.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "answerDetail")
@Table(name="answer_question_relation")
@Data
public class AnswerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionId")
    @JsonIgnoreProperties(value = {"answerList"})
    private QuestionDetail question; // 关联问题id
    private String option; // 选项
    private Integer score; // 分数
    private boolean standardAnswer; // 分数
    private String createTime;
    private String modifyTime;
    private String creator;
    private String editor;
}