<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberClient</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(function() {
// 조회	
	$.ajax("ajax/memberList.do", {
		dataType: "json"
// ajax 요청이 실행되고 나면 다른 펑션으로 이어진다.. 보통 success 쓰는거보다 이걸 더 선호함
	}).done(function(datas){
		for(i=0; i<datas.length; i++){
			$("<div>").append($("<span>").text(datas[i].id))
					  .append($("<span>").text(datas[i].name))
					  .append($("<span>").text(datas[i].tel))
					  .appendTo("#memList"); // 새로만들어진 이 div 태그들을 #memList에 붙이겠다
		}
	}); 
// 등록
	$("#btnSave").on("click", function(){
		$.ajax("ajax/memberInsert.do", {
			dataType: "json",
			data: $("#frm").serialize()
			// 넘겨줄 데이터들..?? 아이디 frm인 form태그의 모든 입력값(serial)을 뜻함
		}).done(function(datas){ // 단건이므로 for문 필요없음
			$("<div>").append($("<span>").text(datas.id))
			  .append($("<span>").text(datas.name))
			  .append($("<span>").text(datas.tel))
			  .appendTo("#memList");
		// appendTo 제일뒤에
		// prependTo 제일앞에 추가
		});
	});
})
</script>
</head>
<body>
<div align="center"><h1>회원가입</h1></div>
<div class="row">
	<div class="col">
		<div id="memList"></div>
	</div>
	<div class="col">
		<form id="frm" name="frm" method="post">
			<table border="1" style="border-collapse: collapse; text-align: center;">
				<tr>
					<th width="150" style="background-color: lightblue;">아 이 디</th>
					<td><input type="text" id="id" name="id"></td>
				</tr>
				<tr>
					<th width="150" style="background-color: lightblue;">이   름</th>
					<td><input type="text" id="name" name="name"></td>
				</tr>
				<tr>
					<th width="150" style="background-color: lightblue;">패스워드</th>
					<td><input type="password" id="pw" name="pw"></td>
				</tr>
				<tr>
					<th width="150" style="background-color: lightblue;">주   소</th>
					<td><input type="text" id="address" name="address"></td>
				</tr>
				<tr>
					<th width="150" style="background-color: lightblue;">전화번호</th>
					<td><input type="text" id="tel" name="tel"></td>
				</tr>
				<tr>
					<th width="150" style="background-color: lightblue;">가입일자</th>
					<td><input type="date" id="enterdate" name="enterdate"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="btnSave" value="회원가입">&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>