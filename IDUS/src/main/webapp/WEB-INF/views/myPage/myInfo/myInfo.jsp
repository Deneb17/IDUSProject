<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="/WEB-INF/views/commons/meta.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/myInfo.css">

<meta charset="UTF-8">
<title>내 정보 수정</title>
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
					<div>
						<div>
							<h1>개인 정보 수정</h1>
							<form action="${pageContext.request.contextPath}/myPage/myInfo"
								method="post" id="MyInfoBox">

								<div class="row">
									<div class="col-25">
										<label for="email">이메일 :</label>
									</div>
									<div class="col-75">
										<input type="text" id="email" name="email"
											value="${informationModifyRequest.email}" readonly>
									</div>
								</div>

								<div class="row">
									<div class="col-25">
										<label for="password">비밀번호 :</label>
									</div>
									<div class="col-75">
										<input type="password" id="password" name="password">
									</div>
								</div>

								<div class="row">
									<div class="col-25">
										<label for="confPassword">비밀번호 확인 :</label>
									</div>
									<div class="col-75">
										<input type="password" id="confPassword" name="confPassword">
									</div>
								</div>

								<div class="row">
									<div class="col-25">
										<label for="name">이름 :</label>
									</div>
									<div class="col-75">
										<input type="text" id="name" name="name"
											value="${informationModifyRequest.name}">
									</div>
								</div>

								<div class="row">
									<div class="col-25">
										<label for="phone">전화 :</label>
									</div>
									<div class="col-75">
										<input type="text" id="phone" name="phone"
											value="${informationModifyRequest.phone}">
									</div>
								</div>

								<div class="row">
									<div class="col-25">
										<label for="address">주소 :</label>
									</div>
									<div class="col-75">
										<input type="text" id="address" name="address"
											value="${informationModifyRequest.address}">
									</div>
								</div>

								<div class="row">
									<div class="col-25">
										<label for="subject">자기소개 :</label>
									</div>
									<div class="col-75">
										<textarea id="introduction" name="introduction"
											placeholder="Write something.." style="height: 200px">${informationModifyRequest.introduction}</textarea>
									</div>
								</div>

								<div class="infobtn">
									<button id="Infobtn-r" type="submit">수정</button>
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>

</body>

</html>
