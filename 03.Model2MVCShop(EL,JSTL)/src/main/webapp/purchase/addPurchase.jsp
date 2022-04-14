<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.service.domain.User" %>


<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>

<% Purchase vo = (Purchase)request.getAttribute("purchase"); %>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=<%=vo.getTranNo()%>" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>${product.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td>${user.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		<select 	name="paymentOption"		class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20">
			<option value="1" selected="selected">���ݱ���</option>
				<option value="2">�ſ뱸��</option>
				</select>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><input type="text" name="receiverName" 	class="ct_input_g" 
						style="width: 100px; height: 19px" maxLength="20" value="${user.userName}" /></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><input 	type="text" name="receiverPhone" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20"   value="${user.phone}"/></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><input 	type="text" name="receiverAddr" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" value="${user.addr}"  /></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>${purchase.DivyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><input 	type="text"  name="receiverDate" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" value="${purchase.divyDate }"/>
			<img 	src="../images/ct_icon_date.gif" width="15" height="15"	
						onclick="show_calendar('document.addPurchase.receiverDate', document.addPurchase.receiverDate.value)"/></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>