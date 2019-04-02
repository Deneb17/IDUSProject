<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/commons/meta.jsp"%>
</head>
<body>

	<!-- header -->
	<c:if test="${!empty auth}">
		<%@ include file="/WEB-INF/views/commons/loginHeader.jsp"%>
	</c:if>
	<c:if test="${empty auth}">
		<%@ include file="/WEB-INF/views/commons/noLoginHeader.jsp"%>
	</c:if>

	<section>
		<div class="container">
			<div class="sectionHeader">
				<h1>작품 목록</h1>
				<hr>
				<p>좋아하는 작가의 작품을 만나보세요!</p>
			</div>

			<div class="atelier">
				<!-- 좌측 네비게이션 -->
				<div class="leftNav">
					<!-- 프로필 -->
					<div class="blogProfile" onclick="window.location='${pageContext.request.contextPath}/blog/${artist.artistNo}'" style="cursor: pointer">
						<!-- 프로필 사진 : 블로그 주인 프로필 이미지 받아오기 -->
						<div class="profileImage"
							style="background-image: url(${artist.thumbnail})"></div>
						<!-- 작가 이름 -->
						<div class="name">
							<h3>${artist.nickName}</h3>
						</div>
						<!-- 자기소개 -->
						<div class="introduction">${artist.introduction}</div>
					</div>

					<c:if test="${artist.isManager()}">
						<!-- 자신 소유 블로그 일때 보이는 메뉴 -->
						<div class="loginMenu">
							<ul>
								<li class="menubtn"><a
									href="${pageContext.request.contextPath}/blog/addPiece">상품
										등록</a></li>
								<li class="menubtn"><a
									href="${pageContext.request.contextPath}/blog/writePost">포스트
										작성</a></li>
								<li class="menubtn"><a href="">나의 주문 관리</a></li>
							</ul>
						</div>
					</c:if>

					<!-- 일반 메뉴 -->
					<div class="generalMenu">
						<ul>
							<c:if test="${!artist.isManager() && !empty auth}">
								<!-- 타인의 공방에만 보이는 메뉴 -->
								<li class="menubtn"><a href="">즐겨찾기 추가</a></li>
							</c:if>
							<!-- 공통 메뉴 -->
							<li class="menubtn"><a href="${pageContext.request.contextPath}/blog/${artist.artistNo}/pieceList">상품보기</a></li>
							<li class="menubtn"><a href="${pageContext.request.contextPath}/blog/${artist.artistNo}/postList">포스트 보기</a></li>
						</ul>
					</div>
					<!-- 공방 정보 -->
					<div class="info">
						<h3>활동 정보</h3>
						<p>팔로우 : ${artist.follower}</p>
					</div>
				</div>

				<!-- 우측 -->
				<div class="blogContent">
					<h3>판매중인 작품</h3>
					<!-- 상품 이름으로 작품 검색 -->
					<form action="${pageContext.request.contextPath}/blog/${artist.artistNo}/pieceList" method="get" class="pieceSearch">
						<input type="search" placeholder="작가의 작품 내 검색" name="pieceName">
						<button type="submit">검색</button>
					</form>
					<hr>
					<div class="pieceContainer" id="target">
						<!-- for문으로 상품 카드 반복해서 뿌림 -->
						<c:forEach var="piece" items="${pieceList}">
							<!-- 상품 카드 클릭 시 상세 페이지로 이동 -->
							<div class="piece" onclick="pieceDetail(${piece.pieceNo})">
								<div class="pieceImage" style="background-image: url(${piece.thumbnail})"></div>
								<h4 class="nickName">${piece.artist}</h4>
								<p class="title">${piece.title}</p>
								<p class="price">
									<span class="discount">[${piece.discount}%] 
										<fmt:formatNumber value="${(1-piece.discount*0.01)*piece.price}" type="currency"/>
									</span> 
									<span class="originalPrice"><fmt:formatNumber value="${piece.price}" type="currency"/></span>
								</p>
								<hr>
								<span style="color: gold" class="star">
									<!-- for문 -->
									<c:forEach var="i" begin="1" end="${piece.star}" step="1">
										<i class="fas fa-star"></i> <!-- 꽉 찬 별 -->
									</c:forEach>
									<c:forEach var="i" begin="1" end="${5-piece.star}" step="1">
										<i class="far fa-star"></i> <!-- 빈 별 -->
									</c:forEach>
								</span>
							</div>
						</c:forEach>
						<c:if test="${empty pieceList}">
							<p style="color:#ccc;">등록된 상품이 존재하지 않습니다.</p>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	
	<script>
		
		var pieceName = "${pieceName}";
	
		function pieceDetail(pieceNo) {
			window.location = "${pageContext.request.contextPath}/blog/detail/" + pieceNo;
		}
		
		// 작가번호 -> ajax에 사용됨
		var artistNo = ${artist.artistNo};
	</script>
	
	<script src="${pageContext.request.contextPath}/resources/js/blogPieceList.js"></script>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>
	
</body>
</html>