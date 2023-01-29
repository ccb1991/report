package com.mastering.spring.springboot.controller;

import com.mastering.spring.springboot.bean.exception.EnumTypeError;
import com.mastering.spring.springboot.bean.exception.NoPreviousMoonAgeError;
import com.mastering.spring.springboot.bean.exception.NoStandardAnswer;
import com.mastering.spring.springboot.bean.vo.exam.ExamVo;
import com.mastering.spring.springboot.bean.vo.score.Score;
import com.mastering.spring.springboot.bean.vo.exam.SubmitExamInfo;
import com.mastering.spring.springboot.service.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
	@PostMapping("/produceReport")
	@ResponseBody
	public HttpServletResponse calculateScore(
			@RequestBody SubmitExamInfo submitExamInfo,
								HttpServletResponse response)
			throws NoStandardAnswer, IOException {
//		OutputStream outputStream = null;
//		try{
			examService.produceReport(submitExamInfo);
//			File file=examService.produceReport(submitExamInfo);
//			response.addHeader("Content-Disposition","attachment:filename=report.xlsx");
//			response.addHeader("Content-Length",""+file.getTotalSpace());
//			outputStream = new FileOutputStream(file);
//			response.setContentType("application/octet-stream");
//		}
//		finally {
//			if (outputStream!=null){
//				outputStream.flush();
//				outputStream.close();
//			}
//		}
		return response;
	}
}