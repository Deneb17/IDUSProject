// 자동 로그인 세션 삭제
function deleteSession() {

	var xhr = new XMLHttpRequest();

	xhr.open("post", "idus/logout/deleteSession",
			true);

	xhr.onreadystatechange = function() {

		var readyState = this.readyState;
		var status = this.status;

		if (readyState === 4 && status === 200) {

			var response = this.responseText;
			var json = JSON.parse(response);
			console.log(json);

			if (json["isTerminated"]) {
				window.alert("세션이 성공적으로 삭제 되었습니다.");
				deleteCookie(json["sessionId"]);
			} else {
				window.alert("세션이 이미 삭제 되었습니다.");
			}

		}
	}
	xhr.send();
}


// 자동 로그인 세션 쿠키 삭제
function deleteCookie(name) {
	document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}