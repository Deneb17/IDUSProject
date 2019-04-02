package com.idus.auth.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.idus.auth.dao.LoginDao;
import com.idus.auth.dto.Authorization;
import com.idus.auth.dto.LoginSession;
import com.idus.auth.dto.Member;
import com.idus.common.util.SecurityManager;


public class AutoLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private LoginDao dao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 클라이언트의 세션 확인
		HttpSession session = request.getSession();
		
		// 자동 로그인 인터셉터의 브레이크 확인
		if(session.getAttribute("autoLoginBreak") != null) {
			return true;
		}
		
		// 인증 객체 확인
		Authorization auth = (Authorization) session.getAttribute("auth");
		
		// 로그인 되어 있지 않은 상태
		if (auth == null) {

			// client의 자동 로그인 쿠키 검사
			Cookie[] cookies = request.getCookies();
			String autoLogin = SecurityManager.hashByMd5("autoLogin");
			String cookieName = null;
			String sessionId = null;
			
			if(cookies != null) {
				for (Cookie c : cookies) {
					cookieName = c.getName();
					if (cookieName.equals(autoLogin)) {
						sessionId = c.getValue();
						break;
					}
				}  // for end
			}  // if end
			
			// 자동 로그인 쿠키가 있을 경우
			if (sessionId != null) {

				// 데이터 베이스에서 로그인세션 검색
				LoginSession loginSession = dao.selectLoginSession(sessionId);
				
				if(loginSession == null) {
					session.setAttribute("autoLoginBreak", false);
					return true;
				}
				
				// 로그인 세션으로 부터 이메일 추출
				int memberNo = loginSession.getMemberNo();

				// 이메일로 회원 검색
				Member member = dao.selectMemberByMemberNo(memberNo);
				
				// 검색된 회원이 소셜 회원이 아닌경우
				if (member != null && !member.isSocial()) {

					// 새로운 인증 객체 생성
					auth = new Authorization();
					auth.setMemberNo(member.getMemberNo());
					auth.setEmail(member.getEmail());
					auth.setName(member.getName());
					auth.setNickName(member.getNickName());
					auth.setThumbnail(member.getThumbnail());
					
					// 세션에 인증객체를 추가하여 로그인 처리
					session.setAttribute("auth", auth);
					
				}  // if end

			}  // if end

		}  // if end
		
		// 자동 로그인 인터셉터의 브레이크 설정
		session.setAttribute("autoLoginBreak", false);
		return true;
		
	}  // preHandle method end

}
