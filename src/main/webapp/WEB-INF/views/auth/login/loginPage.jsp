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
			<div class="mainText login">
				
				<!-- 로그인 form -->
				<form class="loginForm" id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
					
					<!-- 이메일 입력란 -->
					<input type="text" id="email" name="email" value="${loginRequest.email}" placeholder="이메일" />
					<!-- 이메일 오류 메세지 출력 -->
					<span id="emailValidationState" class="errorMsg"></span>
					
					<!-- 비밀번호 입력란 -->
					<input type="password" id="password" name="password" placeholder="비밀번호" />
					<!-- 비밀번호 오류 메세지 출력 -->
					<span id="passwordValidationState" class="errorMsg"></span>
					
					<!-- 로그인 유지 checkbox -->
					<label>
						<input type="checkbox" id="rememberMe" name="rememberMe" value="true" /> 
						로그인 유지하기
					</label>
					
					<!-- 서버에서 처리 도중 email, 비밀번호가 다를경우 -->
					<c:if test="${ errors.hasErrors() }">
						<div id="loginFailMsg" class="errorMsg">
							<span style="color: red">아이디 혹은 비밀번호를 다시한번 확인하세요. <br/> idus에
								등록되지 않은 아이디 이거나 비밀번호가 잘못되었습니다.
							</span>
						</div>
					</c:if>
					
					<!-- 일반회원 로그인 버튼 -->
					<button type="button" onclick="login()">로그인</button>
					
					
					<div>
						<!-- 네이버 로그인 버튼 -->
						<span id="naver_id_login"></span>
						<!-- 카카오 로그인 버튼 -->
						<span id="kakao-login-btn"></span>
					</div>
					<a href="">아이디, 혹은 비밀번호를 잊으셨나요?</a>
				</form>
			
			</div>
		</div>
	</section>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>
	
	<!-- 회원가입 양식 검사 -->
	<script src="${pageContext.request.contextPath}/resources/js/loginFormValidation.js"></script>
	
	<!-- 네이버 로그인 API -->
	<script src="${pageContext.request.contextPath}/resources/js/naverLogin_implicit-1.0.3-min.js"></script>
	<!-- 네이버 로그인 -->
	<script src="${pageContext.request.contextPath}/resources/js/naverLogin.js"></script>
	<!-- 카카오 로그인 api -->
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<!-- 카카오 로그인 -->
	<script src="${pageContext.request.contextPath}/resources/js/kakaoLogin.js"></script>
	
</body>
</html>