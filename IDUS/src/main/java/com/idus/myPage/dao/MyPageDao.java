package com.idus.myPage.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idus.auth.dto.Member;
import com.idus.myPage.dto.InformationModifyRequest;

@Repository
public class MyPageDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// 이메일로 회원 검색 
	public Member selectMemberByEmail(String email) {
		return sqlSession.selectOne("auth.selectMemberByEmail", email);  // auth-mapper.xml
	}
	
	
	// 회원 정보 업데이트
	public int updateMemberInfo(InformationModifyRequest informationModifyRequest) {
		return sqlSession.update("myPage.updateMemberInfo", informationModifyRequest);
	}
	
	
	// 회원 탈퇴
	public int deleteMemberByEamil(String email) {
		return sqlSession.delete("myPage.deleteMemberByEamil", email);
	}
	
}