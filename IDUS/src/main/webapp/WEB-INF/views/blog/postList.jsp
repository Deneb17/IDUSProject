<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/commons/meta.jsp"%>
</head>
<body>
	
	<!-- header -->
	<c:if test="${!empty auth}">
		<%@ include file="/WEB-INF/views/commons/loginHeader.jsp" %>
	</c:if>
	<c:if test="${empty auth}">
		<%@ include file="/WEB-INF/views/commons/noLoginHeader.jsp" %>
	</c:if>
	
	<section>
		<div class="container">
			<!-- 헤더 -->
			<div class="sectionHeader">
				<h1>포스트</h1>
				<hr>
				<p>작가님의 최근 소식을 접하고 소통 해 보세요!</p>
			</div>
			
			<!-- 블로그 내용 -->
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
							<li class="menubtn"><a href="">포스트 보기</a></li>
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
					<h3>포스트</h3>
					<!-- 상품 이름으로 작품 검색 -->
					<form action="${pageContext.request.contextPath}/blog/postList" method="get" class="pieceSearch">
						<input type="search" placeholder="블로그 내 검색" name="title">
						<button type="submit">검색</button>
					</form>
					<hr>
					<div class="postContainer">
						<!-- 출력할 포스트가 존재 할 경우 -->
						<c:if test="${!empty postList}">
							<table>
								<colgroup>
									<col span="1" style="width: 60%;">
									<col span="1" style="width: 20%">
									<col span="1" style="width: 20%;">
								</colgroup>
								<thead>
									<tr>
										<th>제목</th>
										<th>작성자</th>
										<th>작성시간</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="post" items="${postList}">
										<tr onclick="viewPost(${post.postNo})">
											<td>${post.title}</td>
											<td>${post.nickName}</td>
											<td>${post.createdDate}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<span>
								<!-- 이전 항목이 존재 할 경우 -->
								<c:if test="${page.hasBefore()}">
									<button onclick="changePage(-1)">&lt;&lt;</button>
								</c:if>
								<c:forEach var="pageNo" items="${page.list}">
									<button onclick="getPage(${pageNo})">${pageNo}</button>
								</c:forEach>
								<!-- 다음 항목이 존재 할 경우 -->
								<c:if test="${page.hasNext()}">
									<button onclick="changePage(1)">&gt;&gt;</button>
								</c:if>
							</span>
						</c:if>
						<!-- 출력할 포스트가 없을 경우 -->
						<c:if test="${empty postList}">
							<p style="color: #ccc;">등록된 포스트가 없습니다.</p>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<script>
		// 현재 페이지
		var currentPage = ${page.currentPage};
		
		function getPage(pageNo) {
			window.location = "${pageContext.request.contextPath}/blog/" + ${artist.artistNo} + "/postList?pageNo=" + pageNo;
		}
		
		function changePage(index) {
			currentPage += index;
			window.location = "${pageContext.request.contextPath}/blog/" + ${artist.artistNo} + "/postList?currentPage=" + currentPage;
		}
	</script>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>
	
</body>
</html>