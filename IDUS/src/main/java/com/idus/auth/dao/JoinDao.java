package com.idus.auth.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idus.auth.dto.Member;

@Repository
public class JoinDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// 새로운 회원 추가
	public int insertNewMember(Member member) {
		return sqlSession.insert("auth.insertNewMember", member);
	}
	
	
	// 이메일로 회원 검색
	public Member selectMemberByEmail(String email) {
		return sqlSession.selectOne("auth.selectMemberByEmail", email);
	}
	
	
	// 닉네임으로 회원 검색
	public Member selectMemberByNickName(String nickName) {
		return sqlSession.selectOne("auth.selectMemberByNickName", nickName);
	}
}
