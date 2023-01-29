package com.mastering.spring.springboot.controller;

import com.mastering.spring.springboot.bean.exception.EnumTypeError;
import com.mastering.spring.springboot.bean.exception.NoPreviousMoonAgeError;
import com.mastering.spring.springboot.bean.vo.ExamVo;
import com.mastering.spring.springboot.bean.vo.Score;
import com.mastering.spring.springboot.bean.vo.SubmitExamInfo;
import com.mastering.spring.springboot.service.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class ExamController {

	@Autowired
	private ExamServiceImpl examService;

	/**
	 * 获取题目列表
	 * @param moonAge
	 * @return
	 */
	@GetMapping("/queryQuestion")
	public ExamVo queryQuestion(@RequestParam("age") Integer moonAge)
			throws NoPreviousMoonAgeError {
		return examService.queryQuestionByAge(moonAge);
	}

	/**
	 * 提交试卷
	 * @param submitExamInfo
	 * @return
	 */
	@PostMapping("/submitExam")
	public ExamVo submitExam(@RequestBody SubmitExamInfo submitExamInfo)
			throws NoPreviousMoonAgeError, EnumTypeError {
		return examService.submitExam(submitExamInfo);
	}

	/**
	 * 计算分数
	 * @param submitExamInfo
	 * @return
	 */
	@PostMapping("/calculateScore")
	public Score calculateScore(@RequestBody SubmitExamInfo submitExamInfo){
		return examService.calculateScore(submitExamInfo);
	}
}