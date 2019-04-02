<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/commons/meta.jsp"%>

<meta charset="UTF-8">
<title>주문 정보</title>
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
				<div class="blogContent myOrderTable">
					<table summary bgcolor="#d1d1d1" width="756" id="orderTable">
						<colgroup>
							<col width="12%">
							<col width="17%">
							<col width="10%">
							<col width="30%">
							<col width="21%">
							<col width="10%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" class="OrderInfo">주문일</th>
								<th scope="col" class="OrderInfo">주문번호</th>
								<th scope="col" class="OrderInfo">수령인</th>
								<th scope="col" class="OrderInfo">주문상품</th>
								<th scope="col" class="OrderInfo">조회</th>
								<th scope="col" class="OrderInfo">비고</th>
							</tr>

						</thead>
						<tbody>
							<tr>
								<td colspan="6"><br>
								<br>
								<br>
								<br>
								<br></td>
							</tr>

							<tr>
								<td colspan="6"><h1 id="myOrderFail">현재 주문 정보가 존재 하지
										않습니다</h1></td>
							</tr>
						</tbody>


					</table>
				</div>
			</div>
		</div>
	</section>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>

</body>
</html>