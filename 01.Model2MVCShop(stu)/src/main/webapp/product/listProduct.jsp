<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.model2.mvc.common.SearchVO"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	HashMap<String, Object> map =(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO = (SearchVO)request.getAttribute("searchVO");
	
	int total = 0;
	List<ProductVO> list = null;
	if(map != null){
		total = ((Integer)map.get("count")).intValue();
		list = (List<ProductVO>)map.get("list");
	}
	
	int currentPage = searchVO.getPage();
	
	int totalPage=0;
	if(total>0){
		totalPage=total/searchVO.getPageUnit();
		if(total%searchVO.getPageUnit()>0){
			totalPage +=1;
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetProductList(){
	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=manage" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							상품 관리
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 21 건수, 현재 1 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
		
	<tr class="ct_list_pop">
		<td align="center">3</td>
		<td></td>
		
				<td align="left">vaio vgn FS70B</td>
		
		<td></td>
		<td align="left">10000</td>
		<td></td>
		<td align="left">2012-12-14</td>
		<td></td>
		<td align="left">
		
			재고 없음
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	
	<tr class="ct_list_pop">
		<td align="center">2</td>
		<td></td>
		
				<td align="left">자전거</td>
		
		<td></td>
		<td align="left">10000</td>
		<td></td>
		<td align="left">2012-11-14</td>
		<td></td>
		<td align="left">
		
			재고 없음
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	
	<tr class="ct_list_pop">
		<td align="center">1</td>
		<td></td>
				
				<td align="left"><a href="/getProduct.do?prodNo=10002&menu=manage">보르도</a></td>
		
		<td></td>
		<td align="left">1170000</td>
		<td></td>
		<td align="left">2012-10-14</td>
		<td></td>
		<td align="left">
		
			판매중
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		
			<a href="/listProduct.do?page=1&menu=manage">1</a>
		
			<a href="/listProduct.do?page=2&menu=manage">2</a>
		
			<a href="/listProduct.do?page=3&menu=manage">3</a>
		
			<a href="/listProduct.do?page=4&menu=manage">4</a>
		
			<a href="/listProduct.do?page=5&menu=manage">5</a>
		
			<a href="/listProduct.do?page=6&menu=manage">6</a>
		
			<a href="/listProduct.do?page=7&menu=manage">7</a>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
