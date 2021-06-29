<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="house.keep.mybatis.HouseKeepModel"%>
<%@page import="java.util.Map"%>
<%@page import="house.keep.mybatis.model.housekeeper.OAccount241"%>
<%@page import="java.util.Map.Entry"%>

<%
	Map<OAccount241, HouseKeepModel> detailMap = (Map<OAccount241, HouseKeepModel>) request
			.getAttribute("detailMap");
%>

<!DOCTYPE html">
<html>
<head>
<meta charset="UTF-8">
<title>サンプルページ</title>
</head>
<body>
	<p>出金明細</p>

	<table border="1">
		<thead>
			<tr>
				<td>出金日</td>
				<td>出金金額</td>
				<td>明細項目</td>
				<td>明細金額</td>
			</tr>
		</thead>
		<tbody>
			<%
				for (Entry<OAccount241, HouseKeepModel> entry : detailMap.entrySet()) {
					SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd（E）");

					String outDate = dateFormatter.format(entry.getKey().getPaymentDate());
					HouseKeepModel houseKeepModel = entry.getValue();
			%>
			<tr>
				<td><%=outDate%></td>
				<td><fmt:formatNumber
						value="<%=entry.getKey().getOutAmount()%>" pattern="000,000" /></td>
				<td>-</td>
				<td>-</td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>食費</td>
				<td><fmt:formatNumber value="<%=houseKeepModel.getoFood()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>生活用品費</td>
				<td><fmt:formatNumber value="<%=houseKeepModel.getoLive()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>娯楽費</td>
				<td><fmt:formatNumber value="<%=houseKeepModel.getoWaste()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>その他費目</td>
				<td><fmt:formatNumber value="<%=houseKeepModel.getoOther()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>交際費</td>
				<td><fmt:formatNumber
						value="<%=houseKeepModel.getoEntertainment()%>" pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>モノ費</td>
				<td><fmt:formatNumber value="<%=houseKeepModel.getoGoods()%>"
						pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>繰り越し金</td>
				<td><fmt:formatNumber
						value="<%=houseKeepModel.getStayAmount()%>" pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>用途不明金</td>
				<td><fmt:formatNumber
						value="<%=houseKeepModel.getUnknownMoney()%>" pattern="000,000" /></td>
			</tr>
			<tr>
				<td>-</td>
				<td>-</td>
				<td>現金</td>
				<td><fmt:formatNumber value="<%=houseKeepModel.getMoney()%>"
						pattern="000,000" /></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<br />
</body>
</html>