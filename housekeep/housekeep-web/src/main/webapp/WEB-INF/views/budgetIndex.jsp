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
<%@page import="house.keep.budget.model.BudgetRowModel"%>


<!DOCTYPE html">
<html>
<head>
<meta charset="UTF-8">
<title>予算月初設定／確認／臨時登録</title>
</head>
<body>
	<%
	List<BudgetRowModel> budgetRowList = (List<BudgetRowModel>) request.getAttribute("budgetRowList");
	%>

	<h2>予算月初設定／確認／臨時登録</h2>
	<table border="1">
		<thead>
			<tr>
				<td align="right">予算枠</td>			<!-- 1 -->
				<td align="right">-</td>					<!-- 2 -->
				<td align="right">対予算</td>			<!-- 3 -->
				<td align="right">予算金額</td>		<!-- 4 -->
				<td align="right">実績金額</td>		<!-- 5 -->
				<td align="right">残高</td>			<!-- 6 -->
			</tr>
		</thead>
		<tbody>
			<%
				for (BudgetRowModel budgetRow : budgetRowList) {
					String budgetMajorItem = budgetRow.getBudgetMajorItem();
					String budgetMiddleItem = budgetRow.getBudgetMiddleItem();
					Integer againstBudget = budgetRow.getAgainstBudget();
					BigDecimal budgetAmount = budgetRow.getBudgetAmount();
					BigDecimal actualAmount = budgetRow.getActualAmount();
					BigDecimal balance = budgetRow.getBalance();
			%>
			<tr>
				<td align="right"><%=budgetMajorItem%></td>																						<!-- 1 -->
				<td align="right"><%=budgetMiddleItem%></td>																						<!-- 2 -->
				<td align="right"><%=againstBudget%></td>																							<!-- 3 -->
				<td align="right"><fmt:formatNumber value="<%=budgetAmount%>" pattern="000,000" /><%=%></td>	<!-- 4 -->
				<td align="right"><fmt:formatNumber value="<%=actualAmount%>" pattern="000,000" /><%=%></td>		<!-- 5 -->
				<td align="right"><fmt:formatNumber value="<%=balance%>" pattern="000,000" /><%=%></td>				<!-- 6 -->
				<%
				}
				%>
			</tr>
		</tbody>
	</table>
	<br />
</body>
</html>