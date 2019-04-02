
// form element의 validation에 사용 할 reqular expression
var emailRegex = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
var passwordRegex = /[a-zA-Z0-9\!\@\#\$\%\^\&\*\(\)\_\-\+\\\=]{6,12}/;



// form의 input field들의 validation state
var emailValid = false;
var passwordValid = false;


//form 제출
function login() {
    
    // 회원가입 양식
    var joinForm = document.getElementById("loginForm");
    
    var isFormValid = validateForm();
    
    if(isFormValid) {
        joinForm.submit();
    }
}


//form validation
function validateForm() {
	
	// form validation 상태
    var formValid = false;
    
    // validation에 사용할 field의 값
    var emailVal = $("#email").val();
    var passwordVal = $("#password").val();
    
    // email validation
    emailValid = validateField("email", emailVal);
    
    // password validation
    passwordValid = validateField("password", passwordVal);
    
    // 각각의 input field의 validation 상태에 따라 메세지 출력
    if(emailValid) {
        if(passwordValid) {
        	formValid = true;
        } else {
        	showMessage("password");
        }
    } else {
    	showMessage("email");
    }
    return formValid;
}


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
    }

    // validation 결과를 return
    return isValid;
}



// span의 검증 상태 변경 함수
function showMessage(fieldType) {
	
	$("#emailValidationState").html("");
	$("#passwordValidationState").html("");
	$("#loginFailMsg").hide();
	
    // 검증 상태가 출력 될 span element의 id
    var id = fieldType + "ValidationState";

    // 검증 상태가 출력될 span element
    var target = document.getElementById(id);

    // 형식 만족시키지 못했을 시 출력 될 문구
    var msg;

    // field의 type에 따라 출력 문구 결정
    switch (fieldType) {
        case "email":
            msg = "올바른 이메일을 입력 해 주세요.";
            break;
        case "password":
        	msg = "올바른 비밀번호를 입력 해 주세요.";
            break;
    }
    
    // validation 상태에 따라 span에 알맞은 문구를 출력
    target.innerHTML = msg;
    target.style.color = "red";

}