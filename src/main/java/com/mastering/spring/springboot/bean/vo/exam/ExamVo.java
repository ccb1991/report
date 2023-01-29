package com.mastering.spring.springboot.bean.vo.exam;

import com.mastering.spring.springboot.bean.vo.DomainAge;
import com.mastering.spring.springboot.bean.vo.score.DomainScore;
import lombok.Data;

import java.util.List;

@Data
public class ExamVo {
    private List<DomainAge> domainAges;
    private List<DomainScore> domainScores;
    private List<QuestionResponse> questionResponses;
}
