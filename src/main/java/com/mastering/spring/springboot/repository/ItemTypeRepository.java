package com.mastering.spring.springboot.repository;

import com.mastering.spring.springboot.bean.dto.QuestionDetail;
import com.mastering.spring.springboot.bean.dto.QuestionItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemTypeRepository extends CrudRepository<QuestionItem, Long> {
    List<QuestionItem> findAll();
}
