package com.mastering.spring.springboot.bean.dto;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "domainPosition")
@Table(name="domain_position")
@Data
public class DomainPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String domain;
    private Integer col;
    private Integer row;
}
