<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
       body > div.container{
        	border: 3px solid #D6CDB7;
            margin-top: 10px;
        }
    </style>
    <script type="text/javascript">

	function fncAddProduct() {
		//Form 유효성 검증
		var name = $("input[name='prodName']").val();
		var detail = $("input[name='prodDetail']").val();
		var manuDate = $("input[name='manuDate']").val();
		var price = $("input[name='price']").val();
		
		if (name == null || name.length < 1) {
			alert("상품명은 반드시 입력하여야 합니다.");
			return;
		}
		if (detail == null || detail.length < 1) {
			alert("상품상세정보는 반드시 입력하여야 합니다.");
			return;
		}
		if (manuDate == null || manuDate.length < 1) {
			alert("제조일자는 반드시 입력하셔야 합니다.");
			return;
		}
		if (price == null || price.length < 1) {
			alert("가격은 반드시 입력하셔야 합니다.");
			return;
		}
		
	//	document.detailForm.action = '/product/addProduct';
	//	document.detailForm.submit();
		$("form").attr("method","POST").attr("action","/product/addProduct").submit();
	}
	
	//============= "가입"  Event 연결 =============
	 $(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$( "button.btn.btn-primary" ).on("click" , function() {
			fncAddUser();
		});
	});	
	
	
	//============= "취소"  Event 처리 및  연결 =============
	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$("a[href='#' ]").on("click" , function() {
			$("form")[0].reset();
		});
	});	
	
</script>
</head>

<body bgcolor="#ffffff" text="#000000">



<div class="navbar  navbar-default">
        <div class="container">
        	<a class="navbar-brand" href="/index.jsp">Model2 MVC Shop</a>
   		</div>
   	</div>

	<div class="container">
	
		<h1 class="bg-primary text-center">상품등록</h1>
		
		<!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal">
		
	
		  <div class="form-group">
		    <label for="password" class="col-sm-offset-1 col-sm-3 control-label">상품이름</label>
		    <div class="col-sm-4">
		      <input type="password" class="form-control" name="prodName" placeholder="상품명">
		    </div>
		  </div>

		  
		  <div class="form-group">
		    <label for="userName" class="col-sm-offset-1 col-sm-3 control-label">가격</label>
		    <div class="col-sm-4">
		      <input type="password" class="form-control" name="price" placeholder="가격">
		    </div>
		  </div>
		  

		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">상세정보</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="prodDetail" placeholder="상세정보">
		    </div>
		  </div>
		  
		
		  
		   <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">제조일자</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="manuDate" placeholder="제조일자">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">상품이미지</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="uploadFile" placeholder="상품이미지">
		    </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  > 등&nbsp;록</button>
			  <a class="btn btn-primary btn" href="#" role="button">취&nbsp;소</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
		
 	</div>
 	
 </body>
 </html>
 	