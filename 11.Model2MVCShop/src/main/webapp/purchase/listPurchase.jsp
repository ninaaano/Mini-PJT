<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
	
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
   <!-- jQuery UI toolTip 사용 CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip 사용 JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	  body {
            padding-top : 50px;
        }
    </style>
     
     
     </head>
     

<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetUserList(currentPage) {
	$("#currentPage").val(currentPage)
	$("form").attr("method" , "POST").attr("action" , "/purchase/listPurchase").submit();
}

$(function() {
	
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$( "td:nth-child(2)" ).on("click" , function() {
		 self.location ="/purchase/getPurchase?tranNo="+$(this).data("value");
	});
				
	//==> userId LINK Event End User 에게 보일수 있도록 
	$( "td:nth-child(2)" ).css("color" , "red");
	
});	


//============= userId 에 회원정보보기  Event  처리 (double Click)=============
 $(function() {
	 
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$(  "td:nth-child(5) > i" ).on("click" , function() {

			var tranNo = $(this).data("value");
		
			$.ajax( 
					{
						url : "/purchase/json/findPurchase2/"+tranNo ,
						method : "GET" ,
						dataType : "json" ,
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData , status) {
				
							var displayValue = "<h3>"
														
														+"회원아이디 : "+JSONData.buyer.userId+"<br/>"
														+"전화번호 : "+JSONData.receiverPhone+"<br/>"
														+"배송현황 : "+JSONData.tranCode+"<br/>"
														
														+"</h3>";
		
							$("h3").remove();
							$( "#"+tranNo+"" ).html(displayValue);
						}
				});
				////////////////////////////////////////////////////////////////////////////////////////////
			
	});
	
	//==> userId LINK Event End User 에게 보일수 있도록 
	$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
	$("h7").css("color" , "red");
	
	//==> 아래와 같이 정의한 이유는 ??
	$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
});	

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

		<jsp:include page="/layout/toolbar.jsp" />
	
	
	<div class="container">

		<form name="detailForm" action="/listPurchase" method="post">

		<div class="page-header text-info">
	       <h3>구매목록조회</h3>
	    </div>

    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div>

	      <table class="table table-hover table-striped" >

		
		<thead>
          <tr>
            <th align="center">No</th>
            <th align="left" >회원ID</th>
            <th align="left">전화번호</th>
            <th align="left">배송현황</th>
            <th align="left">배송희망일자</th>
          </tr>
        
			<c:if test="${resultPage.totalCount == 0 }">
					<tr class="ct_list_pop"> 
						<td align="center" colspan="100%">
							구매 내역이 없습니다.
						</td>
					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</c:if>
		</thead>


		<tbody>
		
		 <c:set var = "i" value = "0"/>
   <c:forEach var ="purchase" items ="${list }">
      <c:set var="i"  value = "${i+1 }"/>
      <tr class="ct_list_pop">
      <td align="center">
      <input type="hidden" name="tranNo"  value="${purchase.tranNo }" />
      ${purchase.tranNo}
      </td>
		<td align="left" title="Click : 구매정보확인" data-value="${purchase.tranNo}" >
      ${purchase.buyer.userId}
      </td>
            
			
						<td align="left">${purchase.receiverPhone}</td>

						
						<td align="left">
						<c:choose>
								<c:when test="${purchase.tranCode=='0' }">
									배송완료
								</c:when>
								<c:when test="${purchase.tranCode=='1' }">
									배송중
								</c:when>
								<c:otherwise>
									구매완료
								</c:otherwise>
						</c:choose> 
						</td>
						
						<td align="left">${purchase.divyDate}</td>
						
						
						<td align="left">
						<c:if test="${purchase.tranCode=='2'}">
							<a href="/updateTranCode?menu=search&tranCode=${purchase.tranCode}&prodNo=${purchase.purchaseProd.prodNo}">물건도착</a>
						</c:if>
						</td>
						
						
						
					</tr>
					<tr>

						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
					<input type="hidden" >
			
          </c:forEach>
				
		</tbody>
      
      </table>
      </form>
		</div>
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->
	
</body>

</html>