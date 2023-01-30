package com.mastering.spring.springboot.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "itemPosition")
@Table(name="item_position")
@Data
public class ItemPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId")
    @JsonIgnoreProperties(value = {"itemPositionList"})
    private QuestionItem item;
    private Integer positionType;
    private Integer col;
    private Integer row;
}
