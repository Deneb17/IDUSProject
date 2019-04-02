package com.idus.auth.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.idus.auth.dao.JoinDao;
import com.idus.auth.dao.LoginDao;
import com.idus.auth.dto.Authorization;
import com.idus.auth.dto.KakaoLoginRequest;
import com.idus.auth.dto.LoginRequest;
import com.idus.auth.dto.LoginSession;
import com.idus.auth.dto.Member;
import com.idus.auth.dto.NaverLoginRequest;
import com.idus.auth.exceptions.FailToCreateSessionException;
import com.idus.common.util.SecurityManager;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao dao;
	@Autowired
	private JoinDao joinDao;
	
	
	// 일반회원 로그인 서비스
	@Transactional
	public boolean doLogin(LoginRequest loginRequest, HttpSession session, Errors errors, HttpServletRequest request, HttpServletResponse response) {
		
		// 서비스에 필요한 변수 정의
		boolean isLoginSuccess = false;
		String requestEmail = loginRequest.getEmail();  // id
		String requestPassword = SecurityManager.hashBySha(loginRequest.getPassword());  // password
		boolean agreeRemember = loginRequest.isRememberMe();  // remember me
		
		// 입력된 email로 member 검색
		Member member = dao.selectMemberByEmail(requestEmail);
		
		// 검색된 member가 없을 때
		if(member == null) {
			errors.reject("loginFail");
			return isLoginSuccess;
		} else if(member.isSocial()) {
			// 검색된 회원이 소셜 로그인 회원 일 경우
			return isLoginSuccess;
		}
		
		// 패스워드 검사
		boolean isEqual = member.comparePasswordWith(requestPassword);
		
		if(isEqual) {
			
			// 패스워드가 같을 경우
			isLoginSuccess = true;
			
			// 인증객체 생성 후 세션에 추가
			Authorization auth = new Authorization();
			auth.setMemberNo(member.getMemberNo());  // 회원번호
			auth.setEmail(member.getEmail());  // 회원 이메일
			auth.setName(member.getName());  // 회원 이름
			auth.setNickName(member.getNickName());  // 회원 닉네임
			auth.setThumbnail(member.getThumbnail());  // 회원 썸네일
			session.setAttribute("auth", auth);
			
			// 자동 로그인에 동의 하였을 경우
			if(agreeRemember) {
				
				// 쿠키 생성에 사용될 변수 선언
				String autoLogin = SecurityManager.hashByMd5("autoLogin");
				String sessionId = SecurityManager.hashByMd5(String.valueOf(member.getMemberNo()));
				
				// 데이터베이스에 저장된 로그인세션 검사
				LoginSession loginSession = dao.selectLoginSession(sessionId);
				
				// 검색된 로그인 세션이 없을 경우
				if(loginSession == null) {
					
					// 새로운 로그인 세션 객체 생성
					loginSession = new LoginSession();
					loginSession.setSessionId(sessionId);  // md5로 암호화된 회원번호
					loginSession.setMemberNo(member.getMemberNo());  // 회원번호
					
					// 데이터베이스에 저장
					int result = dao.insertLoginSession(loginSession);
					
					// 세션 저장 실패 시
					if(result <= 0) {
						throw new FailToCreateSessionException("로그인 세션 저장에 실패 하였습니다.");
					}
					
				}
				
				// 쿠키 생성
				Cookie cookie = new Cookie(autoLogin, sessionId);
				cookie.setMaxAge(60*60*24*30);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
		} else {
			// 패스워드가 다를경우
			errors.reject("loginFail");
		}
		
		return isLoginSuccess;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// 네이버 로그인 서비스
	@Transactional
	public boolean doNaverLogin(NaverLoginRequest naverLoginRequest, HttpSession session) {
		
		boolean isLoginSuccess = false;
		
		// 로그인 요청이 정상적으로 들어오지 않았을 경우
		if(naverLoginRequest == null) {
			return isLoginSuccess;
		}
		
		// 이메일로 회원검색
		Member member = dao.selectMemberByEmail(naverLoginRequest.getEmail());
		
		// 검색된 회원이 존재 할 경우
		if(member != null) {
			
			// 검색된 회원이 소셜 회원일 경우 -> 로그인 처리
			if(member.isSocial()) {
				
				// 인증 객체 생성 후 세션에 추가
				Authorization auth = new Authorization();
				auth.setMemberNo(member.getMemberNo());
				auth.setEmail(member.getEmail());
				auth.setName(member.getName());
				auth.setNickName(member.getNickName());
				auth.setThumbnail(member.getThumbnail());
				session.setAttribute("auth", auth);
				
				// return값을 true로 변경
				isLoginSuccess = true;
				
			}
			
			// 검색된 회원이 소셜 회원이 아닐경우 -> 로그인 실패
			
		} else {  // 검색된 회원이 없을 경우
			
			// 새로운 회원 객체 생성
			member = new Member();
			member.setEmail(naverLoginRequest.getEmail());
			member.setName(naverLoginRequest.getName());
			member.setNickName(naverLoginRequest.getNickName() + naverLoginRequest.getId());
			member.setThumbnail(naverLoginRequest.getThumbnail());
			member.setSocial(true);
			
			// 데이터베이스에 저장
			int memberNo = joinDao.insertNewMember(member);
			
			// 인증 객체 생성 후 세션에 추가
			Authorization auth = new Authorization();
			auth.setMemberNo(memberNo);
			auth.setEmail(member.getEmail());
			auth.setName(member.getName());
			auth.setNickName(member.getNickName());
			auth.setThumbnail(member.getThumbnail());
			session.setAttribute("auth", auth);
			
			// return값을 true로 변경
			isLoginSuccess = true;
			
		}
		
		return isLoginSuccess;
	}

	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	// 카카오 로그인 서비스
	@Transactional
	public boolean doKakaoLogin(KakaoLoginRequest kakaoLoginRequest, HttpSession session, HttpServletRequest request) {
		
		boolean isLoginSuccess = false;
		
		// 로그인 요청이 정상적으로 들어오지 않았을 경우
		if(kakaoLoginRequest == null) {
			return isLoginSuccess;
		}
		
		// 이메일로 회원검색
		Member member = null;
		
		if(kakaoLoginRequest.hasEmail()) {  // 이메일이 포함 되어있을 경우
			
			// 데이터베이스에서 이메일을 이용하여 회원 정보 조회
			member = dao.selectMemberByEmail(kakaoLoginRequest.getEmail());
			
			// 사용자의 프로필이 변경되어 카카오 측에서 이메일을 제공 해 주었을 경우
			if(member == null) {
				// 데이터베이스에서 카카오 아이디를 이용하여 회원 정보 조회
				member = dao.selectMemberByEmail(kakaoLoginRequest.getId());
			}
			
		} else {  // 이메일이 포함되지 않았을 경우
			// 데이터베이스에서 카캉 아이디를 이용하여 회원 정보 조회
			member = dao.selectMemberByEmail(kakaoLoginRequest.getId());
		}
		
		
		// 검색된 회원이 존재 할 경우
		if(member != null) {
			
			// 검색된 회원이 소셜 회원일 경우 -> 로그인 처리
			if(member.isSocial()) {
				
				// 인증 객체 생성 후 세션에 추가
				Authorization auth = new Authorization();
				auth.setMemberNo(member.getMemberNo());
				auth.setEmail(member.getEmail());
				auth.setName(member.getName());
				auth.setNickName(member.getNickName());
				auth.setThumbnail(member.getThumbnail());
				session.setAttribute("auth", auth);
				
				// return값을 true로 변경
				isLoginSuccess = true;
				
			}
			
			// 검색된 회원이 소셜 회원이 아닐경우 -> 로그인 실패
			
		} else {  // 검색된 회원이 없을 경우
			
			// 새로운 회원 객체 생성
			member = new Member();
			
			if(kakaoLoginRequest.hasEmail()) {
				 // 프로필 정보에 이메일이 있을 경우
				member.setEmail(kakaoLoginRequest.getEmail());
			} else {
				// 프로필 정보에 이메일이 없을 경우 카카오 회원번호로 이메일을 대체
				member.setEmail(kakaoLoginRequest.getId());
			}
			
			member.setName(kakaoLoginRequest.getNickName());
			member.setNickName(kakaoLoginRequest.getNickName() + kakaoLoginRequest.getId());
			
			if(kakaoLoginRequest.hasThumbnail()) {
				// 프로필 정보에 썸네일이 있을 경우
				member.setThumbnail(kakaoLoginRequest.getThumbnail());
			} else {
				// 프로필 정보에 썸네일이 없을 경우 기본 썸네일로 지정
				member.setThumbnail(request.getContextPath() + "/resources/user/image/userThumbnail/default_thumbnail.png");
			}
			
			member.setSocial(true);  // 소셜 회원으로 설정
			
			// 데이터베이스에 저장
			int memberNo = joinDao.insertNewMember(member);
			
			// 인증 객체 생성 후 세션에 추가
			Authorization auth = new Authorization();
			auth.setMemberNo(memberNo);
			auth.setEmail(member.getEmail());
			auth.setName(member.getName());
			auth.setNickName(member.getNickName());
			auth.setThumbnail(member.getThumbnail());
			session.setAttribute("auth", auth);
			
			// return값을 true로 변경
			isLoginSuccess = true;
			
		}
		
		return isLoginSuccess;
	}
	
}
