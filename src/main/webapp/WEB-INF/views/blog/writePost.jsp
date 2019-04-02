<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/commons/meta.jsp"%>
	<!-- summernote css -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.css" rel="stylesheet">
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
			<div class="sectionHeader">
				<h1>포스팅</h1>
				<hr>
				<p>자신만의 스토리를 전해보세요!</p>
			</div>
			<div class="sectionContent">
				<h3>
					<span style="color: var(--red);">*</span> 항목은 필수입니다.
				</h3>
				<!-- 포스트 등록 -->
				<form action="${pageContext.request.contextPath}/blog/writePost" method="post" id="postForm">
					<!-- 제목 -->
					<label> 1. 제목 <span style="color: var(--red);">*</span><br>
						<input type="text" id="title" name="title" />
					</label>
					
					<!-- 포스트 내용 -->
					<label> 2. 포스트 내용 <span style="color: var(--red);">*</span> <br><br>
						<textarea id="content" name="content"></textarea>
					</label>
					
					<button class="subBtn" type="button" onclick="writePost()">포스팅</button>
				</form>
			</div>
		</div>
	</section>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>
	
	<!-- summernote api -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/writePost.js"></script>
	
</body>
</html>