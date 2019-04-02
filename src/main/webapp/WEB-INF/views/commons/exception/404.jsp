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
				<h1>404 error</h1>
				<h3>요청하신 페이지를 찾을 수 없습니다. <br /> 정상적인 접근이 아니거나 <br /> 서버에 문제가 있을 수 있습니다.</h3>
				<p>이런 현상이 지속적으로 발생 할 경우, 관리자에게 문의 해 주세요.</p>
				<a href="${pageContext.request.contextPath}/" class="button">메인으로 이동</a>
			</div>
		</div>
	</section>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>
</body>
</html>