<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 수정 완료!</h1>
	<p>${auth.name} 님의 회원 정보를 성공적으로 수정 하였습니다.</p>
	<ul>
		<li><a href="${pageContext.request.contextPath}/">[ 메인 페이지 ]</a></li>
		<li><a href="${pageContext.request.contextPath}/myPage">[ 마이 페이지 ]</a></li>
	</ul>
</body>
</html>