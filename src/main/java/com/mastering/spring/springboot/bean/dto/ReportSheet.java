package com.mastering.spring.springboot.bean.dto;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "reportSheet")
@Table(name="report_sheet")
@Data
public class ReportSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String  sheetName;
    private String  rootDomainName;
}
