package com.mastering.spring.springboot.bean.dto;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "questionItem")
@Table(name = "question_item")
@Data
public class QuestionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String item;
    private String itemName;
    private   String createTime;
    private   String modifyTime;
    private   String creator;
    private   String editor;
}
