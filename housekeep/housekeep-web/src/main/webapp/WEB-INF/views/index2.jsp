<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="house.keep.model.MonthWeekModel"%>
<%@page import="house.keep.model.MonthWeekModelAsWeek"%>

<%
	MonthWeekModel monthWeekModel = (MonthWeekModel) request.getAttribute("monthWeekModel");
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
<title>月週間明細</title>
</head>
<body>
	<%
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd（E）");
		String paymentDate = dateFormatter.format(monthWeekModel.getPaymentDate());
	%>
	<!-- 月単位 -->
	<table border="1">
		<thead>
			<tr>
				<td><%=paymentDate%></td>
				<td>A</td>
				<td>B</td>
				<td>C</td>
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
				<td><fmt:formatNumber
						value="<%=monthWeekModel.getoAccount241()%>" pattern="000,000" /></td>
				<td>-</td>
				<td>-</td>
			</tr>
			<tr>
				<td>出費合計</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=monthWeekModel.getOutSum()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr>
				<td>残高</td>
				<td><fmt:formatNumber value="<%=monthWeekModel.getLastAmount()%>" pattern="000,000" /></td>
				<td>-</td>
				<td>-</td>
			</tr>
			<tr>
				<td>出費明細.クレジットカード</td>
				<td>-</td>
				<td><fmt:formatNumber value="<%=monthWeekModel.getoCredit()%>"
						pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<%
				for (int i = 0; i < monthWeekModel.getDetailModelList().size(); i++) {
					MonthWeekModelAsWeek detailModel = monthWeekModel.getDetailModelList().get(i);

					String detailPaymentDate = dateFormatter.format(detailModel.getPaymentDate());
			%>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td><%=detailPaymentDate%></td>
				<td><fmt:formatNumber value="<%=detailModel.getOut246()%>" pattern="000,000" /></td>
				<td>-</td>
			</tr>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td>-</td>
				<td>出費明細.食費</td>
				<td><fmt:formatNumber value="<%=detailModel.getoFood()%>"pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td>-</td>
				<td>出費明細.生活用品費</td>
				<td><fmt:formatNumber value="<%=detailModel.getoLive()%>"pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td>-</td>
				<td>出費明細.娯楽費</td>
				<td><fmt:formatNumber value="<%=detailModel.getoWaste()%>"pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td>-</td>
				<td>出費明細.その他費目</td>
				<td><fmt:formatNumber value="<%=detailModel.getoOther()%>"pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td>-</td>
				<td>出費明細.交際費</td>
				<td><fmt:formatNumber value="<%=detailModel.getoEntertainment()%>" pattern="000,000" /></td>
			</tr>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td>-</td>
				<td>出費明細.常駐家庭用品費</td>
				<td><fmt:formatNumber value="<%=detailModel.getoGoods()%>"pattern="000,000" /></td>
			</tr>
			<%
				if (detailModel.getUnknownMoney() != null){
			%>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td>-</td>
				<td>出費明細.用途不明金</td>
				<td><fmt:formatNumber value="<%=detailModel.getUnknownMoney()%>" pattern="000,000" /></td>
			</tr>
			<%
				} else {
			%>
			<tr>
				<td>出費明細.WEEK<%=i%></td>
				<td>-</td>
				<td>出費明細.現金</td>
				<td><fmt:formatNumber value="<%=detailModel.getCash()%>" pattern="000,000" /></td>
			</tr>
			<% } %>
			<%
				}
			%>
		</tbody>
	</table>
	<br />
</body>
</html>