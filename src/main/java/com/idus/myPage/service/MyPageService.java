package com.idus.myPage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idus.auth.dto.Authorization;
import com.idus.auth.dto.Member;
import com.idus.common.controller.ExceptionController;
import com.idus.common.exception.IllegalAccessException;
import com.idus.myPage.dao.MyOrderDao;
import com.idus.myPage.dao.MyPageDao;
import com.idus.myPage.dto.AuthCheckRequest;
import com.idus.myPage.dto.InformationModifyRequest;
import com.idus.myPage.dto.Order;
import com.idus.myPage.dto.OrderInformation;
import com.idus.myPage.exception.DropMemberFailException;
import com.idus.myPage.exception.GetOrderInformationFailException;
import com.idus.myPage.exception.InformationUpdateFailException;

@Service
public class MyPageService {

	@Autowired
	private MyPageDao dao;
	@Autowired
	private MyOrderDao orderdao;

	// 권한 확인 서비스
	public boolean authCheck(AuthCheckRequest authCheckRequest) {

		System.out.println("권한 확인 서비스를 실행합니다.");

		// 서비스에 필요한 변수 정의
		boolean hasAuthority = false;
		String email = authCheckRequest.getEmail();

		// 데이터베이스에서 이메일로 회원 검색
		Member member = dao.selectMemberByEmail(email);
		System.out.println("데이터베이스에서 회원을 검색합니다.");

		// 검색된 회원이 없을 경우
		if (member == null) {
			return hasAuthority;
		}

		// 검색된 회원과 입력받은 양식의 비밀번호 비교
		boolean isPasswordEqual = member.comparePasswordWith(authCheckRequest.getPassword());

		// 비밀번호가 같을 경우
		if (isPasswordEqual) {
			hasAuthority = true;
		}

		return hasAuthority;
	}

	// 회원 정보 검색 서비스
	public boolean getUserInformation(InformationModifyRequest informationModifyRequest, HttpSession session) {

		System.out.println("회원정보 검색 서비스를 실행합니다.");

		boolean isAccessible = false;

		// 접근 가능한 상태가 아니면
		if (session.getAttribute("accessible") == null) {
			System.out.println("정상적인 접근이 아닙니다.");
			throw new IllegalAccessException("회원 정보 수정페이지로 비정상적인 접근이 감지 되었습니다.");
		}
		session.removeAttribute("accessible");

		// 세션의 인증 객체로 부터 이메일 확인
		Authorization auth = (Authorization) session.getAttribute("auth");
		String email = auth.getEmail();

		// 데이터베이스에서 회원 정보 검색
		Member member = dao.selectMemberByEmail(email); // select * from member where email = ~~~

		// 검색된 회원이 존재 하지 않을 때
		if (member == null) {
			return isAccessible;
		}

		// 회원의 정보를 커맨드 객체로 전달
		informationModifyRequest.getMemberInformation(member);
		isAccessible = true;

		return isAccessible;
	}

	// 회원정보 변경 서비스
	public boolean modifyUserInformation(InformationModifyRequest informationModifyRequest, HttpSession session) {

		System.out.println("회원 정보 변경 서비스를 실행합니다.");

		// 서비스에 필요한 변수 선언
		boolean isUpdateSuccess = false;
		boolean isPasswordConfirm = informationModifyRequest.validatePassword(); // 패스워드 검사
		System.out.println("패스워드 검사를 실행합니다.");

		// 패스워드와 패스워드 확인이 다를 경우
		if (!isPasswordConfirm) {
			System.out.println("패스워드가 일치하지 않습니다.");
			return isUpdateSuccess;
		}

		// 데이터베이스에 회원 정보 업데이트
		int updateNum = dao.updateMemberInfo(informationModifyRequest);
		System.out.println("데이터베이스에 회원 정보를 업데이트 합니다.");

		// 업데이트 성공 시
		if (updateNum > 0) {
			System.out.println("회원정보 업데이트 성공");
			isUpdateSuccess = true;

			// 세션 인증 객체의 닉네임 변경
			Authorization auth = (Authorization) session.getAttribute("auth");
			auth.setName(informationModifyRequest.getName());
			System.out.println("업데이트된 회원정보를 세션에 반영합니다.");

		} else {
			System.out.println("업데이트 처리 도중 exception이 발생하였습니다.");
			throw new InformationUpdateFailException("회원 정보 업데이트에 실패했습니다.");
		}

		return isUpdateSuccess;
	}

	// 회원 탈퇴 서비스
	public boolean dropMembership(HttpSession session) {

		System.out.println("회원 탈퇴 서비스를 실행합니다.");

		// 서비스에 실행 할 변수 선언
		boolean isDeleteSuccess = false;
		Authorization auth = (Authorization) session.getAttribute("auth");
		String email = auth.getEmail();

		// 데이터베이스에서 회원 삭제
		int deleteNum = dao.deleteMemberByEamil(email);
		System.out.println("데이터베이스에서 회원정보를 삭제합니다.");

		if (deleteNum > 0) {
			// 삭제 성공시
			isDeleteSuccess = true;
			// 세션 삭제
			session.invalidate();
			System.out.println("회원 정보 삭제 성공");
		} else {
			// 삭제 실패시
			System.out.println("회원정보 삭제 도중 exception이 발생하였습니다.");
			throw new DropMemberFailException("회원정보 삭제에 실패하였습니다.");
		}
		return isDeleteSuccess;
	}

	// 주문 정보 검색 서비스
	public boolean getOrderInformation(OrderInformation orderInformation, HttpSession session) {

		System.out.println("주문정보 검색 서비스를 실행 합니다");

		boolean isAccessible = false;

		Authorization auth = (Authorization) session.getAttribute("auth");
		int customerNo = auth.getMemberNo();
		
		List<Order> orderList = orderdao.selectMyOrder(customerNo);
		
		if(orderList == null || orderList.size()==0) {
			return isAccessible;
		} else {
			session.setAttribute("orderList", orderList);
			isAccessible = true;
		}
		
		System.out.println("주문정보 검색 서비스를 완료 합니다");
		
		return isAccessible;
	}

	// 주문 환불 요청 서비스
	public boolean modifyRefundRequest(Order order, HttpSession session) {

		System.out.println("주문 환불 서비스를 실행 합니다");

		boolean isAccessible = false;

		return isAccessible;

	}

}
