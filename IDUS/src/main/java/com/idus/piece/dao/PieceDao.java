package com.idus.piece.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idus.piece.dto.RecentItems;

@Repository
public class PieceDao {
	
	//dao는 데이터를 읽어온다, 추가한다

	@Autowired
	private SqlSession sqlSession;
	
	//아이템 번호를 이용하여 작품 전체를 검색
	public List<RecentItems> selectRecent() {
		return sqlSession.selectList("piece.selectRecent");
	}
	
	public List<RecentItems> selectMoreRecent(int bno) {
		return sqlSession.selectList("piece.selectMoreRecent", bno);
	}
	
	
}
