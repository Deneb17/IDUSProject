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
		<%@ include file="/WEB-INF/views/commons/loginHeader.jsp"%>
	</c:if>
	<c:if test="${empty auth}">
		<%@ include file="/WEB-INF/views/commons/noLoginHeader.jsp"%>
	</c:if>
	
	<!--background-->
	<div class="bgImage"></div>

	<section class="transparent">
		<div class="container heroSection">
			<div class="mainText">
				<h1>idus</h1>
				<h3>아이디어스에서 <br /> 수많은 작가들과 함께 하세요</h3>
				<p>아이디어스는 수제 상품 전문 쇼핑몰 입니다. <br />
				 다양한 작가들이 활동하고 있는 아이디어스에서 
				 당신의 입맛에 꼭 맞는 수제 상품을 경험 해 보세요.</p>
				<c:if test="${!empty auth}">
					<a href="${pageContext.request.contextPath}/join" class="button">최신 작품 둘러보기</a>
				</c:if>
				<c:if test="${empty auth}">
					<a href="${pageContext.request.contextPath}/join" class="button">가입하기</a>
				</c:if>
			</div>
		</div>
	</section>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>

</body>
</html>