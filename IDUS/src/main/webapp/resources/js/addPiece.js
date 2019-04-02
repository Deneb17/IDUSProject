var optionNo = 1;  // 옵션 번호


$(document).ready(function() {
	$("#description").summernote({
		placeholder : "상품의 상세 내용을 입력 하세요.",
		width : "100%",
		height : 600,
		minHeight : 600,
		maxHeight : 600,
		callbacks : {
			onImageUpload : function(files, editor, welEditable) {
				for (var i = 0; i < files.length; i++) {
					sendFile(files[i], this);
				}
			}
		}
	});
});


function sendFile(file, el) {

	var formData = new FormData();
	formData.append("multipartFile", file);

	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {

		var readyState = this.readyState;
		var status = this.status;

		if (readyState === 4 && status === 200) {
			var temp = this.responseText;
			var json = JSON.parse(temp);
			if (json["isSaved"]) {
				$(el).summernote("editor.insertImage", json["url"]);
			}
		}

	}

	xhr.open("POST", "/idus/blog/saveImage", true);
	xhr.setRequestHeader("enctype", "multipart/form-data; charset=utf-8;");
	xhr.send(formData);
}


// 옵션 아이템 추가
function addItem() {
    
    // li tag의 아이디 생성
    var id = "item" + optionNo;
    optionNo++;
    
    // input의 value로 사용 될 옵션값
    var newOption = $("#item").val();
    
    // li tag 생성
    var li = $("<li id='" + id + "' display='none'>" + newOption + "</li>");
    // input tag 생성
    var input = $("<input type='hidden' name='options' value='" + newOption + "'></input>");
    // 삭제버튼
    var button = $("<button class='optionbtn' type='button' onclick='deleteItem(\"" + id + "\")'><i class='fas fa-minus'></i></button>");
    
    li.append(input);
    li.append(button);
    
    $("#optionTarget").append(li);
    $("#item").val("");
    
    if($("#optionTarget").has()) {
        $("#optionTarget").slideDown();
        $("#"+id).slideDown();
    }
    
}


// 아이템 삭제
function deleteItem(elementId) {
    $("#"+elementId).slideUp().remove();
    if($("#optionTarget").children().length <= 0) {
        $("#optionTarget").slideUp();
    }
}