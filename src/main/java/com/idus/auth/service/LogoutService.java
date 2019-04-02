package com.idus.auth.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idus.auth.dao.LogoutDao;
import com.idus.auth.dto.Authorization;
import com.idus.common.util.SecurityManager;

@Service
public class LogoutService {
	
	@Autowired
	private LogoutDao dao;
	
	// 자동 로그인 세션 삭제 서비스
	public boolean terminateSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("자동 로그인 세션 삭제 서비스를 시작합니다.");
		
		// 서비스에 필요한 변수 정의
		boolean isTerminateSuccess = false;
		Cookie[] cookies = request.getCookies();
		String autoLogin = SecurityManager.hashByMd5("autoLogin");
		String cookieName = null;
		String sessionId = null;
		
		// client의 쿠키로 부터 세션아이디를 확인
		for (Cookie c : cookies) {
			cookieName = c.getName();
			if (cookieName.equals(autoLogin)) {
				sessionId = c.getValue();
				break;
			}
		}
		
		// 쿠키에 세션 아이디가 없을 경우
		if(sessionId == null){
			
			System.out.println("쿠키로 부터 세션 아이디를 확인하는데 실패 하였습니다.");
			System.out.println("세션의 인증 객체로부터 세션 아이디를 생성 합니다.");
			
			// 세션으로 부터 인증 객체 확인
			Authorization auth = (Authorization) session.getAttribute("auth");
			
			
			if(auth != null) {
				// 인증객체가 있을 경우
				sessionId = SecurityManager.hashByMd5(auth.getEmail());
			} else {
				// 인증 객체가 없을 경우
				return isTerminateSuccess;
			}
			
		}
		
		// 데이터 베이스에서 로그인 세션 삭제
		int deletedNum = dao.deleteLoginSession(sessionId);
		System.out.println("저장된 자동 로그인 세션 정보를 삭제합니다.");
		
		// 삭제 성공 시
		if(deletedNum > 0) {
			isTerminateSuccess = true;
			System.out.println("자동 로그인 세션이 성공적으로 삭제 되었습니다.");
		}
		
		return isTerminateSuccess;
	}
	
}
