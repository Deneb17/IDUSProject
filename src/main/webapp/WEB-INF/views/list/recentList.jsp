<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/commons/meta.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>
ul {
	list-style-type: none;
}

div.gallery {
	margin: 5px;
	border: 1px solid #ccc;
	float: left;
	width: 180px;
}

div.gallery:hover {
	border: 1px solid #777;
}

div.gallery img {
	width: 100%;
	height: auto;
}

div.desc {
	padding: 15px;
	text-align: center;
	border: 1px solid #ccc;
}

.big-box {
  width: 100%;
  background-color: gray;
  height: 100vh;
  border-top: 1px solid black;
}
</style>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">

</head>
<body>
<!-- header -->
	<c:if test="${!empty auth}">
		<%@ include file="/WEB-INF/views/commons/loginHeader.jsp" %>
	</c:if>
	<c:if test="${empty auth}">
		<%@ include file="/WEB-INF/views/commons/noLoginHeader.jsp" %>
	</c:if>

<section>
	<h2>최근 상품 리스트 입니다.</h2>

	<ul id="RecentList">
		<!-- controller에 들어있는 model에서 result를 꺼내 쓴다 -->
		<c:forEach var="row" items="${result}">
			<li>
				<div class="gallery">
					<div>
						<a href=""><img alt="" src="${row.url}" height="100"
							width="100"></a>
					</div>
					<div class="desc">
						<div>작품번호: ${row.pieceNo}</div>
						<div>제작자: ${row.nickName}</div>
						<div>제목: ${row.title}</div>
						<div>상품 이름: ${row.pieceName}</div>
						<div>
							별점:
							<c:forEach begin="1" end="${row.star}">
								<i class="fas fa-star"></i>
							</c:forEach>
						</div>
					</div>
				</div> 
			</li>
		</c:forEach>
	</ul>
	
	</section>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

let isEnd = false;

$(function(){
	$(window).scroll(function() {
		
		 let $window = $(this);
         let scrollTop = Math.ceil($window.scrollTop());
         let windowHeight = $window.height();
         let documentHeight = $(document).height();
         console.log("documentHeight: " + documentHeight +
        		 " scrollTop+windowHeight: " + (scrollTop+windowHeight));

  // scrollbar의 thumb가 바닥 전 30px까지 도달 하면 리스트를 가져온다.

	  if(scrollTop + windowHeight > documentHeight || scrollTop + windowHeight == documentHeight ){
		  console.log(">>>>>>>>>>>> if 탄다 ㅋㅋㅋ");
		  fetchList();
	  }
 
	});
});
      
	var startNo = 21; //bno에 넣을 처음 값!
	
	let fetchList = function(){
	    if(isEnd == true){
	             return;
	    }
	    $.ajax({
	    			
	             url:"recentMore?bno=" + startNo , // 서버측에서 가져올 페이지 //bno에 넣을 처음 값!
	             type: "GET", 
	             dataType: "json", // html , javascript, text, xml, jsonp 등이 있다
	             
	             success: function(result){ // 정상적으로 완료되었을 경우에 실행된다
	            	 
	                     // 남은 데이터가 20개 이하일 경우 무한 스크롤 종료
	              let length = result.length;
	              	console.log("length>>>>>> " +length);
	              	if( length < 20 ){
	                   isEnd = true;
	                     }
	                     
	                     //이부분 뭐지? 각각 result를  function()함수로 적용?????????
	              	$.each(result, function(index, vo){renderList(vo);})
	             	startNo +=20;
	             	console.log("startNo>>>>>>>>>>>>" + startNo);
	             	// 처음 startNo =21, 41, 61....
	             }
	    });
	}

	//renderList(vo);로 각각 돌린다.
	let renderList = function(vo){
	    // 리스트 html을 정의
	    let html = '<li><div class="gallery">'+
		'<div><a href=""><img alt="" src='+ vo.url+' height="100" width="100"></a>'+
		'</div><div class="desc"><div>작품번호:'+ vo.pieceNo+'</div><div>제작자:'+ vo.nickName+
		'}</div><div>제목:'+vo.title+'</div><div>상품 이름:'+vo.pieceName+'</div><div>별점:';
		
		for(var i=0; i<vo.star; i++){
			html += "<i class='fas fa-star'></i>";
		}
	     $("#RecentList").append(html); //"#RecentList"뒤에  html내용 붙인다.
	     //html에 <ul id="RecentList">이라고 써져있음 거기 뒤에 붙어라..
	}
</script>


</body>
</html>
