package com.mastering.spring.springboot.controller;

import com.mastering.spring.springboot.bean.exception.*;
import com.mastering.spring.springboot.bean.vo.exam.ExamVo;
import com.mastering.spring.springboot.bean.vo.score.Score;
import com.mastering.spring.springboot.bean.vo.exam.SubmitExamInfo;
import com.mastering.spring.springboot.service.ExamServiceImpl;
import org.apache.poi.ss.usermodel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/exam")
public class ExamController {
	@Autowired
	private  HttpServletRequest request;

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

	@GetMapping("/queryQuestion2")
	public ExamVo queryQuestion2(@RequestParam("age") Integer moonAge)
			throws NoPreviousMoonAgeError, ExecutionException, InterruptedException {
		Future<ExamVo> future=examService.queryQuestionByAge2(moonAge);
		return future.get();
	}

	@GetMapping("/queryQuestion3")
	public ExamVo queryQuestion3(@RequestParam("age") Integer moonAge
	,@RequestHeader("token") String header)
			throws NoPreviousMoonAgeError, ExecutionException, InterruptedException {
		Future<ExamVo> future=examService.queryQuestionByAge3(moonAge);
		System.out.println(header);
		String value=request.getHeader("token");
		return future.get();
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
			throws NoStandardAnswer, IOException, DomainTypeNotFound, PositionTypeError, ItemTypeNotFound {
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