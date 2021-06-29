<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="house.keep.mybatis.HouseKeepModel"%>

<%
	List<HouseKeepModel> list = (List<HouseKeepModel>) request.getAttribute("list");
%>

<!DOCTYPE html">
<html>
<head>
<style type="text/css">
tr.account461 {
	background-color: #bde9ba;
}

tr.accountConst {
	background-color: #ffd78c;
}
</style>
<meta charset="UTF-8">
<title>サンプルページ</title>
</head>
<body>
	<p>Hellow JSP!</p>

	<%
		for (HouseKeepModel item : list) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
			String paymentDate = dateFormatter.format(item.getPaymentDate());
	%>
	<table border="1">
		<thead>
			<tr>
				<td><%=paymentDate%></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>NAME</td>
				<td>IN</td>
				<td>OUT</td>
				<td>DETAIL</td>
			</tr>
			<tr>
				<td>給与</td>
				<td><fmt:formatNumber value="<%=item.getoAccount241()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr>
				<td>出費合計</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getOutSum()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>出費明細.固定費</td>
				<td><fmt:formatNumber value="<%=item.getoConstants()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.クレジットカード</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getoCredit()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.食費</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getoFood()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.生活用品費</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getoLive()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.娯楽費</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getoWaste()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.その他費目</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getoOther()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.交際費</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getoEntertainment()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.常駐家庭用品費</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getoGoods()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.用途不明金</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getUnknownMoney()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>繰り越し金</td>
				<td><fmt:formatNumber value="<%=item.getStayAmount()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr id="account461">
				<td>口座461：前月繰り越し金</td>
				<td><fmt:formatNumber value="<%=item.getStartSum461()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr id="account461">
				<td>口座461：入金計</td>
				<td><fmt:formatNumber value="<%=item.getInAmount461()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr id="account461">
				<td>口座461：出金計</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getOutSum461()%>"
						pattern="000,000" /></td>
			</tr>
			<tr id="account461">
				<td>口座461：当月残高</td>
				<td><fmt:formatNumber value="<%=item.getStayAmount461()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr id="accountConst">
				<td>口座定期：前月繰り越し金</td>
				<td><fmt:formatNumber value="<%=item.getStartSumConst()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr id="accountConst">
				<td>口座定期：入金計</td>
				<td><fmt:formatNumber value="<%=item.getInAmountConst()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr id="accountConst">
				<td>口座定期：出金計</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=item.getOutSumConst()%>"
						pattern="000,000" /></td>
			</tr>
			<tr id="accountConst">
				<td>口座定期：当月残高</td>
				<td><fmt:formatNumber value="<%=item.getStayAmountConst()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
		</tbody>
	</table>
	<br />
	<%
		}
	%>
</body>
</html>