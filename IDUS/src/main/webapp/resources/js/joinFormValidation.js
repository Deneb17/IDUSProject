
// form element의 validation에 사용 할 reqular expression
var emailRegex = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
var passwordRegex = /[a-zA-Z0-9\!\@\#\$\%\^\&\*\(\)\_\-\+\\\=]{6,12}/;
var nameRegex = /[가-힣]{2,10}/;
var nickNameRegex = /[가-힣a-zA-Z0-9]{1,10}/;  // 
var phoneRegex = /\d{10,11}/;


// form의 input field들의 validation state
var emailValid = false;
var passwordValid = false;
var confPasswordValid = false;
var nameValid = false;
var nickNameValid = false;  // 
var phoneValid = false;
var isEmailDup = true;
var isNickNameDup = true;  // 


// form 에 입력되는 값. setter를 이용하여 값이 바뀔 때 마다 특정 함수를 실행
var formVal = {
	email : "",
	password : "",
	confPassword : "",
	name : "",
	nickName: "",
	phone : "",
	introduction : "",
	setEmail : function(x) {
		this.email = x;
		let isValid = validateField("email", this.email);
		emailValid = isValid;
		isEmailDup = true; // email field가 한번이라도 변경 될 경우 중복검사 다시 실행 하기 위해
		showMessage("email", isValid);
	},
	setPassword : function(x) {
		this.password = x;
		let isValid = validateField("password", this.password);
		passwordValid = isValid;
		showMessage("password", isValid);
	},
	setConfPassword : function(x) {
		this.confPassword = x;
		let isValid = validateField("confPassword", this.confPassword);
		confPasswordValid = isValid;
		showMessage("confPassword", isValid);
	},
	setName : function(x) {
		this.name = x;
		let isValid = validateField("name", this.name);
		nameValid = isValid;
		showMessage("name", isValid);
	},
	setNickName: function (x) {
        this.nickName = x;
        let isValid = validateField("nickName", this.nickName);
        nickNameValid = isValid;
        isNickNameDup = true;  // nickName field가 한번이라도 변경 될 경우 중복검사 다시 실행 하기 위해
        showMessage("nickName", isValid);
    },
	setPhone : function(x) {
		this.phone = x;
		let isValid = validateField("phone", this.phone);
		phoneValid = isValid;
		showMessage("phone", isValid);
	},
}


// document의 loading이 끝나면 form의 각 element에 event listener함수를 적용.
$(document).ready(function() {
	$("#email").keyup(function() {
		formVal.setEmail(this.value);
	});
	$("#password").keyup(function() {
		formVal.setPassword(this.value);
	});
	$("#confPassword").keyup(function() {
		formVal.setConfPassword(this.value);
	});
	$("#name").keyup(function() {
		formVal.setName(this.value);
	});
	$("#nickName").keyup(function () {
        formVal.setNickName(this.value);
    });
	$("#phone").keyup(function() {
		formVal.setPhone(this.value);
	});
	$("#thumbnail").change(function() {
		validateFile(this);
	});
	$("#detailAddress").keyup(function() {
		checkChar(this);
	});
	$("#introduction").keyup(function() {
		checkChar(this);
	});
	$("#totalAgree").click(function() {
		$("#agree1").click();
		$("#agree2").click();
		$("#agree3").click();
	});
});


// validation 함수
function validateField(fieldType, fieldValue) {

	// field의 검증 상태
	var isValid = false;

	// field의 type에 따라 각기 다른 reqular expression을 사용하여 test
	switch (fieldType) {
	case "email":
		isValid = emailRegex.test(fieldValue);
		break;
	case "password":
		isValid = passwordRegex.test(fieldValue);
		break;
	case "confPassword":
		isValid = (formVal["password"] === fieldValue);
		if(fieldValue === "") isValid = false;
		break;
	case "name":
		isValid = nameRegex.test(fieldValue);
		break;
	case "nickName":
		isValid = nickNameRegex.test(fieldValue);
		break;
	case "phone":
		isValid = phoneRegex.test(fieldValue);
		break;
	default:
		if (fieldValue.length < 100) {
			isValid = true;
		}
	}

	// validation 결과를 return
	return isValid;
}


// 업로드 된 파일의 확장자 검증 함수
function validateFile(element) {

	// console.log(element);

	// 파일의 정보
	var file = element.files;

	// console.log(file);
	// console.log(file[0]["name"]);

	if (/(.gif|.jpg|.jpeg|.png)$/i.test(file[0]["name"])) {
		$("#thumbnailValidationState").html("올바른 형식의 파일입니다.").css({
			"color" : "green"
		});
	} else {
		element.value = "";
		$("#thumbnailValidationState").html("사용 할 수 없는 형식의 파일입니다.").css({
			"color" : "red"
		});
	}
}


// 글자수 체크 함수
function checkChar(element) {
	
	// field에 입력된 값
	var txt = element.value;
	
	//
	if(txt.length > 100) {
		alert("100자 이상 입력 할 수 없습니다.");
		element.value = txt.substring(0, 100);
		element.focus();
	}
}


// span의 검증 상태 변경 함수
function showMessage(fieldType, isValid) {

	// 검증 상태가 출력 될 span element의 id
	var id = fieldType + "ValidationState";

	// 검증 상태가 출력될 span element
	var target = document.getElementById(id);

	// 형식 만족시 출력될 문구
	var goodHtml;

	// 형식 만족시키지 못했을 시 출력 될 문구
	var badHtml;

	// field의 type에 따라 출력 문구 결정
	switch (fieldType) {
	case "email":
		goodHtml = "사용 할 수 있는 이메일 주소 형식입니다.";
		badHtml = "올바른 이메일 주소 형식으로 입력 해 주세요.";
		break;
	case "password":
		goodHtml = "사용 가능한 비밀번호 입니다.";
		badHtml = "비밀번호는 영어 대소문자, 숫자, 특수문자를 포함한 6 ~ 12자여야 합니다.";
		break;
	case "confPassword":
		goodHtml = "비밀번호와 일치합니다.";
		badHtml = "일치하지 않는 비밀번호입니다.";
		break;
	case "name":
		goodHtml = "올바른 이름 형식입니다.";
		badHtml = "바른 형식의 이름을 입력 해 주세요.";
		break;
	case "nickName":
		goodHtml = "올바른 닉네임 형식입니다.";
		badHtml = "닉네임은 영어 대소문자, 숫자, 한글 1~10자리여야 합니다.";
		break;
	case "phone":
		goodHtml = "사용 할 수 있는 전화번호 형식입니다.";
		badHtml = "전화번호는 '-' 를 제외한 숫자 10 ~ 11 자리여야 합니다.";
		break;
	default:
		goodHtml = "올바른 형식입니다.";
		badHtml = "해당 필드는 100글자 이상 입력 할 수 없습니다.";
	}

	// validation 상태에 따라 span에 알맞은 문구를 출력
	if (isValid) {
		target.innerHTML = goodHtml;
		target.style.color = "green";
	} else {
		target.innerHTML = badHtml;
		target.style.color = "red";
	}

}


// 이메일 중복 체크 함수
function emailDuplicationCheck() {

	var email = document.getElementById("email");
	var xhr = new XMLHttpRequest();

	if (typeof email.value === undefined || email.value === "") {
		alert("이메일이 입력되지 않았습니다.");
		return;
	}

	xhr.open("post", "/idus/join/emailDup", true);

	xhr.onreadystatechange = function() {

		var readyState = this.readyState;
		var status = this.status;

		if (readyState === 4 && status === 200) {

			var response = this.responseText;
			var json = JSON.parse(response);
			console.log(json);

			if (json["isDuplicated"]) {
				window.alert("중복된 이메일 입니다.");
				isEmailDup = true;
			} else {
				window.alert("사용 가능한 이메일 입니다.");
				isEmailDup = false;
			}

		}
	}
	xhr.setRequestHeader("content-type",
			"application/x-www-form-urlencoded; charset=utf-8");
	xhr.send("email=" + encodeURIComponent(email.value));
}



//닉네임 중복 체크 함수
function nickNameDuplicationCheck() {

	var nickName = document.getElementById("nickName");
	var xhr = new XMLHttpRequest();

	if (typeof nickName.value === undefined || nickName.value === "") {
		alert("닉네임이 입력되지 않았습니다.");
		return;
	}

	xhr.open("post", "/idus/join/nickNameDup", true);

	xhr.onreadystatechange = function() {

		var readyState = this.readyState;
		var status = this.status;

		if (readyState === 4 && status === 200) {

			var response = this.responseText;
			var json = JSON.parse(response);
			console.log(json);

			if (json["isDuplicated"]) {
				window.alert("중복된 닉네임 입니다.");
				isNickNameDup = true;
			} else {
				window.alert("사용 가능한 닉네임 입니다.");
				isNickNameDup = false;
			}

		}
	}

	xhr.setRequestHeader("content-type",
			"application/x-www-form-urlencoded; charset=utf-8");
	xhr.send("nickName=" + encodeURIComponent(nickName.value));
}


// form의 validation 상태 확인
function validateForm() {

	// form validation 상태
	var formValid = false;

	// 각각의 input field에 대해 validation 상태 검증
	if(!emailValid) {
		alert("이메일을 다시한번 확인 해 주세요.");
		$("#email").focus();
	} else if(!passwordValid) {
		alert("비밀번호를 다시한번 확인 해 주세요.");
		$("#password").focus();
	} else if(!confPasswordValid) {
		alert("비밀번호가 일치하지 않습니다.");
		$("#confPassword").focus();
	} else if(!nameValid) {
		alert("이름을 다시한번 확인 해 주세요.");
		$("#name").focus();
	} else if(!nickNameValid) {
		alert("닉네임을 다시한번 확인 해 주세요.");
		$("#nickName").focus();
	} else if(!phoneValid) {
		alert("핸드폰 번호를 다시한번 확인 해 주세요.");
		$("#phone").focus();
	} else if(isEmailDup) {
		alert("중복된 이메일 이거나 중복체크가 필요합니다.");
		$("#email").focus();
	} else if(isNickNameDup) {
		alert("중복된 닉네임 이거나 중복체크가 필요합니다.");
		$("#nickName").focus();
	} else {
		formValid = true;
	}
	
	return formValid;
}



//form 제출
function join() {

	// 회원가입 양식
	var joinForm = document.getElementById("joinForm");

	var isFormValid = validateForm();

	if (isFormValid) {
		joinForm.submit();
	}
}