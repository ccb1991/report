package com.mastering.spring.springboot.repository;

import com.mastering.spring.springboot.bean.dto.QuestionDetail;
import com.mastering.spring.springboot.bean.dto.QuestionDomain;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DomainTypeRepository extends CrudRepository<QuestionDomain, Long> {
    List<QuestionDomain> findAll();
}
