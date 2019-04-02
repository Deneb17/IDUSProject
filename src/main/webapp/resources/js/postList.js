
function nextPage(pageNo) {
	if (title !== undefined || title !== "") { // title이 있을 경우
		window.location = url + "?pageNo=" + encodeURIComponent(pageNo)
				+ "&title=" + encodeURIComponent(title);
	} else { // title이 없을 경우
		window.location = url + "?pageNo=" + encodeURIComponent(pageNo);
	}
}


function changePageBundle(x) {
	currentBundle += x; // 현재 페이지 묶음에서 +, - 1
	if (title !== undefined || title !== "") { // title이 있을 경우
		window.location = url + "?pageNo=" + (currentBundle * 5 - 4)
				+ "&title=" + encodeURIComponent(title);
	} else { // title이 없을 경우
		window.location = url + "?pageNo=" + (currentBundle * 5 - 4);
	}
}