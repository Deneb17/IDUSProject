package com.idus.auth.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogoutDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 자동 로그인 세션 삭제
	public int deleteLoginSession(String sessionId) {
		return sqlSession.delete("auth.deleteLoginSession", sessionId);
	}
	
}
