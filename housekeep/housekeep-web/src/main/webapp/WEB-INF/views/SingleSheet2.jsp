<%@page import="java.math.BigDecimal"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="house.keep.weekly.singlesheet.model.SingleSheetModel"%>
<%@page import="house.keep.weekly.model.WeeklySheetModel"%>
<%@page import="house.keep.mybatis.model.housekeeper.OOther"%>

<!--  %
	SingleSheetModel singleSheetModel = (SingleSheetModel) request.getAttribute("singleSheetModel");
%>  -->

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
<title>単票</title>
</head>
<body>
	<%
		SingleSheetModel singleSheetModel = (SingleSheetModel) request.getAttribute("singleSheetModel");

		SimpleDateFormat dateFormatterFull = new SimpleDateFormat("yyyy/MM/dd（E）");
		SimpleDateFormat dateFormatterPlain = new SimpleDateFormat("MMdd");

		String paymentDate = dateFormatterPlain.format(singleSheetModel.getSingleSheetFrom());
		BigDecimal paymentAmount = singleSheetModel.getPaymentAmount();

		BigDecimal totalFixedCosts = BigDecimal.ZERO;
		totalFixedCosts = totalFixedCosts.add(singleSheetModel.getHouseRentCost());
		totalFixedCosts = totalFixedCosts.add(singleSheetModel.getTennisCost());
		totalFixedCosts = totalFixedCosts.add(singleSheetModel.getCreditPayment());
		totalFixedCosts = totalFixedCosts.add(singleSheetModel.getRegularTrainFee());

		BigDecimal weeklySumAll = BigDecimal.ZERO;

		BigDecimal totalExpenditure = BigDecimal.ZERO;
	%>

	<h2>単票</h2>

	<table border="1">
		<thead>
			<tr>
				<td>費目</td><!-- 1 -->
				<td>期間</td><!-- 2 -->
				<td>合計</td><!-- 3 -->
				<td>内訳</td><!-- 4 -->
				<td></td><!-- 5 -->
				<td></td><!-- 6 -->
				<td></td><!-- 7 -->
				<td></td><!-- 8-->
				<td></td><!-- 9 -->
				<td></td><!-- 10 -->
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>給与</td><!-- 1 -->
				<td><%=paymentDate%></td><!-- 2 -->
				<td><fmt:formatNumber value="<%=paymentAmount%>" pattern="000,000" /></td><!-- 3 -->
				<td>-</td><!-- 4 -->
				<td>-</td><!-- 5 -->
				<td>-</td><!-- 6 -->
				<td>-</td><!-- 7 -->
				<td>-</td><!-- 8-->
				<td>-</td><!-- 9 -->
				<td>-</td><!-- 10 -->
			</tr>
			<tr>
				<td>固定費</td><!-- 1 -->
				<td>-</td><!-- 2 -->
				<td>-</td><!-- 3 -->
				<td>家賃</td><!-- 4 -->
				<td>レックテニス</td><!-- 5 -->
				<td>クレジット</td><!-- 6 -->
				<td>定期代</td><!-- 7 -->
				<td>-</td><!-- 8-->
				<td>-</td><!-- 9 -->
				<td>-</td><!-- 10 -->
			</tr>
			<tr>
				<td>-</td><!-- 1 -->
				<td>-</td><!-- 2 -->
				<td><fmt:formatNumber value="<%=totalFixedCosts%>" pattern="000,000" /></td><!-- 3 -->
				<td><fmt:formatNumber value="<%=singleSheetModel.getHouseRentCost()%>" pattern="000,000" /></td><!-- 4 -->
				<td><fmt:formatNumber value="<%=singleSheetModel.getTennisCost()%>" pattern="000,000" /></td><!-- 5 -->
				<td><fmt:formatNumber value="<%=singleSheetModel.getCreditPayment()%>" pattern="000,000" /></td><!-- 6 -->
				<td><fmt:formatNumber value="<%=singleSheetModel.getRegularTrainFee()%>" pattern="000,000" /></td><!-- 7 -->
				<td>-</td><!-- 8-->
				<td>-</td><!-- 9 -->
				<td>-</td><!-- 10 -->
			</tr>
		</tbody>
	</table>

	<table border="1">
		<tbody>
			<%
				for (int i = 0; i < singleSheetModel.getWeeklySheetModelSortedList().size(); i++) {
					WeeklySheetModel weeklySheetModel = singleSheetModel.getWeeklySheetModelSortedList().get(i);

					String weeklySheetFromString = dateFormatterPlain.format(weeklySheetModel.getWeeklySheetFrom());
					String weeklySheetToString = dateFormatterPlain.format(weeklySheetModel.getWeeklySheetTo());

					List<OOther> otherEntityList = weeklySheetModel.getOtherList();

					BigDecimal weeklySum = BigDecimal.ZERO;
			%>
				<tr>
					<td>week<%=i%></td><!-- 1 -->
					<td><%=weeklySheetFromString%> - <%=weeklySheetToString%></td><!-- 2 -->
			<%
					for(OOther other : otherEntityList){
						BigDecimal amount = other.getAmount();
						String expense = other.getExpense();
						String detail = other.getDetail();
						weeklySum = weeklySum.add(amount);
						%>
					<td><%=expense%> | <%=detail%> | <fmt:formatNumber value="<%=amount%>" pattern="000,000" /></td>
			<%
					}
			%>
					<td>変動費計 | <fmt:formatNumber value="<%=weeklySumAll%>" pattern="000,000" /></td><!-- 3 -->
			<%
				}
			%>
				</tr>
		</tbody>
	</table>
	<br />
</body>
</html>