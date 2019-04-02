$(document).ready(function() {
	$("#content").summernote({
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



function writePost() {
	
	var isFormValid = false;
	
	if($("#title").val() === "") {
		alert("제목은 반드시 작성 되어야 합니다.");
		$("#title").focus();
	} else if ($("#content").val() === "") {
		alert("내용은 반드시 작성 되어야 합니다.");
		$("#content").focus();
	} else {
		isFormValid = true;
	}
	
	
	if(isFormValid) {
		$("#postForm").submit();
	}
	
}