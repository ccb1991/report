package com.mastering.spring.springboot.bean.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "questionDomain")
@Table(name = "question_domain")
@Data
public class QuestionDomain  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String domain;
    private String domainName;
    private   String createTime;
    private   String modifyTime;
    private   String creator;
    private   String editor;

}
