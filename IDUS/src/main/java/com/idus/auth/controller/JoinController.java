package com.idus.auth.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idus.auth.dto.JoinRequest;
import com.idus.auth.exceptions.FailToJoinException;
import com.idus.auth.exceptions.FileSaveFailException;
import com.idus.auth.service.JoinService;
import com.idus.common.util.JsonStringBuilder;
import com.idus.common.util.ResponseManager;

@Controller
@RequestMapping("/join")
public class JoinController {
	
	@Autowired
	private JoinService service;
	private String joinPageView = "auth/join/joinPage";
	private String joinSuccessView = "auth/join/joinSuccess";
	private String joinFailView = "auth/join/joinFail";
	
	
	// 회원가입 view 핸들러
	@RequestMapping(method=RequestMethod.GET)
	public String joinPageHandler(JoinRequest joinRequest) {
		return joinPageView;
	}
	
	
	// 회원가입 처리 핸들러
	@RequestMapping(method=RequestMethod.POST)
	public String joinHandler(JoinRequest joinRequest, HttpServletRequest request) {
		
		boolean isJoinSuccess = service.doJoin(joinRequest, request);
		
		if(isJoinSuccess) {
			return "redirect:/join/joinSuccess";
		} else {
			// exceptionHanlder에서 처리 되기 때문에 사용 되지 않는 코드.
			return joinFailView;
		}
		
	}
	
	
	// 회원가입 성공 view 핸들러
	@RequestMapping(value="/joinSuccess", method=RequestMethod.GET)
	public String joinSuccessHandler() {
		return joinSuccessView;
	}
	
	
	// 이메일 중복체크 핸들러
	@RequestMapping(value="/emailDup", method=RequestMethod.POST)
	public void emailDuplicationCheckHandler(String email, HttpServletResponse response) {
		
		// 이메일 중복 체크 서비스 실행
		boolean isDuplicated = service.emailDuplicationCheck(email);
		
		// 클라이언트로 응답 하기 위해 json과 printwriter 객체 생성
		JsonStringBuilder json = new JsonStringBuilder();
		PrintWriter out = ResponseManager.getJSONWriter(response);
		
		// json String 생성
		if(isDuplicated) {
			// 이메일 중복 시
			json.addEntry("isDuplicated", true);
		} else {
			// 이메일 중복 아닐 시
			json.addEntry("isDuplicated", false);
		}
		
		// 클라이언트로 응답 전송
		out.write(json.toString());
	}
	
	
	// 닉네임 중복 체크 핸들러
	@RequestMapping(value = "/nickNameDup", method = RequestMethod.POST)
	public void nickNameDuplicationCheckHandler(String nickName, HttpServletResponse response) {

		// 닉네임 중복 체크 서비스 실행
		boolean isDuplicated = service.nickNameDuplicationCheck(nickName);

		// 클라이언트로 응답 하기 위해 json과 printwriter 객체 생성
		JsonStringBuilder json = new JsonStringBuilder();
		PrintWriter out = ResponseManager.getJSONWriter(response);

		if (isDuplicated) {
			// 닉네임 중복 시
			json.addEntry("isDuplicated", true);
		} else {
			// 닉네임 중복 아닐 시
			json.addEntry("isDuplicated", false);
		}

		// 클라이언트로 응답 전송
		out.write(json.toString());
	}
	
	
	
	// FailToJoinException 핸들러
	@ExceptionHandler(FailToJoinException.class)
	public String failToCreateSessionHandler(FailToJoinException e) {
		return joinFailView;
	}
	
	
	// FileSaveFailException 핸들러
	@ExceptionHandler(FileSaveFailException.class)
	public String fileSaveFailHandler(FileSaveFailException e) {
		return "";
	}
	
}