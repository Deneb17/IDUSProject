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

	<section>
		<div class="container centerText">

			<div class="top">
				<div class="logo">
					<h1 class="title">idus</h1>
				</div>
				<div class="subLogo">
					<h2 class="subTitle">- 정말 간단한 회원가입 하기 -</h2>
				</div>
			</div>

			<div class="bottom">
				<div class="subLogo">
					<h3>가입 정보 입력하기</h3>
					<p><span style="color: var(--red);">*</span> 항목은 필수입니다.</p>
				</div>
				<div>
					<!-- 회원가입 양식 -->
					<form class="joinForm" id="joinForm"
						action="${pageContext.request.contextPath}/join" method="post"
						enctype="multipart/form-data">
						
						<!-- 이메일 -->
						<div class="input">
							<label for="email" class="input-display-block">
								이메일 <span style="color: var(--red);">*</span>
							</label>
							<!-- 이메일 입력란 -->
							<input type="text" id="email" name="email" />
							<!-- 이메일 중복 확인 버튼 -->
							<button class="button" type="button" onclick="emailDuplicationCheck()">중복 확인</button>
							<!-- 이메일 상태 메세지 출력란 -->
							<span class="errorMsg" id="emailValidationState"></span>
						</div>

						<!-- 비밀번호 -->
						<div class="input">
							<label for="password" class="input-display-block">
								비밀번호 <span style="color: var(--red);">*</span>
							</label>
							<!-- 비밀번호 입력란 -->
							<input class="input-max" type="password" id="password" name="password" /> 
							<!-- 비밀번호 상태 메세지 출력란 -->
							<span class="errorMsg" id="passwordValidationState"></span>
						</div>
						
						<!-- 비밀번호 확인 -->
						<div class="input">
							<label for="confPassword" class="input-display-block">
								비밀번호 확인<span style="color: var(--red);">*</span>
							</label>
							<!-- 확인용 비밀번호 입력란 -->
							<input class="input-max" type="password" id="confPassword" />
							<!-- 확인용 비밀번호 상태 메세지 출력란 -->
							<span class="errorMsg" id="confPasswordValidationState"></span>
						</div>
						
						<!-- 이름 -->
						<div class="input">
							<label for="name" class="input-display-block">
							이름 <span style="color: var(--red);">*</span>
							</label>
							<!-- 이름 입력란 -->
							<input class="input-max" type="text" id="name" name="name" />
							<!-- 이름 상태 메세지 출력란 -->
							<span class="errorMsg" id="nameValidationState"></span>
						</div>
						
						<!-- 닉네임 -->
						<div class="input">
							<label for="nickName" class="input-display-block">
							별명 <span style="color: var(--red);">*</span>
							</label>
							<!-- 닉네임 입력란 -->
							<input type="text" id="nickName" name="nickName" placeholder="상호명으로 사용됩니다."/>
							<!-- 닉네임 중복 확인 버튼 -->
							<button class="button" type="button" onclick="nickNameDuplicationCheck()">중복 확인</button>
							<!-- 닉네임 상태 메세지 출력란 -->
							<span class="errorMsg" id="nickNameValidationState"></span>
						</div>
						
						<!-- 전화번호 -->
						<div class="input">
							<label for="phone" class="input-display-block">
								전화번호 <span style="color: var(--red);">*</span>
							</label>
							<!-- 전화번호 입력란 -->
							<input class="input-max" type="text" id="phone" name="phone" />
							<!-- 전화번호 상태 메세지 출력란 -->
							<span class="errorMsg" id="phoneValidationState"></span>
						</div>
						
						<!-- 주소 -->
						<div class="input">
							<label for="">
								주소 <span style="color: var(--red);">*</span>
							</label>
							<!-- 우편번호 입력란 -->
							<input type="text" id="zoneCode" name="zoneCode" readonly="readonly">
							<!-- 주소 찾기 버튼 -->
							<button class="button" type="button" onclick="daumPost()">주소 찾기</button>
							<!-- 주소란 -->
							<input class="input-max" type="text" id="address" name="address" readonly="readonly">
							<!-- 상세 주소 입력란 -->
							<input class="input-max" type="text" id="detailAddress" name="detailAddress">
							<!-- 주소 상태 메세지 출력란 -->
							<span class="errorMsg" id="detailAddressValidationState"></span>
						</div>
						
						<!-- 자기소개 -->
						<div class="input">
							<label for="introduction">자기 소개</label>
							<!-- 자기소개 입력란 -->
							<textarea class="introduction" id="introduction" name="introduction" placeholder="100자까지 입력 가능합니다."></textarea>
						</div>
						
						<!-- 썸네일 -->
						<div class="input">
							<label for="thumbnail">썸네일</label>
							<!-- 썸네일 이미지 입력창 -->
							<input class="input-max" type="file" id="thumbnail" name="thumbnail" /><br />
							<!-- 썸네일 상태 메세지 출력란 -->
							<span class="errorMsg" id="thumbnailValidationState"></span> <br />
						</div>
						
						<!-- 약관 -->
						<div class="input">
							<label><input class="checkBox" type="checkbox" id="totalAgree"> 전체 동의</label>
							<hr>
							<label><input class="checkBox" type="checkbox" id="agree1"> 이용약관 동의</label> 
							<label><input class="checkBox" type="checkbox" id="agree2"> 개인정보 취급방침 동의</label>
							<label><input class="checkBox" type="checkbox" id="agree3"> 이벤트 알림 받기(선택)</label>
							<hr>
						</div>
						
						<!-- 가입 버튼 -->
						<button class="button joinbtn" type="button" onclick="join()">가 입</button>
					</form>
					
				</div>
			</div>

		</div>
	</section>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/joinFormValidation.js"></script>

	<!--다음 주소 라이브러리-->
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/juso.js"></script>
</body>
</html>