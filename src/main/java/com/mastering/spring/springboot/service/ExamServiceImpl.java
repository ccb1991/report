package com.mastering.spring.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.mastering.spring.springboot.bean.dto.AnswerDetail;
import com.mastering.spring.springboot.bean.dto.QuestionDetail;
import com.mastering.spring.springboot.bean.exception.NoPreviousMoonAgeError;
import com.mastering.spring.springboot.bean.vo.*;
import com.mastering.spring.springboot.repository.AnswerRepository;
import com.mastering.spring.springboot.repository.QuestionDetailRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.MonitorInfo;
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
        examVo.setCurrentMoonAge(moonAge);
        return  examVo;
    }

    /**
     * 根据答案判断是否有错返回下一题
     * @param submitExamInfo
     * @return
     */
    public ExamVo submitExam(SubmitExamInfo submitExamInfo) throws NoPreviousMoonAgeError {
        List<SubmitAnswers> submitAnswers=submitExamInfo.getAnswers();
        List<Integer> ids= submitAnswers.stream().map(SubmitAnswers::getQuestionId).
                collect(Collectors.toList());
        List<QuestionDetail> questionDetails=questionDetailRepository.findById(ids);
        List<String> domains=new ArrayList<>();
        List<QuestionDetail> nextQuestion=new ArrayList<>();
        submitAnswers.forEach(submitAnswer->{
            Integer questionId= submitAnswer.getQuestionId();
            QuestionDetail questionDetail=questionDetails.stream().filter(q->q.getId().equals(questionId)
            ).findFirst().get();
            AnswerDetail answerDetail=questionDetail.getAnswerList()
                    .stream().filter(AnswerDetail::isStandardAnswer).findFirst().get();
            if (!answerDetail.getId().equals(submitAnswer.getAnswerId())){
                domains.add(questionDetail.getDomain().getDomain());
//                List<Question> questions=questionRepository.findByAgeAndDomain(
//                        submitExamInfo.getAge(),questionDetail.getDomain().getDomain());
            }
        });
        List<QuestionResponse> questionResponses=new ArrayList<>();
        Integer currentAge=MoonAge.getPreviousMoonAge(submitExamInfo.getMoonAge());
        if (domains.size()!=0 && submitExamInfo.getMoonAge()>MoonAge.stage){
            Integer minAge=MoonAge.getPreviousMoonAge(currentAge);
            List<QuestionDetail> questions=questionDetailRepository.findByAgeAndDomain(
                    minAge,currentAge,domains);
            questionResponses=questions.stream().map(q->
                    JSONObject.parseObject(JSONObject.toJSONString(q),QuestionResponse.class)
            ).collect(Collectors.toList());
        }
        ExamVo examVo=new ExamVo();
        examVo.setQuestionResponses(questionResponses);
        examVo.setCurrentMoonAge(currentAge);
        return examVo;
    }

    public Score calculateScore(SubmitExamInfo submitExamInfo){
        return new Score();
    }
}
