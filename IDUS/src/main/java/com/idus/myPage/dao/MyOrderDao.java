package com.idus.myPage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idus.myPage.dto.Order;

@Repository
public class MyOrderDao {

	@Autowired
	private SqlSession sqlSession;

	// 환불 요청 하기
	public int updateRefundrequest(Order order) {
		return sqlSession.update("order.updateRefundrequest", order);
	}

	// 주문 정보 검색
	public List<Order> selectMyOrder(int customerNo) {
		return sqlSession.selectList("order.selectMyOrder", customerNo);
	}
}
