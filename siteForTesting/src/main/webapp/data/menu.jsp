<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu.jsp</title>
</head>
<body> 
<%-- <img src="${pageContext.request.contextPath}/images/Among-Us5.png"> --%>
    <!-- 메뉴 row 시작 -->
    <div class="row">
    	<div class="col-lg-6 col-md-12">
    	<!-- 6은 50%이라는 뜻. large일때는 50% -->
    	<!-- 12은 100%이라는 뜻. medium일때는 100% 보여라 -->
    		<h1>Beverages</h1>
    		<p>Cappucino $3.25</p>
    		<p>Latte $3.35</p>
    		<p>Espresso $2.00</p>
    		<p>Mocha $3.50</p>
    	</div>
    	<div class="col">
    		<h1>Dessert</h1>
    		<p>ㅇ $3.25</p>
    		<p>ㄱ $3.35</p>
    		<p>ㄴ $2.00</p>
    		<p>ㄷ $3.50</p>
    	</div>
    </div>
    <!-- 메뉴 row 끝 -->
    <!-- 메뉴 row 시작 -->
    <div class="row">
    	<div class="col">
    		<table class="table">
    			<thead><tr><th>이름</th><th>전화번호</th></tr></thead>
    			<tbody><tr><td>김기자</td><td>111</td></tr></tbody>
    			<tbody><tr><td>이기자</td><td>222</td></tr></tbody>
    			<tbody><tr><td>최기자</td><td>333</td></tr></tbody>
    		</table>
    		<!-- 버튼 -->
    		<span class="btn btn-outline-warning">span</span>
    		<a class="btn btn-danger" href="#" role="button">Notifications <span class="badge badge-light">4</span></a>
			<input class="btn btn-outline-dark" type="button" value="Input">
			<input class="btn btn-primary" type="submit" value="Submit">
    	</div>
    </div>
    <!-- 메뉴 row 끝 -->
</body>
</html>