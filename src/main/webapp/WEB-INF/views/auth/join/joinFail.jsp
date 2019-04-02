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
				<h1>회원가입 실패</h1>
				<h3>알 수 없는 이유로 회원가입에 실패하였습니다. 관리자에게 문의 바랍니다.</h3>
				<a href="${pageContext.request.contextPath}/">[ 메인으로 이동 ]</a>
			</div>
		</div>
	</section>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>
</body>
</html>