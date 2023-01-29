package com.mastering.spring.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.mastering.spring.springboot.bean.dto.AnswerDetail;
import com.mastering.spring.springboot.bean.dto.QuestionDetail;
import com.mastering.spring.springboot.bean.exception.EnumTypeError;
import com.mastering.spring.springboot.bean.exception.NoPreviousMoonAgeError;
import com.mastering.spring.springboot.bean.exception.NoStandardAnswer;
import com.mastering.spring.springboot.bean.vo.*;
import com.mastering.spring.springboot.bean.vo.exam.ExamVo;
import com.mastering.spring.springboot.bean.vo.exam.QuestionResponse;
import com.mastering.spring.springboot.bean.vo.exam.SubmitAnswers;
import com.mastering.spring.springboot.bean.vo.exam.SubmitExamInfo;
import com.mastering.spring.springboot.bean.vo.score.Score;
import com.mastering.spring.springboot.repository.AnswerRepository;
import com.mastering.spring.springboot.repository.QuestionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionDetailRepository questionDetailRepository;


    public ExamVo queryQuestionByAge(Integer moonAge) throws NoPreviousMoonAgeError {
        Integer previousMoonAge=MoonAge.getPreviousMoonAge(moonAge);
        List<QuestionDetail> questions=questionDetailRepository.findByAge(previousMoonAge,moonAge);
        List<QuestionResponse> questionResponses=questions.stream().map(q->
            JSONObject.parseObject(JSONObject.toJSONString(q),QuestionResponse.class)
        ).collect(Collectors.toList());
        ExamVo examVo=new ExamVo();
        examVo.setQuestionResponses(questionResponses);
//        examVo.setCurrentMoonAge(moonAge);
        return  examVo;
    }

    /**
     * 根据答案判断是否有错返回下一题
     * @param submitExamInfo
     * @return
     */
    public ExamVo submitExam(SubmitExamInfo submitExamInfo) throws NoPreviousMoonAgeError, EnumTypeError {
        List<SubmitAnswers> submitAnswers=submitExamInfo.getAnswers();
        List<Integer> ids= submitAnswers.stream().map(SubmitAnswers::getQuestionId).
                collect(Collectors.toList());
        List<QuestionDetail> questionDetails=questionDetailRepository.findById(ids);
        List<DomainType> domains=new ArrayList<>();
        submitAnswers.forEach(submitAnswer->{
            Integer questionId= submitAnswer.getQuestionId();
            QuestionDetail questionDetail=questionDetails.stream().filter(q->q.getId().equals(questionId)
            ).findFirst().get();
            AnswerDetail answerDetail=questionDetail.getAnswerList()
                    .stream().filter(AnswerDetail::isStandardAnswer).findFirst().get();
            if (!answerDetail.getId().equals(submitAnswer.getAnswerId())){
                domains.add(DomainType.valueOf(questionDetail.getDomain().getDomain()));
//                List<Question> questions=questionRepository.findByAgeAndDomain(
//                        submitExamInfo.getAge(),questionDetail.getDomain().getDomain());
            }
        });
        List<QuestionResponse> questionResponses=new ArrayList<>();
        List<DomainAge> domainAges=new ArrayList<>();
        if (domains.size()!=0 && submitExamInfo.getMoonAge()>MoonAge.stage){
            for (DomainType domain: domains){
                Integer domainCurrentMoonAge=submitExamInfo.getAgeByDomain(domain);
                Integer newCurrentAge=MoonAge.getPreviousMoonAge(domainCurrentMoonAge);
                if (newCurrentAge>0){
                    Integer minAge=MoonAge.getPreviousMoonAge(newCurrentAge);
                    List<QuestionDetail> questions=questionDetailRepository.findByAgeAndDomain(
                            minAge,newCurrentAge,domain.name());
                    questionResponses.addAll(questions.stream().map(q->
                            JSONObject.parseObject(JSONObject.toJSONString(q),QuestionResponse.class)
                    ).collect(Collectors.toList()));
                    domainAges.add(new DomainAge(newCurrentAge,domain));
                }
            }
        }
        ExamVo examVo=new ExamVo();
        examVo.setQuestionResponses(questionResponses);
        examVo.setDomainAges(domainAges);
        return examVo;
    }

    public Score calculateScore(SubmitExamInfo submitExamInfo){
        return new Score();
    }

    public Score calculateTotalScore(Integer moonAge)
            throws NoStandardAnswer {
        List<QuestionDetail> questionDetails=questionDetailRepository.findByAgeLessThan(moonAge);
        Score score=new Score();
        for (QuestionDetail q:questionDetails){
            AnswerDetail answerDetail=q.getStandardAnswer();
            Integer answerScore=answerDetail.getScore();

        }
        return score;
    }
}
