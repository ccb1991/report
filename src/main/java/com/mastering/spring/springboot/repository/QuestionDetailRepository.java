package com.mastering.spring.springboot.repository;

import com.mastering.spring.springboot.bean.dto.QuestionDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionDetailRepository extends CrudRepository<QuestionDetail, Long> {

    @Query(value = "select * from question q where q.id in (?1)",nativeQuery = true)
    List<QuestionDetail> findById(List<Integer> id);

    List<QuestionDetail> findByAgeLessThan(Integer moonAge);

    @Query(value = "select * from question q where q.age between ?1 and ?2",nativeQuery = true)
    List<QuestionDetail> findByAge(Integer minMoonAge,Integer maxMoonAge);

    @Query(value = "select * from question q where q.age between ?1 and ?2 and q.domain = ?3",nativeQuery = true)
    List<QuestionDetail> findByAgeAndDomain(Integer minMoonAge,Integer maxMoonAge,String domain);
}
