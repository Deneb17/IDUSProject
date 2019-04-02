package com.idus.auth.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idus.auth.dto.LoginSession;
import com.idus.auth.dto.Member;

@Repository
public class LoginDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// email을 이용하여 회원 검색
	public Member selectMemberByEmail(String email) {
		return sqlSession.selectOne("auth.selectMemberByEmail", email);
	}
	
	// 회원번호를 이용하여 회원 검색
	public Member selectMemberByMemberNo(int memberNo) {
		return sqlSession.selectOne("auth.selectMemberByMemberNo", memberNo);
	}
	
	// 세션아이디를 이용하여 자동 로그인 세션 검색
	public LoginSession selectLoginSession(String sessionId) {
		return sqlSession.selectOne("auth.selectLoginSession", sessionId);
	}
	
	// 자동 로그인 세션 추가
	public int insertLoginSession(LoginSession loginSession) {
		return sqlSession.insert("auth.insertLoginSession", loginSession);
	}
	

}