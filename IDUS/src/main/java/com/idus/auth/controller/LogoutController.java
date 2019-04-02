package com.idus.auth.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idus.auth.service.LogoutService;
import com.idus.common.util.JsonStringBuilder;
import com.idus.common.util.ResponseManager;
import com.idus.common.util.SecurityManager;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Autowired
	private LogoutService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String logoutHandler(HttpSession session, HttpServletRequest request) {
		
		// 세션에서 인증객체 삭제
		session.removeAttribute("auth");
		
		// 로그아웃 이후 원래 페이지로 돌아가기 위한 url
		String url = request.getHeader("referer");
		
		return "redirect:" + url;
	}
	
	
	// 자동 로그인 세션 삭제 핸들러
	@RequestMapping(value="/deleteSession", method=RequestMethod.POST)
	public void sessionHandler(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		// 자동 로그인 세션 삭제 서비스 실행
		boolean isTerminated = service.terminateSession(session, request, response);
		
		// 클라이언트로 json 형태의 응답을 보낼 printwriter 객체 생성
		PrintWriter out = ResponseManager.getJSONWriter(response);
		JsonStringBuilder json = new JsonStringBuilder();
		
		System.out.println(isTerminated);
		
		if(isTerminated) {
			// 삭제 성공 시
			json.addEntry("isTerminated", true);
			json.addEntry("sessionId", SecurityManager.hashByMd5("autoLogin"));
			out.print(json.toString());
		} else {
			// 삭제 실패시
			json.addEntry("isTerminated", false);
			out.print(json.toString());
		}
		
	}
	
}
