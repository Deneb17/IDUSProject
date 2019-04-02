<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<div class="container">
		<!--위쪽 헤더-->
		<div class="upper header">
			<!-- 로고 -->
			<div class="logo">
				<h1>
					<a href="${pageContext.request.contextPath}/">idus</a>
				</h1>
			</div>
			<!-- 검색 창 -->
			<div class="searchBar">
				<input type="search" class="search" name="search" placeholder="작품, 작가 명을 입력 하세요.">
				<button class="searchBtn" type="button">검색</button>
			</div>
			<!-- 사용자 프로필 -->
			<div class="profile">
				<span class="userName">${auth.nickName} 님</span>
				<!-- 마이페이지로 이동 -->
				<a href="${pageContext.request.contextPath}/myPage" title="마이 페이지"> 
					<img class="thumbnail" src="${auth.thumbnail}" alt="썸네일" />
				</a>
				<!-- 장바구니로 이동 --> 
				<a href="${pageContext.request.contextPath}/myPage/shoppingBag" title="장바구니">
					<i class="fas fa-shopping-cart" class="icon"></i>
				</a>
				<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</div>
		</div>
		<div class="lower header">
			<nav>
				<ul class="gnb">
					<li><a href="${pageContext.request.contextPath}/">메인</a></li>
					<li><a href="${pageContext.request.contextPath}/list/recent">최신 작품</a></li>
					<li><a href="${pageContext.request.contextPath}/">인기 작품</a></li>
					<li><a href="${pageContext.request.contextPath}/">인기 작가</a></li>
					<li><a href="${pageContext.request.contextPath}/blog/${auth.memberNo}">공방</a></li>
					<li><a href="${pageContext.request.contextPath}/">문의</a></li>
				</ul>
			</nav>
		</div>
	</div>
</header>