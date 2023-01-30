package com.mastering.spring.springboot.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "reportSheet")
@Table(name="report_sheet")
@Data
public class ReportSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String  sheetName;
    private String  rootDomainName;
    private String  createTime;
    @OneToMany(mappedBy = "reportSheet")
    @JsonIgnoreProperties(value = { "reportSheet" })
    private List<QuestionDomain> questionDomains;

}
