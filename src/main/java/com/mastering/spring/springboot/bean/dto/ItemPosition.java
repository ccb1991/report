package com.mastering.spring.springboot.bean.dto;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "itemPosition")
@Table(name="item_position")
@Data
public class ItemPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String item;
    private Integer col;
    private Integer row;
}
