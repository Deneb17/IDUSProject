package com.idus.myPage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.idus.myPage.dto.AuthCheckRequest;
import com.idus.myPage.dto.InformationModifyRequest;
import com.idus.myPage.dto.OrderInformation;
import com.idus.myPage.exception.DropMemberFailException;
import com.idus.myPage.exception.GetOrderInformationFailException;
import com.idus.myPage.exception.InformationUpdateFailException;
import com.idus.myPage.service.MyPageService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	@Autowired
	private MyPageService service;
	private String myPageView = "myPage/myPage";
	private String myOrdersView = "myPage/myOrders/myOrders";
	private String shoppingBagView = "myPage/shoppingBag/shoppingBag";
	private String authCheckView = "myPage/authCheck/authCheck";
	private String myInfoView = "myPage/myInfo/myInfo";
	private String dropMembershipView = "myPage/dropMembership/dropMembership";
	private String modifySuccessView = "myPage/myInfo/modifySuccess";
	private String InformationUpdateFailView = "myPage/myInfo/informationUpdateFail";
	private String dropSuccessView = "myPage/dropMembership/dropSuccess";
	private String dropMemberFailView = "myPage/dropMembership/dropMemberFail";

	// 마이 페이지 뷰 핸들러
	@RequestMapping(method = RequestMethod.GET)
	public String myPageVeiwHandler() {
		System.out.println("마이 페이지 뷰 핸들러를 실행 합니다.");
		return myPageView;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	// 내 주문 뷰 핸들러
	@RequestMapping(value = "/myOrders", method = RequestMethod.GET)
	public String myOrderVeiwHandler(OrderInformation orderInformation, HttpSession session) {

		System.out.println("마이 오더 뷰 핸들러 실행");
		// TODO 데이터베이스에서 주문 내역 긁어와서 뷰에서 뿌리기
		boolean isAccessible = service.getOrderInformation(orderInformation, session);

		if (!isAccessible) {
			throw new GetOrderInformationFailException("주문 정보 검색에 실패하셨습니다");
		}

		return myOrdersView;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////

	// 장바구니 뷰 핸들러
	@RequestMapping(value = "/shoppingBag", method = RequestMethod.GET)
	public String myShoppingBagVeiwHandler() {

		// TODO 데이터베이스에서 장바구니 내역 긁어와서 뷰에서 뿌리기

		System.out.println("장바구니 뷰 핸들러를 실행 합니다.");
		return shoppingBagView;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	// 권한 확인 뷰 핸들러
	@RequestMapping(value = "/authCheck", method = RequestMethod.GET)
	public String authCheckVeiwHandler(Model model, HttpServletRequest request) {

		// TODO 로그인 되어 있는 상태인지 체크 필요 -> 인터셉터 혹은 web 필터 이용.

		System.out.println("권한 확인 뷰 핸들러를 실행 합니다.");
		// request parameter로 넘어온 path값을 model 객체로 전달
		model.addAttribute("path", request.getParameter("path"));
		return authCheckView;
	}

	// 권한 확인 핸들러
	@RequestMapping(value = "/authCheck", method = RequestMethod.POST)
	public String authCheckHandler(AuthCheckRequest authCheckRequest, HttpSession session) {

		System.out.println("권한 확인 핸들러를 실행합니다.");

		String path = authCheckRequest.getPath();
		boolean hasAuthority = service.authCheck(authCheckRequest);

		if (hasAuthority) {
			System.out.println("중요 페이지로의 접근 권한을 설정합니다.");
			session.setAttribute("accessible", true);
			return "redirect:/myPage/" + path;
		} else {
			return "redirect:/myPage/authCheck?path=" + path;
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	// 회원정보 뷰 핸들러
	@RequestMapping(value = "/myInfo", method = RequestMethod.GET)
	public String myInfoViewHandler(InformationModifyRequest informationModifyRequest, HttpSession session) {

		// 사용자 정보 검색 서비스 호출
		boolean isAccessible = service.getUserInformation(informationModifyRequest, session);

		// 접근이 불가능한 경우
		if (!isAccessible) {
			return "redirect:/myPage";
		}

		return myInfoView;
	}

	// 회원정보 업데이트 핸들러
	@RequestMapping(value = "/myInfo", method = RequestMethod.POST)
	public String myInfoHandler(InformationModifyRequest informationModifyRequest, HttpSession session) {

		boolean isUpdateSuccess = service.modifyUserInformation(informationModifyRequest, session);

		if (isUpdateSuccess) {
			// 회원정보 수정 성공시
			return "redirect:/myPage/myInfo/modSuccsss";
		} else {
			// 회원정보 수정 실패시 -> 비밀번호 다를경우
			// 차후 비밀번호 양식에 대한 체크는 javascript 이용 할 계획
			return "redirect:/myPage/authCheck?path=myInfo";
		}
	}

	// 회원 정보 수정 성공 뷰 핸들러
	@RequestMapping(value = "/myInfo/modSuccsss", method = RequestMethod.GET)
	public String modSuccessViewHandler() {
		System.out.println("정보 수정 성공 뷰 핸들러를 실행합니다.");
		return modifySuccessView;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 회원 탈퇴 뷰 핸들러
	@RequestMapping(value = "/dropMembership", method = RequestMethod.GET)
	public String dropMembershipViewHandler() {
		System.out.println("회원탈퇴 뷰 핸들러를 실행합니다.");
		return dropMembershipView;
	}

	// 회원 탈퇴 핸들러
	@RequestMapping(value = "/dropMembership", method = RequestMethod.POST)
	public String dropMembershipHandler(@RequestParam(required = false, defaultValue = "false") boolean agree,
			HttpSession session) {

		System.out.println("회원탈퇴 핸들러를 실행합니다.");

		if (agree) {
			// 탈퇴 동의 시
			System.out.println("탈퇴 동의를 확인 하였습니다.");
			boolean isDeleteSuccess = service.dropMembership(session);

			if (isDeleteSuccess) {
				// 삭제 성공시
				System.out.println("탈퇴 처리가 완료 되었습니다.");
				return "redirect:/myPage/dropMembership/dropSuccess";
			} else {
				// 삭제 실패시 -> exception 처리
				return "redirect:/myPage";
			}
		} else {
			// 탈퇴 하지 않을시
			System.out.println("탈퇴 동의가 확인되지 않았습니다.");
			return "redirect:/myPage";
		}
	}

	// 회원 탈퇴 성공 뷰 핸들러
	@RequestMapping(value = "/dropMembership/dropSuccess", method = RequestMethod.GET)
	public String dropSuccessViewHandler() {
		System.out.println("회원 탈퇴 성공 뷰 핸들러를 실행합니다.");
		return dropSuccessView;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 회원정보 업데이트 도중 exception 발생 시
	@ExceptionHandler(InformationUpdateFailException.class)
	public String InformationUpdateFailHandler(InformationUpdateFailException e) {
		System.out.println("InformationUpdateFailException 핸들러를 실행합니다");
		System.out.println(e.getMessage()); // TODO 로거로 대체
		return InformationUpdateFailView;
	}

	// 회원 탈퇴 도중 exception 발생 시
	@ExceptionHandler(DropMemberFailException.class)
	public String dropMemberFailHandler(DropMemberFailException e) {
		System.out.println("DropMemberFailException 핸들러를 실행합니다.");
		System.out.println(e.getMessage()); // TODO 로거로 대체
		return dropMemberFailView;
	}

}