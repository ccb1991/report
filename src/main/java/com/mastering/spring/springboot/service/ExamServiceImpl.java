package com.mastering.spring.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.mastering.spring.springboot.bean.dto.*;
import com.mastering.spring.springboot.bean.exception.*;
import com.mastering.spring.springboot.bean.report.ExcelReport;
import com.mastering.spring.springboot.bean.vo.*;
import com.mastering.spring.springboot.bean.vo.exam.ExamVo;
import com.mastering.spring.springboot.bean.vo.exam.QuestionResponse;
import com.mastering.spring.springboot.bean.vo.exam.SubmitAnswers;
import com.mastering.spring.springboot.bean.vo.exam.SubmitExamInfo;
import com.mastering.spring.springboot.bean.vo.score.DomainScore;
import com.mastering.spring.springboot.bean.vo.score.ItemScore;
import com.mastering.spring.springboot.bean.vo.score.Score;
import com.mastering.spring.springboot.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExamServiceImpl {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private DomainTypeRepository domainTypeRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private QuestionDetailRepository questionDetailRepository;

    @Autowired
    private ReportSheetRepository reportSheetRepository;


    public ExamVo queryQuestionByAge(Integer moonAge) throws NoPreviousMoonAgeError {
        log.info("开始根据年龄查询题目");
        Integer previousMoonAge=MoonAge.getPreviousMoonAge(moonAge);
        List<QuestionDetail> questions=questionDetailRepository.findByAge(previousMoonAge,moonAge);
        List<QuestionResponse> questionResponses=questions.stream().map(q->
            JSONObject.parseObject(JSONObject.toJSONString(q),QuestionResponse.class)
        ).collect(Collectors.toList());
        ExamVo examVo=new ExamVo();
        examVo.setQuestionResponses(questionResponses);
//        examVo.setCurrentMoonAge(moonAge);
        log.info("查询结束");
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
        // 计算当前分数
        Score score=submitExamInfo.getCurrentScore();
        if (score==null){
            List<QuestionDomain> questionDomains=domainTypeRepository.findAll();
            List<QuestionItem> questionItems=itemTypeRepository.findAll();
            score=new Score(questionDomains,questionItems);
        }
        Score finalScore = score;
        submitAnswers.forEach(submitAnswer->{
            AnswerDetail subAnswerDetail=answerRepository.findById(
                    submitAnswer.getAnswerId());
            Integer questionId= submitAnswer.getQuestionId();
            QuestionDetail questionDetail=questionDetails.stream().filter(q->q.getId().equals(questionId)
            ).findFirst().get();
            DomainType domainType=DomainType.valueOf(questionDetail.getDomain().getDomain());
            AnswerDetail standardAnswer=questionDetail.getAnswerList()
                    .stream().filter(AnswerDetail::isStandardAnswer).findFirst().get();
            if (!standardAnswer.getId().equals(submitAnswer.getAnswerId())){
                domains.add(domainType);
            } else {
                subAnswerDetail=standardAnswer;
            }
            DomainScore domainScore=new DomainScore(subAnswerDetail.getScore(),
                    domainType);
            finalScore.updateDomainScore(domainScore);
            ItemScore itemScore=new ItemScore(subAnswerDetail.getScore(),
                    ItemType.valueOf(questionDetail.getItem().getItem()));
            finalScore.updateItemScore(itemScore);
        });
        // 查询前一阶段题目
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
        examVo.setCurrentScore(score);
        return examVo;
    }

    /**
     * 生成报告
     * @param submitExamInfo
     * @return
     * @throws NoStandardAnswer
     */
    public void produceReport(SubmitExamInfo submitExamInfo)
            throws NoStandardAnswer, IOException, DomainTypeNotFound, PositionTypeError, ItemTypeNotFound {
        Score totalScore=calculateTotalScore(submitExamInfo.getMoonAge());
        Score currentScore=submitExamInfo.getCurrentScore();
        List<ReportSheet> reportSheets=reportSheetRepository.findAll();
        ExcelReport excelReport=new ExcelReport(totalScore,currentScore,
                submitExamInfo,reportSheets);
        excelReport.writeSheet();
        return;
    }

    /**
     * 根据年龄段计算各领域与大项总分
     * @param moonAge
     * @return
     * @throws NoStandardAnswer
     */
    public Score calculateTotalScore(Integer moonAge)
            throws NoStandardAnswer {
        List<QuestionDetail> questionDetails=questionDetailRepository.findByAgeLessThan(moonAge);
        List<QuestionDomain> questionDomains=domainTypeRepository.findAll();
        List<QuestionItem> questionItems=itemTypeRepository.findAll();
        Score score=new Score(questionDomains,questionItems);
        for (QuestionDetail q:questionDetails){
            AnswerDetail answerDetail=
                    QuestionDetail.getStandardAnswer(q.getAnswerList());
            Integer answerScore=answerDetail.getScore();
            ItemScore itemScore=new ItemScore(answerScore,
                    ItemType.valueOf(q.getItem().getItem()));
            score.updateItemScore(itemScore);
            DomainScore domainScore=new DomainScore(answerScore,
                    DomainType.valueOf(q.getDomain().getDomain()));
            score.updateDomainScore(domainScore);
        }
        return score;
    }
}
