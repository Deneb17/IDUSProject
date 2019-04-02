
// 주소창 실행 함수
function daumPost() {
	
	// 다음 주소창 실행
	new daum.Postcode({
		
		// 주소 선택시 실행될 함수 등록
		oncomplete : function(data) {

			// data : 선택된 주소의 정보가 담겨있는 json object
			// console.log(data);
			
			// 우편번호
			var zoneCode = data["zonecode"];
			// 기본 주소
			var address = data["address"];
			// 법정 동
			var bname = data["bname"];
			// 건물명
			var buildingName = data["buildingName"];
			// 공동주택 여부 (Y/N)
			var isApartment = data["apartment"];
			
			var temp = "";
			
			// 동 이름이 ""가 아니면서 동, 로, 가로 끝나는 경우
			if(bname !== "" && /[동|로|가|리]$/g.test(bname)) {
				temp += bname;
			}
			
			// 빌딩 이름이 ""가 아니면서 아파트와 같은 공동 주택일 경우
			if(temp !== "" && buildingName !== "" && isApartment === "Y") {
				// 동 이름이 있을 때
				temp += ", " + buildingName;
			} else if(buildingName !== "" && isApartment === "Y") {
				// 동 이름이 없을 때
				temp += buildingName;
			}
			
			// 완성된 주소 형태 = 기본주소 (법정동, 건물명)
			address += " ( " + temp + " )";
			
			$("#zoneCode").val(zoneCode);
			$("#address").val(address);
			
		}
	}).open();
	
}
