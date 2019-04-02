// 어플리케이션의 JavaScript 키를 설정
Kakao.init("46cec58270b6cd4d4132c6f18c81a2ed");


// 카카오 로그인 버튼을 생성
Kakao.Auth.createLoginButton({
	container : '#kakao-login-btn',  // 로그인 버튼
	success : function(authObj) {  // 로그인 성공 시
		// outhObj : 인증 토큰
		// 사용자 profile 받아오기
		Kakao.API.request({
			url : "/v2/user/me",
			success : function(response) {  // profile 받아오기 성공 콜백
				kakaoLogin(response);
			},
			fail : function(error) {  // profile 받아오기 실패 콜백
				
			}
		});
	},
	fail : function(err) {  // 로그인 실패 콜백
		console.log(err);
	}
});


// 서버로 사용자 profile 전송
function kakaoLogin(response) {
	
	// 서버로 전송 할 사용자 profile 생성
	var profile = "id=" + encodeURIComponent(response["id"]) + "&nickName=" + encodeURIComponent(response["properties"]["nickname"]);
	
	// 이메일 포함 시
	if (response["kakao_account"]["has_email"]) {
		profile += "&email=" + encodeURIComponent(response["kakao_account"]["email"]);
	}
	
	// 썸네일 존재 시
	if (response["properties"]["thumbnail_image"]) {
		profile += "&thumbnail=" + response["properties"]["thumbnail_image"];
	}
	
	// AJAX
	$.ajax({
		type : "post",
		url : "/idus/login/kakao",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : profile,
		success : function(res) {
			
			if(res["success"]) {
				if(res["url"] === "/") {
					window.location = "/idus/";
				} else {
					window.location = "/idus/" + res["url"];
				}
			} else {
				alert("이미 가입되어 있는 회원입니다!");
			}
			
		},
		fail : function(error) {
			console.log(error);
		}
	});
	
}