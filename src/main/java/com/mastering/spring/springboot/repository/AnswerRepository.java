package com.mastering.spring.springboot.repository;

import com.mastering.spring.springboot.bean.dto.AnswerDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<AnswerDetail, Long> {

//    @Query(value="select a.id,a.answer from answer a where a.question_id=?1",
//            nativeQuery = true)
    List<AnswerDetail> queryAnswerByQuestion(Integer questionId);

    AnswerDetail findById(Integer answerId);
}
