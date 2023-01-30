package com.mastering.spring.springboot.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "questionItem")
@Table(name = "question_item")
@Data
public class QuestionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String item;
    private String itemName;
    private String createTime;
    private String modifyTime;
    private String creator;
    private String editor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domainId")
    @JsonIgnoreProperties(value = {"questionItems"})
    private QuestionDomain questionDomain;
    @OneToMany(mappedBy = "item")
    @JsonIgnoreProperties(value = { "item" })
    private List<ItemPosition> itemPositionList;
}
