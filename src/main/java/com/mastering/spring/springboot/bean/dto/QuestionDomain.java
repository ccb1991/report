package com.mastering.spring.springboot.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "questionDomain")
@Table(name = "question_domain")
@Data
public class QuestionDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String domain;
    private String domainName;
    private String createTime;
    private String modifyTime;
    private String creator;
    private String editor;
    @OneToMany(mappedBy = "questionDomain")
    @JsonIgnoreProperties(value = { "questionDomain" })
    private List<QuestionItem> questionItems;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domainSheet")
    @JsonIgnoreProperties(value = {"questionDomains"})
    private ReportSheet reportSheet;
    @OneToMany(mappedBy = "domain")
    @JsonIgnoreProperties(value = { "domain" })
    private List<DomainPosition> domainPositionList;

}
