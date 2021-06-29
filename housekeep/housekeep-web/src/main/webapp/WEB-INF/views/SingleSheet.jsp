<%@page import="java.util.Map.Entry"%>
<%@page import="java.math.BigDecimal"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="house.keep.weekly.singlesheet.model.SingleSheetModel"%>
<%@page import="house.keep.weekly.model.WeeklySheetModel"%>
<%@page import="house.keep.mybatis.model.housekeeper.OOther"%>
<%@page import="house.keep.mybatis.model.housekeeper.ActualValueResultEntity"%>

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

		List<Map<Object, Object>> singleSheetWeeklyModel = (List<Map<Object, Object>>) request
				.getAttribute("singleSheetWeeklyModel");

		ActualValueResultEntity actualValue241Result = singleSheetModel.getActualValue241Result();
		ActualValueResultEntity actualValue461Result = singleSheetModel.getActualValue461Result();

		BigDecimal budget = actualValue241Result.getBudget(); // 当月予算
		BigDecimal outSum = actualValue241Result.getMonthlyUsage(); // 当月使用額（実績）

		BigDecimal totalFixedCosts = BigDecimal.ZERO; // 固定費計
		totalFixedCosts = totalFixedCosts.add(singleSheetModel.getHouseRentCost());
		totalFixedCosts = totalFixedCosts.add(singleSheetModel.getTennisCost());
		totalFixedCosts = totalFixedCosts.add(singleSheetModel.getCreditPayment());
		totalFixedCosts = totalFixedCosts.add(singleSheetModel.getRegularTrainFee());

		BigDecimal totalVariableCosts = BigDecimal.ZERO; // 変動費計
		for (Map<Object, Object> SingleSheetWeeklyMap : singleSheetWeeklyModel) {
			for (Entry<Object, Object> entry : SingleSheetWeeklyMap.entrySet()) {
				String keyString = (String) entry.getKey();
				if("週計".equals(keyString) && entry.getValue() != ""){
					BigDecimal valueBigDecimal = (BigDecimal) entry.getValue();
					totalVariableCosts = totalVariableCosts.add(valueBigDecimal);
				}
			}
		}

		BigDecimal totalCosts = BigDecimal.ZERO; // 当月使用額（固定費＋変動費）
		totalCosts = totalFixedCosts.add(totalVariableCosts);

		BigDecimal balance = budget.subtract(outSum); // 予算残高

		BigDecimal unknownUsageAmount = BigDecimal.ZERO; // 用途不明金
		unknownUsageAmount = outSum.subtract((totalFixedCosts.add(totalVariableCosts)));

		BigDecimal toushi = actualValue461Result.getMonthlyUsage(); // 今月投資額（461出金）

	%>

	<h3>当月予実照会</h3>
	<table border="1">
		<thead>
			<tr>
				<td align="right">予算枠</td><!-- 1 -->
				<td align="right">対予算</td><!-- 2 -->
				<td align="right">金額</td><!-- 3 -->
			</tr>
		</thead>

		<tbody>
			<tr>
				<td align="right">当月予算</td><!-- 1 -->
				<td align="right"><fmt:formatNumber value="<%=budget.divide(budget, 3, RoundingMode.HALF_UP)%>" pattern="##%" /></td><!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=budget%>" pattern="000,000" /></td><!-- 3 -->
			</tr>
			<tr>
				<td align="right">固定費合計</td><!-- 1 -->
				<td align="right"><fmt:formatNumber value="<%=totalFixedCosts.divide(budget, 3, RoundingMode.HALF_UP)%>" pattern="##%" /></td><!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=totalFixedCosts%>" pattern="000,000" /></td><!-- 3 -->
			</tr>
			<tr>
				<td align="right">変動費合計</td><!-- 1 -->
				<td align="right"><fmt:formatNumber value="<%=totalVariableCosts.divide(budget, 3, RoundingMode.HALF_UP)%>" pattern="##%" /></td><!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=totalVariableCosts%>" pattern="000,000" /></td><!-- 3 -->
			</tr>
			<tr>
				<td align="right">当月使用額（固定費＋変動費）</td><!-- 1 -->
				<td align="right"><fmt:formatNumber value="<%=totalCosts.divide(budget, 3, RoundingMode.HALF_UP)%>" pattern="##%" /></td><!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=totalCosts%>" pattern="000,000" /></td><!-- 3 -->
			</tr>
			<tr>
				<td align="right">当月使用額（実績）</td><!-- 1 -->
				<td align="right"><fmt:formatNumber value="<%=outSum.divide(budget, 3, RoundingMode.HALF_UP)%>" pattern="##%" /></td><!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=outSum%>" pattern="000,000" /></td><!-- 3 -->
			</tr>
			<tr>
				<td align="right">予算残高（当月予算 - 当月使用額（実績））</td><!-- 1 -->
				<td align="right"><fmt:formatNumber value="<%=balance.divide(budget, 3, RoundingMode.HALF_UP)%>" pattern="##%" /></td><!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=balance%>" pattern="000,000" /></td><!-- 3 -->
			</tr>
			<tr>
				<td align="right">用途不明金</td><!-- 1 -->
				<td align="right"><fmt:formatNumber value="<%=unknownUsageAmount.divide(budget, 3, RoundingMode.HALF_UP)%>" pattern="##%" /></td><!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=unknownUsageAmount%>" pattern="000,000" /></td><!-- 3 -->
			</tr>
			<tr>
				<td align="right">今月投資額（461出金）</td><!-- 1 -->
				<td align="right">-</td><!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=toushi%>" pattern="000,000" /></td><!-- 3 -->
			</tr>
		</tbody>
	</table>

	<h2>単票</h2>

	<table border="1">
		<thead>
			<tr>
				<td align="right">費目</td>
				<!-- 1 -->
				<td align="right">期間</td>
				<!-- 2 -->
				<td align="right">合計</td>
				<!-- 3 -->
				<td align="right">内訳</td>
				<!-- 4 -->
				<td align="right"></td>
				<!-- 5 -->
				<td align="right"></td>
				<!-- 6 -->
				<td align="right"></td>
				<!-- 7 -->
				<td align="right"></td>
				<!-- 8-->
				<td align="right"></td>
				<!-- 9 -->
				<td align="right"></td>
				<!-- 10 -->
			</tr>
		</thead>

		<tbody>
			<tr>
				<td align="right">給与</td>
				<!-- 1 -->
				<td align="right"><%=paymentDate%></td>
				<!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=paymentAmount%>" pattern="000,000" /></td>
				<!-- 3 -->
				<td align="right">-</td>
				<!-- 4 -->
				<td align="right">-</td>
				<!-- 5 -->
				<td align="right">-</td>
				<!-- 6 -->
				<td align="right">-</td>
				<!-- 7 -->
				<td align="right">-</td>
				<!-- 8-->
				<td align="right">-</td>
				<!-- 9 -->
				<td align="right">-</td>
				<!-- 10 -->
			</tr>
			<tr>
				<td align="right">支出合計</td>
				<!-- 1 -->
				<td align="right">-</td>
				<!-- 2 -->
				<td align="right">-</td>
				<!-- 3 -->
				<td align="right">-</td>
				<!-- 4 -->
				<td align="right">-</td>
				<!-- 5 -->
				<td align="right">-</td>
				<!-- 6 -->
				<td align="right">-</td>
				<!-- 7 -->
				<td align="right">-</td>
				<!-- 8-->
				<td align="right">-</td>
				<!-- 9 -->
				<td align="right">-</td>
				<!-- 10 -->
			</tr>
			<tr>
				<td align="right">固定費</td>
				<!-- 1 -->
				<td align="right">-</td>
				<!-- 2 -->
				<td align="right">-</td>
				<!-- 3 -->
				<td align="right">家賃</td>
				<!-- 4 -->
				<td align="right">レックテニス</td>
				<!-- 5 -->
				<td align="right">クレジット</td>
				<!-- 6 -->
				<td align="right">定期代</td>
				<!-- 7 -->
				<td align="right">-</td>
				<!-- 8-->
				<td align="right">-</td>
				<!-- 9 -->
				<td align="right">-</td>
				<!-- 10 -->
			</tr>
			<tr>
				<td align="right">-</td>
				<!-- 1 -->
				<td align="right">-</td>
				<!-- 2 -->
				<td align="right"><fmt:formatNumber value="<%=totalFixedCosts%>"
						pattern="000,000" /></td>
				<!-- 3 -->
				<td align="right"><fmt:formatNumber
						value="<%=singleSheetModel.getHouseRentCost()%>" pattern="000,000" /></td>
				<!-- 4 -->
				<td align="right"><fmt:formatNumber
						value="<%=singleSheetModel.getTennisCost()%>" pattern="000,000" /></td>
				<!-- 5 -->
				<td align="right"><fmt:formatNumber
						value="<%=singleSheetModel.getCreditPayment()%>" pattern="000,000" /></td>
				<!-- 6 -->
				<td align="right"><fmt:formatNumber
						value="<%=singleSheetModel.getRegularTrainFee()%>"
						pattern="000,000" /></td>
				<!-- 7 -->
				<td align="right">-</td>
				<!-- 8-->
				<td align="right">-</td>
				<!-- 9 -->
				<td align="right">-</td>
				<!-- 10 -->
			</tr>
		</tbody>
	</table>

	<table border="1">
		<tbody>
			<%
				boolean isNotHeaderWritten = true;
				for (Map<Object, Object> SingleSheetWeeklyMap : singleSheetWeeklyModel) {
			%>
			<tr>
				<%
					// KEY
						if (isNotHeaderWritten) {
							for (Entry<Object, Object> entry : SingleSheetWeeklyMap.entrySet()) {
								String keyString = (String) entry.getKey();
				%>
				<td align="right"><%=keyString%></td>
				<%
					}
						}
				isNotHeaderWritten = false;
				%>
			</tr>
			<tr>
				<%
					// VALUE
						for (Entry<Object, Object> entry : SingleSheetWeeklyMap.entrySet()) {
							try {
								BigDecimal valueBigDecimal = (BigDecimal) entry.getValue();
				%>
				<td align="right"><fmt:formatNumber value="<%=valueBigDecimal%>"
						pattern="000,000" /></td>
				<%
					} catch (ClassCastException classCastException) {
								String valueString = (String) entry.getValue();
				%>
				<td align="right"><%=valueString%></td>
				<%
					}
						}
					}
				%>
			</tr>
		</tbody>
	</table>
	<br />
</body>
</html>