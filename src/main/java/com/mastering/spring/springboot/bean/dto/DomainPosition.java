package com.mastering.spring.springboot.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "domainPosition")
@Table(name="domain_position")
@Data
public class DomainPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domainId")
    @JsonIgnoreProperties(value = {"domainPositionList"})
    private QuestionDomain domain;
    private Integer positionType;
    private Integer col;
    private Integer row;
}
