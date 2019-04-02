var isFileUploaded = false; // 현재 파일 업로드 상태
var numberRegex = /[^0-9]+/g;

$(document).ready(function () {
    $("#title").keyup(function () {
        checkCharLength(this, 50);
    });
    
    $("#pieceName").keyup(function () {
        checkCharLength(this, 50);
    });
    
    $("#item").keyup(function () {
        checkCharLength(this, 100);
    });
    
    $("#price").keyup(function() {
        isNumber(this);
    });
    
    $("#deliveryCharge").keyup(function() {
        isNumber(this);
    });
    
    $("#discount").keyup(function() {
        isNumber(this);
    });
    
    $("#thumbnail").change(function () {
        validateFile(this);
    });
});


// 입력된 값이 숫자인지 체크
function isNumber(element) {
    if(numberRegex.test(element.value)) {
        alert("해당 필드는 숫자만 입력 할 수 있습니다.");
        element.value = "";
        element.focus();
    }
}

// 글자수 체크 함수
function checkCharLength(element, limit) {

    // field에 입력된 값
    var txt = element.value;

    if (txt.length > limit) {
        alert(limit + "자 이상 입력 할 수 없습니다.");
        element.value = txt.substring(0, limit);
        element.focus();
    }
}


function addPiece() {
    var isFormValid = validateForm();
    if (isFormValid) {
        if($("#discount").val() === "") {
            $("#discount").val(0);
        }
        $("#pieceForm").submit();
    }
}


function validateForm() {

    var isFormValid = false;

    if ($("#title").val() === "") {
        alert("제목은 반드시 입력되어야 합니다.");
        $("#title").focus();
    } else if ($("#pieceName").val() === "") {
        alert("상품명은 반드시 입력되어야 합니다.");
        $("#pieceName").focus();
    } else if ($("#price").val() === "") {
        alert("가격은 반드시 입력되어야 합니다.");
        $("#price").focus();
    } else if ($("#deliveryCharge").val() === "") {
        alert("배송비는 반드시 입력되어야 합니다.");
        $("#deliveryCharge").focus();
    } else if(!isFileUploaded) {
        alert("상품의 대표 이미지를 업로드 해 주세요.");
    } else if($("#description").val() === "") {
        alert("상품 설명은 반드시 입력되어야 합니다.");
        $("#description").focus();
    } else if($("#optionTarget").children().length <= 0) {
        alert("상품 옵션은 반드시 1개 이상 입력되어야 합니다.");
        $("#item").focus();
    }else {
        isFormValid = true;
    }
    
    

    return isFormValid;
}


// 업로드 된 파일의 확장자 검증 함수
function validateFile(element) {

    // console.log(element);

    // 파일의 정보
    var file = element.files;

    // console.log(file);
    // console.log(file[0]["name"]);

    if (/(.gif|.jpg|.jpeg|.png)$/i.test(file[0]["name"])) {
        $("#thumbnailVS").html("올바른 형식의 파일입니다.").css({
            "color": "green"
        });
        isFileUploaded = true;
    } else {
        element.value = "";
        $("#thumbnailVS").html("사용 할 수 없는 형식의 파일입니다.").css({
            "color": "red"
        });
        isFileUploaded = false;
    }
}
