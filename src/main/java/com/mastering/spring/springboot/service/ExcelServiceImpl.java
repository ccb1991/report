package com.mastering.spring.springboot.service;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class ExcelServiceImpl {
    private String fileName;
    private List<ReportSheet> sheetList;
}
