package com.mastering.spring.springboot.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mastering.spring.springboot.bean.exception.NoStandardAnswer;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "questionDetail")
@Table(name = "question")
@Data
public class QuestionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private   Integer id;
    @OneToOne
    @JoinColumn(name="domain",referencedColumnName="domain")
    private   QuestionDomain domain;
    private   Integer age;
    @OneToOne
    @JoinColumn(name="item",referencedColumnName="id")
    private   QuestionItem item;
    private   String subitem;
    private   String describe;
    private   String createTime;
    private   String modifyTime;
    private   String creator;
    private   String editor;
    @OneToMany(mappedBy = "question")
    @JsonIgnoreProperties(value = { "question" })
    private List<AnswerDetail> answerList;

    public QuestionDetail() {// Make JPA Happy

    }

    public static AnswerDetail getStandardAnswer(List<AnswerDetail> answerList) throws NoStandardAnswer {
        for (AnswerDetail a:answerList){
            if (a.isStandardAnswer()){
                return a;
            }
        }
        throw new NoStandardAnswer("未配置标准答案");
    }

}
