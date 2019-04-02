<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<!-- 네이버 로그인 API -->
	<script src="${pageContext.request.contextPath}/resources/js/naverLogin_implicit-1.0.3-min.js"></script>
	<!-- jQuery -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<p>please wait....</p>
	
	<script>
	
		// 네이버로 부터 발급 받은 로그인 API의 clientID
		var clientId = "lrTa2NvPev7CUD6_wzDG";
		// 로그인 처리 후 돌아갈 url
		var callBackUrl = "http://localhost:8080/idus/login/naver";
		
		var naverLogin = new naver_id_login(clientId, callBackUrl);
		
		// 네이버 로그인 객체
		// console.log(naverLogin);
		
		// 네이버 로그인 access tocken
		// console.log(naverLogin.oauthParams.access_token);
		
		// 로그인 한 사용자의 프로필을 처리 할 callback 함수 등록
		naverLogin.get_naver_userprofile("profileCallback()");
		
		function profileCallback() {
			
			var id = encodeURIComponent(naverLogin.getProfileData("id"));
			var email = encodeURIComponent(naverLogin.getProfileData("email"));
			var name = encodeURIComponent(naverLogin.getProfileData("name"));
			var nickName = encodeURIComponent(naverLogin.getProfileData("nickname"));
			var thumbnail = encodeURIComponent(naverLogin.getProfileData("profile_image"));
			
			var ajax = new XMLHttpRequest();
			
			ajax.open("post", "${pageContext.request.contextPath}/login/naver", true);
			
			ajax.onreadystatechange = function() {
				
				var readyState = this.readyState;
				var status = this.status;
				
				if (readyState === 4 && status === 200) {
					
					var json = JSON.parse(this.responseText);
					
					if(json["success"]) {  // 로그인 성공시
						
						if(json["url"] === "/") {
							window.location = "/idus";
						} else {
							window.location = "/idus/" + json["url"];
						}
					
					} else {
						
						alert("이미 회원으로 가입된 이메일 입니다!");
						window.location = "/idus/login";
						
					}
					
				}
				
			}
			
			ajax.setRequestHeader("content-type", "application/x-www-form-urlencoded; charset=utf-8");
			ajax.send("id=" + id + "&email=" + email + "&name=" + name + "&thumbnail=" + thumbnail + "&nickName=" + nickName);
		}
	</script>
</body>
</html>