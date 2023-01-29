package com.mastering.spring.springboot.bean.vo.exam;

import com.mastering.spring.springboot.bean.vo.DomainAge;
import com.mastering.spring.springboot.bean.vo.score.DomainScore;
import com.mastering.spring.springboot.bean.vo.score.ItemScore;
import com.mastering.spring.springboot.bean.vo.score.Score;
import lombok.Data;

import java.util.List;

@Data
public class ExamVo {
    private List<DomainAge> domainAges;
    private Score currentScore=new Score();
    private List<QuestionResponse> questionResponses;
}
