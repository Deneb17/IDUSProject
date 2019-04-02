<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  1. header section  -->
<header>
	<div class="container">
		<!-- 위쪽 헤더 -->
		<div class="upper header">
			<!-- 로고 -->
			<div class="logo">
				<h1>
					<a href="${pageContext.request.contextPath}/">idus</a>
				</h1>
			</div>
			<!-- 검색 창 -->
			<div class="searchBar">
				<input type="text" class="search" name="search" placeholder="작품, 작가 명을 입력 하세요.">
				<button class="searchBtn" type="button">검색</button>
			</div>
			<!-- 사용자 프로필 -->
			<div class="profile">
				<a href="${pageContext.request.contextPath}/login">로그인</a>
				<a href="${pageContext.request.contextPath}/join">회원가입</a>
			</div>
		</div>
		<div class="lower header">
			<nav>
				<ul class="gnb">
					<li><a href="${pageContext.request.contextPath}/">메인</a></li>
					<li><a href="${pageContext.request.contextPath}/">최신 작품</a></li>
					<li><a href="${pageContext.request.contextPath}/">인기 작품</a></li>
					<li><a href="${pageContext.request.contextPath}/">인기 작가</a></li>
					<li><a href="${pageContext.request.contextPath}/">문의</a></li>
				</ul>
			</nav>
		</div>
	</div>
</header>