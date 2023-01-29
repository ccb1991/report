package com.mastering.spring.springboot.repository;

import com.mastering.spring.springboot.bean.dto.QuestionDetail;
import org.springframework.data.repository.CrudRepository;

public interface ItemTypeRepository extends CrudRepository<QuestionDetail, Long> {
}
