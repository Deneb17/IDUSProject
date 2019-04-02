<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/commons/meta.jsp"%>
<title>마이 페이지</title>
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
					<!-- 타이틀 바 -->
					<div class="title-bar">
						<h1 class="txt">MY정보</h1>
					</div>
					<!-- 나의 정보 -->
					<ul class="dashboard-list">
						<li><strong class="label">Point</strong><a>0P</a></li>
						<li><strong class="label">배송 중</strong><a>0</a></li>
						<li><strong class="label">배송 완료</strong><a>0</a></li>
						<li><strong class="label">취소 / 환불</strong><a>0</a></li>
					</ul>
					<!-- 최근 주문 내역 -->
					<div class="sub-title">
						<h2 class="txt">최근 주문내역</h2>
						<a href="${pageContext.request.contextPath}/myPage/myOrders">
							더보기 ></a>
					</div>
					<p class="empty-msg">최근 1개월 이내에 주문한 작품이 없습니다</p>
				</div>
			</div>
		</div>
	</section>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>

</body>
</html>