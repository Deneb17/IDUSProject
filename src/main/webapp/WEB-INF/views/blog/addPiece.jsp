<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/commons/meta.jsp"%>
	<!-- summernote css -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.css" rel="stylesheet">
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
		<div class="container">
			<div class="sectionHeader">
				<h1>상품 등록</h1>
				<hr>
				<p>자신만의 상품을 많은 사람들에게 선보이세요.</p>
			</div>
			<div class="sectionContent">
				<h3>
					<span style="color: var(--red);">*</span> 항목은 필수입니다.
				</h3>
				<!-- 상품 등록 -->
				<form action="${pageContext.request.contextPath}/blog/addPiece" method="post" id="pieceForm" enctype="multipart/form-data">
					<!-- 제목 -->
					<label> 1. 제목 <span style="color: var(--red);">*</span><br>
						<input type="text" id="title" name="title" />
					</label>
					
					<!-- 작품 이름 -->
					<label> 2. 작품 이름 <span style="color: var(--red);">*</span><br>
						<input type="text" id="pieceName" name="pieceName" />
					</label>
					
					<!-- 가격 -->
					<label> 3. 가격 <span style="color: var(--red);">*</span><br>
						<input type="text" id="price" name="price" />
					</label>

					<!-- 옵션 -->
					<label> 4. 옵션 <span style="color: var(- -red);">*</span><br>
						<input class="option" type="text" id="item" />
						<button class="optionbtn" type="button" onclick="addItem()">
							<i class="fas fa-plus"></i>
						</button>
					</label>
					<ul id="optionTarget" style="display: none"></ul>

					<!-- 배송비 -->
					<label> 5. 배송비 <span style="color: var(--red);">*</span><br>
						<input type="text" id="deliveryCharge" name="deliveryCharge" />
					</label>
					
					<!-- 할인 -->
					<label> 6. 할인율<br> 
						<input type="text" id="discount" name="discount" placeholder="미 기입시 0%"/>
					</label>
					
					<!-- 상품 대표 이미지 -->
					<label> 7. 상품 대표 이미지 <span style="color: var(--red);">*</span><br>
						<input type="file" id="thumbnail" name="thumbnail" multiple />
					</label>
					<span class="errorMsg" id="thumbnailVS"></span>
					
					<!-- 상품 이미지 -->
					<label> 8. 상품 이미지<br> 
						<input type="file" id="images" name="images" multiple />
					</label>
					
					<!-- 상품 상세 -->
					<label> 9. 상품 상세 <span style="color: var(--red);">*</span> <br><br>
						<textarea id="description" name="description"></textarea>
					</label>
					
					<button class="subBtn" type="button" onclick="addPiece()">작품 등록</button>
				</form>
			</div>
		</div>
	</section>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/commons/footer.jsp"%>
	
	<!-- summernote api -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/addPiece.js"></script>
	
	<!-- validation javascript -->
	<script src="${pageContext.request.contextPath}/resources/js/addPieceFormValidation.js"></script>
</body>
</html>