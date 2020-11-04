<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>RESTful 웹서비스 클라이언트(JSON)</title>
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" >
	$(function(){
		userList();

		userSelect();
		
		userDelete();
		
		userInsert();
	
		userUpdate();
		
		init();
	});
	
	//초기화
	function init() {
		//초기화 버튼 클릭
		$('#btnInit').on('click',function(){
			$('#form1').each(function(){
				this.reset();
			});
		});
	}//init
	
	//사용자 삭제 요청
	function userDelete() {
		//삭제 버튼 클릭
		$('body').on('click','#btnDelete',function(){
			// 위에서 먼저 펑션을 실행하게 되므로 그때는 버튼에 이벤트 걸어둔걸 인식을 못해
			// 사용자 추가될때마다 삭제버튼도 새로이 만들어져야하므로! 그래서 이렇게 부모태그에 이벤트를 위임 해둔거
			var userId = $(this).closest('tr').find('#hidden_userId').val();
			var result = confirm(userId +" 사용자를 정말로 삭제하시겠습니까?");
			if(result) {
				$.ajax({
					url:'ajax/usersDelete.do',
					data : {id : userId}, // id 값 넣어주면 삭제
					dataType:'json',
					error:function(xhr,status,msg){
						//console.log("상태값 :" + status + " Http에러메시지 :"+msg);
						console.log("dddd");
					}, success:function(xhr) {
						userList();
					}
				});      }//if
		}); //삭제 버튼 클릭
	}//userDelete
	
	//사용자 조회 요청
	function userSelect() {
		//조회 버튼 클릭
		$('body').on('click','#btnSelect',function(){
			// 위에서 먼저 펑션을 실행하게 되므로 그때는 버튼에 이벤트 걸어둔걸 인식을 못해
			// 사용자 추가될때마다 조회버튼도 새로이 만들어져야하므로! 그래서 이렇게 부모태그에 이벤트를 위임 해둔거
			
			var userId = $(this).data("userId");
			// var userId = $(this).closest('tr').find('#hidden_userId').val();
			// $(this) 클릭한 부분의 부모 tr을 찾고, 그것의 자식중에 #hidden_userId의 값을 가져와
			//특정 사용자 조회
			$.ajax({
				url:'ajax/users.do',
				data : {id : userId},
				dataType:'json',
				error:function(xhr,status,msg){
					alert("상태값 :" + status + " Http에러메시지 :"+msg);
				},
				success:userSelectResult // 결과콜백함수에 넣어줘
			});
		}); //조회 버튼 클릭
	}//userSelect
	
	//사용자 조회 응답
	function userSelectResult(user) {
		$('input:text[name="id"]').val(user.id);
		$('input:text[name="name"]').val(user.name);
		$('input:radio[name="gender"]').val([user.gender]); // 얘는 배열! 체크박스나 라디오버튼은 배열로 받아줘야함!
		$('select[name="role"]').val(user.role).attr("selected", "selected");
	}//userSelectResult
	
	//사용자 수정 요청
	function userUpdate() {
		//수정 버튼 클릭
		$('#btnUpdate').on('click',function(){
			// 수정버튼은 딱 하나뿐이고 이미 만들어져있어서 부모태그에 위임안해도 됨
			$.ajax({ 
			    url: "ajax/usersUpdate.do", 
			    dataType: 'json', 
			    data: $("#form1").serialize(),
			    success: function(data) { 
			        userList();
			    },
			    error:function(xhr, status, message) { 
			        alert(" status: "+status+" er:"+message);
			    }
			});
		});//수정 버튼 클릭
	}//userUpdate
	
	//사용자 등록 요청
	function userInsert(){
		//등록 버튼 클릭
		$('#btnInsert').on('click',function(){
			$.ajax({ 
			    url: "ajax/usersInsert.do",  
			    dataType: 'json', // 서버로부터 넘겨받을 데이터의 타입 
			    method : "POST", 
			    data :  $("#form1").serialize(), // 현재 폼에있는 모든 문자를 데이터(서버로 넘겨줄 파라미터)로 보내줌
			    success: function(response) {
			    		userList();
			    }, 
			    error:function(xhr, status, message) { 
			        alert(" status: "+status+" er:"+message);
			    } 
			 });  
		});//등록 버튼 클릭
	}//userInsert
	
	//사용자 목록 조회 요청
	function userList() {
		$.ajax({
			url:'ajax/usersList.do',
			type:'GET',
			dataType:'json',
			error:function(xhr,status,msg){
				alert("상태값 :" + status + " Http에러메시지 :"+msg);
			},
			success:userListResult
		});
	}//userList
	
	//사용자 목록 조회 응답
	function userListResult(data) {
		$("tbody").empty(); // usersList 부를때마다 기존의 내용을 다 지우고 다시 불러온다
		$.each(data,function(idx,item){ // item 하나가 중괄호라고..?? 그거안에 각각 key-value 쌍으로있고?
			$('<tr>') // 태그 생성
			.append($('<td>').html(item.id))
			.append($('<td>').html(item.name))
			.append($('<td>').html(item.gender))
			.append($('<td>').html(item.role))
			.append($('<td>').append($('<button>').attr('id','btnSelect')
									.data('userId', item.id).text('조회')))
			.append($('<td>').html('<button id=\'btnDelete\'>삭제</button>'))
			.append($('<input type=\'hidden\' id=\'hidden_userId\'>').val(item.id))
			.appendTo('tbody');
		});//each
	}//userListResult
</script>
</head>
<body>
<div id="div1" data-name="park" data-desp="goods">제이쿼리 data함수 test</div>
<div class="container">
	<form id="form1"  class="form-horizontal">
		<h2>사용자 등록 및 수정</h2>
		<div class="form-group">		
			<label >아이디:</label>
			<input type="text"  class="form-control" name="id" >
		</div>	
		<div class="form-group">
			<label>이름:</label>
			<input type="text"  class="form-control"  name="name" >
		</div>				
		<div class="form-group">
			<label >성별:</label>
			<div class="radio">
				<label><input type="radio"  name="gender"  value="m">남</label>
			</div>
			<div class="radio">
				<label><input type="radio"  name="gender"  value="f">여</label>
			</div>	
		</div>	    
		<div class="form-group">   
			<label>역할:</label>
				<select class="form-control" name="role">
					   		<option value="Admin">관리자</option>
					   		<option value="User">사용자</option>
				</select>
		</div>  
		<div class="btn-group">      
				<input type="button"  class="btn btn-primary" value="등록"  id="btnInsert" /> 
				<input type="button"  class="btn btn-primary" value="수정"  id="btnUpdate" />
				<input type="button"  class="btn btn-primary" value="초기화" id="btnInit" />
		</div>
	</form>
</div>		
<hr/>		
<div class="container">	
	<h2>사용자 목록</h2>
	<table class="table text-center">
		<thead>
		<tr>
			<th class="text-center">아이디</th>
			<th class="text-center">이름</th>
			<th class="text-center">성별</th>
			<th class="text-center">거주지</th>
		</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>	
</body>
</html>