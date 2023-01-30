package com.mastering.spring.springboot.repository;

import com.mastering.spring.springboot.bean.dto.QuestionDetail;
import com.mastering.spring.springboot.bean.dto.ReportSheet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportSheetRepository extends CrudRepository<ReportSheet, Long> {
    List<ReportSheet> findAll();
}
