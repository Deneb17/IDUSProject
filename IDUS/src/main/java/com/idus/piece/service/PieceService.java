package com.idus.piece.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idus.piece.dao.PieceDao;
import com.idus.piece.dto.RecentItems;

@Service
//서비스는 기능이다. dao로 가져온 데이터를 li로 정리한다.
public class PieceService {

	@Autowired
	private PieceDao dao;
	
	public List<RecentItems> selectItems() {
		List<RecentItems> result = dao.selectRecent();
		return result;
	}

	public List<RecentItems> selectMoreItems(int bno) {
		List<RecentItems> result = dao.selectMoreRecent(bno);
		return result;
	}
	
}
