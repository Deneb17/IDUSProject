<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/commons/meta.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/shoppingBag.css">

<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>

	<c:if test="${!empty auth}">
		<%@ include file="/WEB-INF/views/commons/loginHeader.jsp"%>
	</c:if>
	<c:if test="${empty auth}">
		<%@ include file="/WEB-INF/views/commons/noLoginHeader.jsp"%>
	</c:if>

	<section>
		<div class="container">

			<div class="atelier">
				<div class="leftNav">
					<!-- 프로필 -->
					<div class="blogProfile">
						<!-- 프로필 사진 : 블로그 주인 프로필 이미지 받아오기 -->
						<div class="profileImage"
							style="background-image: url(../../resources/user/image/userThumbnail/default_thumbnail.png)">
							<img class="thumbnail" src="${auth.thumbnail}" alt="썸네일"
								style="width: 100%; height: 100%;" />
						</div>
						<!-- 작가 이름 -->
						<div class="name">
							<span class="userName">${auth.name} 님</span>
						</div>
					</div>
					<!-- 일반 메뉴 -->
					<div class="generalMenu">
						<ul>
							<li class="menubtn"><a
								href="${pageContext.request.contextPath}/myPage/myOrders">
									나의 주문정보 </a></li>
							<li class="menubtn"><a
								href="${pageContext.request.contextPath}/myPage/shoppingBag">
									장바구니 </a></li>
							<li class="menubtn"><a
								href="${pageContext.request.contextPath}/myPage/authCheck?path=myInfo">
									내 정보 수정 </a></li>
							<li class="menubtn"><a
								href="${pageContext.request.contextPath}/myPage/authCheck?path=dropMembership">
									탈퇴하기 </a></li>
						</ul>
					</div>
					<!-- 공방 정보 -->
					<div class="info">
						<h3>활동 정보</h3>
						<p>팔로우 :</p>
					</div>
				</div>
				<div class="blogContent">
					<%-- <h1>장바구니 페이지</h1>
					<ul>
						<li><a href="${pageContext.request.contextPath}/">[ 메인
								페이지 ]</a></li>
						<li><a href="${pageContext.request.contextPath}/myPage">[
								마이 페이지 ]</a></li>
					</ul> --%>
					<div class="title-style clf">
						<h2 class="txt fl">장바구니</h2>
						<ol class="page-location fr">
							<li class="active"><em class="icon-num">1</em> <span>장바구니</span>
								<i class="fa fa-angle-right"></i></li>
							<li><em class="icon-num">2</em> <span>주문결제</span> <i
								class="fa fa-angle-right"></i></li>
							<li><em class="icon-num">3</em> <span>주문완료</span></li>
						</ol>
					</div>
					<form class="cart-form" action="/w/cart/add" method="post">
						<input type="hidden" name="reset" value="1">
						<div class="inner-w800">
							<table class="cartCard" data-ui="cart-card" data-ui-type="cart"
								data-artist-status="1"
								data-artist-uuid="911c8cb6-3256-4fe0-9ab7-03b214a6a8c1"
								data-artist-name="pinto (핀토)" data-delivery-style="0"
								data-delivery-policy="50000" data-delivery-charge="3000"
								data-delivery-charge-remote="3000">
								<caption class="hidden">pinto (핀토) 작가님</caption>
								<colgroup>
									<col width="125px">
									<col width="*">
								</colgroup>
								<thead>
									<tr>
										<th colspan="2"><input type="hidden"
											name="membership_price" disabled="" value=""> <input
											type="hidden" name="artist_order_price" disabled="" value="">
											<input type="hidden" name="delivery_charge" disabled=""
											value="3000"> <label> <input class="bp"
												data-ui="check-linked"
												data-linked-parent="911c8cb6-3256-4fe0-9ab7-03b214a6a8c1"
												type="checkbox" checked="" disabled="">
												<div class="txt-group">
													<b class="bold">pinto (핀토) 작가님</b> <em class="light">50,000원
														이상 배송비 무료</em>
												</div>
										</label></th>
									</tr>
								</thead>
								<tbody>

									<tr class="list-item"
										data-cart-uuid="9eb6341f-7bf8-4536-9ec3-85ec4a0d9356"
										data-product-uuid="28873244-2929-4991-8f50-dc1c2d525958">


										<td class="area-img"><input class="bp"
											data-ui="check-linked"
											data-linked-child="911c8cb6-3256-4fe0-9ab7-03b214a6a8c1"
											type="checkbox" data-action="disable_inputs" disabled="">
											<div class="img-bg"
												style="background-image: url(https://image.idus.com/image/files/b9fd06269039454c81ddebc127a807ac_512.jpg)"></div>
										</td>

										<td class="area-txt">
											<div class="txt-group">
												<a class="bold"
													href="/w/product/28873244-2929-4991-8f50-dc1c2d525958"
													target="_blank"> <label for="prd-name">노트북/태블릿
														파우치 세트</label>
												</a> <em class="light">주문시 제작</em>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2" class="flexible">
											<ul class="list-options">
												<li data-artist-uuid="911c8cb6-3256-4fe0-9ab7-03b214a6a8c1"
													data-option-status=""
													data-key="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.W3sib3B0aW9uX3RpdGxlIjoiU0laRSgtNDAlKSIsIm9wdGlvbl9uYW1lIjoiOX4xMVx1Yzc3OFx1Y2U1OCgtNDAlKSIsIm9wdGlvbl9pbmRleCI6MCwib3B0aW9uX3ByaWNlIjotMTQwMH0seyJvcHRpb25fdGl0bGUiOiJcdWMwYzlcdWMwYzEiLCJvcHRpb25fbmFtZSI6Ilx1Yzc3OFx1YjUxNFx1ZDU1MVx1ZDA2YyIsIm9wdGlvbl9pbmRleCI6MSwib3B0aW9uX3ByaWNlIjowfSx7Im9wdGlvbl90aXRsZSI6Ilx1YmJmOFx1YjJjOFx1ZDMwY1x1YzZiMFx1Y2U1OCBTSVpFIiwib3B0aW9uX25hbWUiOiJcdWJiZjhcdWIyYzhTIiwib3B0aW9uX2luZGV4IjoyLCJvcHRpb25fcHJpY2UiOjB9LHsib3B0aW9uX3RpdGxlIjoiXHVjNzc0XHViMmM4XHVjMTVjIFx1YzE5MFx1Yzc5MFx1YzIxOCIsIm9wdGlvbl9uYW1lIjoiXHVjMTIwXHVkMGRkXHVkNTU4XHVjOWMwIFx1YzU0YVx1Yzc0YyIsIm9wdGlvbl9pbmRleCI6Mywib3B0aW9uX3ByaWNlIjowfSx7Im9wdGlvbl90aXRsZSI6Ilx1YjE3OFx1ZDJiOFx1YmQ4MSBcdWI0NTBcdWFlZDgiLCJvcHRpb25fbmFtZSI6Ilx1YjE3OFx1ZDJiOFx1YmQ4MSBcdWI0NTBcdWFlZDhcdWFjMDAgMH4xLjljbSBcdWMwYWNcdWM3NzQiLCJvcHRpb25faW5kZXgiOjQsIm9wdGlvbl9wcmljZSI6MH0seyJvcHRpb25fdGl0bGUiOiJcdWM4MWNcdWQ0ODggXHViYWE4XHViMzc4XHViYTg1IFx1ZDU0NFx1YzIxOCIsIm9wdGlvbl9uYW1lIjoiXHVjOGZjXHViYjM4IFx1YzY5NFx1Y2NhZFx1YzBhY1x1ZDU2ZFx1YzVkMCBcdWJhYThcdWIzNzhcdWJhODUgXHVhZjJkXHVjMzY4XHVjOGZjXHVjMTM4XHVjNjk0ISEiLCJvcHRpb25faW5kZXgiOjUsIm9wdGlvbl9wcmljZSI6MH0seyJvcHRpb25fdGl0bGUiOiJcdWFjZjVcdWM5YzBcdWQ2NTVcdWM3NzhcdWJkODBcdWQwYzFcdWI0ZGNcdWI4MjRcdWM2OTQhIiwib3B0aW9uX25hbWUiOiJcdWMwYzFcdWMxMzhcdWQzOThcdWM3NzRcdWM5YzAgXHViOWU4IFx1YzcxN1x1YWUwMCBcdWFmMmQgXHVjNzdkXHVjNWI0XHVjOGZjXHVjMTM4XHVjNjk0ISIsIm9wdGlvbl9pbmRleCI6Niwib3B0aW9uX3ByaWNlIjowfV0.oyIIat0AgBIUXuwqna2omHJvMcjhEYSN8uBEEJ_Btik"
													data-state="disabled"><input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][0][option_index]"
													value="0" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][0][option_title]"
													value="SIZE(-40%)" disabled=""> <input
													type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][0][option_name]"
													value="9~11인치(-40%)" disabled=""> <input
													type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][0][option_price]"
													value="-1400" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][1][option_index]"
													value="1" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][1][option_title]"
													value="색상" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][1][option_name]"
													value="인디핑크" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][1][option_price]"
													value="0" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][2][option_index]"
													value="2" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][2][option_title]"
													value="미니파우치 SIZE" disabled=""> <input
													type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][2][option_name]"
													value="미니S" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][2][option_price]"
													value="0" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][3][option_index]"
													value="3" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][3][option_title]"
													value="이니셜 손자수" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][3][option_name]"
													value="선택하지 않음" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][3][option_price]"
													value="0" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][4][option_index]"
													value="4" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][4][option_title]"
													value="노트북 두께" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][4][option_name]"
													value="노트북 두께가 0~1.9cm 사이" disabled=""> <input
													type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][4][option_price]"
													value="0" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][5][option_index]"
													value="5" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][5][option_title]"
													value="제품 모델명 필수" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][5][option_name]"
													value="주문 요청사항에 모델명 꼭써주세요!!" disabled=""> <input
													type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][5][option_price]"
													value="0" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][6][option_index]"
													value="6" disabled=""> <input type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][6][option_title]"
													value="공지확인부탁드려요!" disabled=""> <input
													type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][6][option_name]"
													value="상세페이지 맨 윗글 꼭 읽어주세요!" disabled=""> <input
													type="hidden"
													name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][option][6][option_price]"
													value="0" disabled="">
													<div class="table-layout">
														<div class="split">
															<span class="option-txt">SIZE(-40%) : 9~11인치(-40%)
																: -1,400원 / 색상 : 인디핑크 / 미니파우치 SIZE : 미니S / 이니셜 손자수 :
																선택하지 않음 / 노트북 두께 : 노트북 두께가 0~1.9cm 사이 / 제품 모델명 필수 : 주문
																요청사항에 모델명 꼭써주세요!! / 공지확인부탁드려요! : 상세페이지 맨 윗글 꼭 읽어주세요!</span>
															<div class="input-number" data-ui="input-number"
																data-state="disabled">
																<label
																	for="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][count]">수량</label>
																<button type="button" data-type="decrement">-</button>
																<div class="input-area">
																	<input class="prd-count"
																		name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][item][0][count]"
																		type="number" value="1" min="1" max="999"
																		autocomplete="off" data-product-price="25000"
																		data-option-price="-1400"
																		data-product-uuid="28873244-2929-4991-8f50-dc1c2d525958"
																		data-price="23600" data-status=""
																		data-status-value="0" data-action="change_count"
																		disabled="">
																</div>
																<button type="button" data-type="increment">+</button>
															</div>
														</div>
														<div class="split">
															<strong><em class="cost-text">옵션 변경</em></strong> <span
																class="btn-group">
																<button class="ui_btn--small" type="button"
																	data-modal-trigger="edit-prd-options" data-modal="open"
																	data-action="edit_option"
																	data-product-uuid="28873244-2929-4991-8f50-dc1c2d525958">
																	<i class="ui_icon--cogwheel"></i>
																</button>
																<button class="ui_btn--small" type="button"
																	data-action="remove_parent"
																	data-product-uuid="28873244-2929-4991-8f50-dc1c2d525958"
																	data-cart-index="0">
																	<i class="ui_icon--close"></i>
																</button>
															</span>
														</div>
													</div></li>
											</ul>
											<div class="ui_field--onchange" data-uipack="textarea"
												data-state="disabled">
												<div class="ui_field__txtarea">
													<textarea
														name="cart_list[28873244-2929-4991-8f50-dc1c2d525958][message]"
														maxlength="500" placeholder="주문 요청사항을 입력해주세요" disabled=""></textarea>
													<em class="ui_field__chars">500</em>
												</div>
												<button type="submit" class="ui_field__btn"
													data-action="reload">저장</button>
											</div>
										</td>
									</tr>
									<tr class="static-row delivery-segment">
										<th>배송비</th>
										<td>
											<div data-freeship="true" style="display: none">
												<input type="text" name="shipping_price" data-unformated="0"
													value="무료 배송" readonly="" disabled=""> <input
													type="text" style="display: none"
													name="free_shipping_policy" data-unformated="50000"
													value="50,000원 이상" readonly="" disabled="">
											</div>

											<div data-freeship="false">
												<input type="text" name="shipping_price"
													data-unformated="3000" value="3,000 원" readonly=""
													disabled=""> <input style="display: none"
													type="text" name="free_shipping_policy"
													data-unformated="50000" value="50,000원 이상" readonly=""
													disabled="">
											</div> <input type="text" name="remote_shipping_price"
											data-unformated="3000" value="3,000원" readonly="" disabled="">
											<div data-vip="true"
												style="border: 1px solid #f00; display: none">
												<span>VIP 클럽 적용</span> <span>- 3,000원</span>
											</div>
										</td>
									</tr>

								</tbody>
							</table>
							<table class="cartCard" data-ui="cart-card" data-ui-type="cart"
								data-artist-status="1"
								data-artist-uuid="f43279e2-becd-435f-b07c-746806a0e714"
								data-artist-name="VOW F&amp;B (바우에프앤비)" data-delivery-style="0"
								data-delivery-policy="50000" data-delivery-charge="4000"
								data-delivery-charge-remote="3000">
								<caption class="hidden">VOW F&amp;B (바우에프앤비) 작가님</caption>
								<colgroup>
									<col width="125px">
									<col width="*">
								</colgroup>
								<thead>
									<tr>
										<th colspan="2"><input type="hidden"
											name="membership_price" disabled="" value=""> <input
											type="hidden" name="artist_order_price" disabled="" value="">
											<input type="hidden" name="delivery_charge" disabled=""
											value="4000"> <label> <input class="bp"
												data-ui="check-linked"
												data-linked-parent="f43279e2-becd-435f-b07c-746806a0e714"
												type="checkbox" checked="">
												<div class="txt-group">
													<b class="bold">VOW F&amp;B (바우에프앤비) 작가님</b> <em
														class="light">50,000원 이상 배송비 무료</em>
												</div>
										</label></th>
									</tr>
								</thead>
								<tbody>

									<tr class="list-item"
										data-cart-uuid="e80b9667-c716-4f31-9dd3-19aacfc3a482"
										data-product-uuid="93b42a84-ef55-4f32-9c56-0891169c3287">


										<td class="area-img"><input class="bp"
											data-ui="check-linked"
											data-linked-child="f43279e2-becd-435f-b07c-746806a0e714"
											type="checkbox" data-action="disable_inputs" checked="">
											<div class="img-bg"
												style="background-image: url(https://image.idus.com/image/files/3568101ae8d6434d815a4de5bfe2723b_512.jpg)"></div>
										</td>

										<td class="area-txt">
											<div class="txt-group">
												<a class="bold"
													href="/w/product/93b42a84-ef55-4f32-9c56-0891169c3287"
													target="_blank"> <label for="prd-name">벚꽃엔딩밀크티(350ml)</label>
												</a> <em class="light">주문시 제작</em>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2" class="flexible">
											<ul class="list-options">
												<li data-artist-uuid="f43279e2-becd-435f-b07c-746806a0e714"
													data-option-status="1"
													data-key="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.W3sib3B0aW9uX3RpdGxlIjoiXHVjNmIwXHVjNzIwXHVjMTIwXHVkMGRkIiwib3B0aW9uX25hbWUiOiJcdWM3N2NcdWJjMThcdWM2YjBcdWM3MjAiLCJvcHRpb25faW5kZXgiOjAsIm9wdGlvbl9wcmljZSI6MH1d.WbvIlEz_VBqbJf0ALFSN-ltyNab2oBFx-54tuAYX5hs"
													data-state=""><input type="hidden"
													name="cart_list[93b42a84-ef55-4f32-9c56-0891169c3287][item][0][option][0][option_index]"
													value="0"> <input type="hidden"
													name="cart_list[93b42a84-ef55-4f32-9c56-0891169c3287][item][0][option][0][option_title]"
													value="우유선택"> <input type="hidden"
													name="cart_list[93b42a84-ef55-4f32-9c56-0891169c3287][item][0][option][0][option_name]"
													value="일반우유"> <input type="hidden"
													name="cart_list[93b42a84-ef55-4f32-9c56-0891169c3287][item][0][option][0][option_price]"
													value="0">
													<div class="table-layout">
														<div class="split">
															<span class="option-txt">우유선택 : 일반우유</span>
															<div class="input-number" data-ui="input-number"
																data-state="">
																<label
																	for="cart_list[93b42a84-ef55-4f32-9c56-0891169c3287][item][0][count]">수량</label>
																<button type="button" data-type="decrement">-</button>
																<div class="input-area">
																	<input class="prd-count"
																		name="cart_list[93b42a84-ef55-4f32-9c56-0891169c3287][item][0][count]"
																		type="number" value="1" min="1" max="999"
																		autocomplete="off" data-product-price="15000"
																		data-option-price="0"
																		data-product-uuid="93b42a84-ef55-4f32-9c56-0891169c3287"
																		data-price="15000" data-status="1"
																		data-status-value="1" data-action="change_count">
																</div>
																<button type="button" data-type="increment">+</button>
															</div>
														</div>
														<div class="split">
															<strong><em class="cost-text">15,000원</em></strong> <span
																class="btn-group">
																<button class="ui_btn--small" type="button"
																	data-modal-trigger="edit-prd-options" data-modal="open"
																	data-action="edit_option"
																	data-product-uuid="93b42a84-ef55-4f32-9c56-0891169c3287">
																	<i class="ui_icon--cogwheel"></i>
																</button>
																<button class="ui_btn--small" type="button"
																	data-action="remove_parent"
																	data-product-uuid="93b42a84-ef55-4f32-9c56-0891169c3287"
																	data-cart-index="0">
																	<i class="ui_icon--close"></i>
																</button>
															</span>
														</div>
													</div></li>
											</ul>
											<div class="ui_field--onchange" data-uipack="textarea"
												data-state="">
												<div class="ui_field__txtarea">
													<textarea
														name="cart_list[93b42a84-ef55-4f32-9c56-0891169c3287][message]"
														maxlength="500" placeholder="주문 요청사항을 입력해주세요"></textarea>
													<em class="ui_field__chars">500</em>
												</div>
												<button type="submit" class="ui_field__btn"
													data-action="reload">저장</button>
											</div>
										</td>
									</tr>
									<tr class="static-row delivery-segment">
										<th>배송비</th>
										<td>
											<div data-freeship="true" style="display: none">
												<input type="text" name="shipping_price" data-unformated="0"
													value="무료 배송" readonly="" disabled=""> <input
													type="text" style="display: none"
													name="free_shipping_policy" data-unformated="50000"
													value="50,000원 이상" readonly="" disabled="">
											</div>

											<div data-freeship="false">
												<input type="text" name="shipping_price"
													data-unformated="4000" value="4,000 원" readonly=""
													disabled=""> <input style="display: none"
													type="text" name="free_shipping_policy"
													data-unformated="50000" value="50,000원 이상" readonly=""
													disabled="">
											</div> <input type="text" name="remote_shipping_price"
											data-unformated="3000" value="3,000원" readonly="" disabled="">
											<div data-vip="true"
												style="border: 1px solid #f00; display: none">
												<span>VIP 클럽 적용</span> <span>- 4,000원</span>
											</div>
										</td>
									</tr>

								</tbody>
							</table>
						</div>
						<div class="mobile-row inner-w800">
							<div class="check-control">
								<label> <input class="bp" type="checkbox"
									data-ui="check-all"
									data-target-selector="[data-ui=&quot;check-linked&quot;]"
									checked=""> <span> 전체선택(<em
										data-ui="check-all-count">1</em>/<em class="check-count-total">2</em>)
								</span>
								</label>
								<button class="ui_btn--mini" type="button"
									data-action="delete_checked_list"
									data-endpoint="/w/cart/delete">선택 삭제</button>
							</div>

							<div class="border-wrapper">
								<table class="table-cost">
									<thead>
										<tr>
											<th><span>총 주문 금액</span></th>
											<th><span>배송비</span></th>
											<th class="optional"><span>작가님 할인 혜택</span></th>
											<th><span>결제 예정금액</span></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><strong data-payment="order">15,000</strong>원</td>
											<td><span>+</span><strong data-payment="shipping">4,000</strong>원</td>
											<td class="optional"><span>-</span><strong
												data-payment="artist_coupon">0</strong>원</td>
											<td class="hilight red"><span>=</span><strong
												data-payment="cart_total">19,000</strong>원</td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="scroll-detector" data-ui="sticky">
								<fieldset class="area-btn mfixed">
									<button type="button" class="ui_btn--red--large"
										data-type="payment-all">전체 작품 주문</button>
									<button type="button" class="ui_btn--large submit"
										data-type="payment">선택한 작품 주문</button>
								</fieldset>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</section>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>

</body>
</html>