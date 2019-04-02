package com.idus.auth.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idus.auth.dto.KakaoLoginRequest;
import com.idus.auth.dto.LoginRequest;
import com.idus.auth.dto.NaverLoginRequest;
import com.idus.auth.exceptions.FailToCreateSessionException;
import com.idus.auth.service.LoginService;
import com.idus.common.util.JsonStringBuilder;
import com.idus.common.util.ResponseManager;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService service;
	private String loginPageView = "auth/login/loginPage";
	private String naverCallbackView = "auth/login/naver/callback";
	private String failToCreateSessionView = "auth/login/sessionTerminateFail";
	private String[] excludeUrl = {"/join", "/login", "/error"};
	
	
	
	// 로그인 뷰 핸들러
	@RequestMapping(method=RequestMethod.GET)
	public String loginPageHandler(LoginRequest loginRequest, HttpSession session, HttpServletRequest request) {
		
		// 세션에서 이전 페이지의 url을 확인
		String redirectUrlAfterLogin = (String) session.getAttribute("referer");
		
		// 세션에 이전 페이지의 url이 없는 경우
		if(redirectUrlAfterLogin == null) {
			
			// request로 부터 이전 페이지의 url을 확인
			redirectUrlAfterLogin = (String) request.getHeader("referer");
			
			// request에 이전 페이지의 url이 없을경우.
			if(redirectUrlAfterLogin == null) {
				
				// 웹사이트의 메인으로 redirect url 설정
				redirectUrlAfterLogin = "/";
				
			} else {
				
				// request에 이전 페이지의 url이 있는 경우 이전 페이지의 url이 예외에 포함되는 url인지 확인
				for(String k : excludeUrl) {
					int index = redirectUrlAfterLogin.indexOf(k);
					if(index >= 0) {
						// 이전 페이지의 url이 예외 url을 포함하는경우 웹사이트의 메인으로 redirect url 설정
						redirectUrlAfterLogin = "/";
					}
				}
				
			}
			
		}  // if end
		
		// 로그인 처리 이후 원래 페이지로 돌아가기 위한 url을 세션에 저장
		session.setAttribute("referer", redirectUrlAfterLogin);
		
		return loginPageView;
	}
	
	
	
	// 로그인 핸들러
	@RequestMapping(method=RequestMethod.POST)
	public String loginHandler(LoginRequest loginRequest, Errors errors, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		// 로그인 처리 이후 원래 페이지로 돌아가기 위한 url
		String url = (String) session.getAttribute("referer");  
		
		// 로그인 서비스 실행
		boolean isLoginSuccess = service.doLogin(loginRequest, session, errors, request, response);
		
		if(isLoginSuccess) {
			// 로그인 성공 시
			return "redirect:" + url;
		} else {
			// 로그인 실패 시
			// 에러 처리를 위해 request에 errors 객체 추가
			request.setAttribute("errors", errors);
			return loginPageView;
		}
		
	}
	
	
	
	// 네이버 로그인 뷰 핸들러
	@RequestMapping(value="/naver", method=RequestMethod.GET)
	public String naverLoginCallback() {
		return naverCallbackView;
	}
	
	
	
	// 네이버 로그인 핸들러
	@RequestMapping(value="/naver", method=RequestMethod.POST)
	public void naverLoginHandler(NaverLoginRequest naverLoginRequest, HttpServletResponse response, HttpSession session) {
		
		boolean isLoginSuccess = service.doNaverLogin(naverLoginRequest, session);
		
		JsonStringBuilder json = new JsonStringBuilder();
		PrintWriter out = ResponseManager.getJSONWriter(response);
		
		if(isLoginSuccess) {  // 로그인 성공 시
			json.addEntry("success", true);
			json.addEntry("url", session.getAttribute("referer"));
		} else {  // 로그인 실패 시
			json.addEntry("success", false);
		}
		
		out.write(json.toString());
		
	}
	
	
	
	// 카카오 로그인 핸들러
	@RequestMapping(value="/kakao", method=RequestMethod.POST)
	public void kakaoLoginHandler(KakaoLoginRequest kakaoLoginRequest, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		
		boolean isLoginSuccess = service.doKakaoLogin(kakaoLoginRequest, session, request);
		
		JsonStringBuilder json = new JsonStringBuilder();
		PrintWriter out = ResponseManager.getJSONWriter(response);
		
		if(isLoginSuccess) {  // 로그인 성공 시
			json.addEntry("success", true);
			json.addEntry("url", session.getAttribute("referer"));
		} else {  // 로그인 실패 시
			json.addEntry("success", false);
		}
		
		out.write(json.toString());
	}
	
	
	
	// FailToCreateSessionException 핸들러
	@ExceptionHandler(FailToCreateSessionException.class)
	public String FailToCreateSessionHandler(FailToCreateSessionException e) {
		System.out.println("FailToCreateSessionException 핸들러를 실행합니다.");
		System.out.println(e.getMessage());
		return failToCreateSessionView;
	}
	
}