<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="house.keep.model.account241.Account241AsMonthModel"%>
<%@page import="house.keep.model.account241.DateBigDecimalEntry"%>
<%@page import="house.keep.mybatis.HouseKeepModel"%>
<%@page import="house.keep.mybatis.model.housekeeper.OOther"%>

<%
	Map<Account241AsMonthModel, HouseKeepModel> monthMap = (Map<Account241AsMonthModel, HouseKeepModel>) request
			.getAttribute("monthMap");

	Entry<Account241AsMonthModel, HouseKeepModel> entry = monthMap.entrySet().iterator().next();
	Account241AsMonthModel monthModel = entry.getKey();
	HouseKeepModel houseKeepModel = entry.getValue();

	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd（E）");

	String paymentDate = dateFormatter.format(monthModel.getPaymentDate());

	BigDecimal outSumAmount = monthModel.getOutSumAmount();
	BigDecimal stayAmount = BigDecimal.ZERO;
	stayAmount = monthModel.getPaymentAmount().subtract(outSumAmount);

	BigDecimal sumWithdrawalAmount = BigDecimal.ZERO;
	for (DateBigDecimalEntry sumWithdrawalAmountEntry : monthModel.getWithdrawalEntryList()) {
		sumWithdrawalAmount = sumWithdrawalAmount.add(sumWithdrawalAmountEntry.getAmount());
	}

	BigDecimal paymentAmount = monthModel.getPaymentAmount();
%>

<!DOCTYPE html">
<html>
<head>
<meta charset="UTF-8">
<title>サンプルページ</title>
</head>
<body>
	<p>月実績</p>

	<table border="1">
		<thead>
			<tr>
				<td align="right">開始日</td>
				<td align="right">項目</td>
				<td align="right">入金金額</td>
				<td align="right">出金金額</td>
				<td align="right">出金パーセント</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="right"><%=paymentDate%></td>
				<td align="right">給与</td>
				<td align="right"><fmt:formatNumber value="<%=paymentAmount%>" pattern="000,000" /></td>
				<td align="right">-</td>
				<td align="right">-</td>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">家賃</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=monthModel.getRentAmount()%>" pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=monthModel.getRentAmount().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">クレジット</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=monthModel.getCreditAmount()%>" pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=monthModel.getCreditAmount().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">テニス</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=monthModel.getTennisAmount()%>" pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=monthModel.getTennisAmount().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">出金</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=sumWithdrawalAmount%>" pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=sumWithdrawalAmount.divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">支出合計</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=outSumAmount%>" pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=outSumAmount.divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">残金</td>
				<td align="right"><fmt:formatNumber value="<%=stayAmount%>" pattern="000,000" /></td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=stayAmount.divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">-</td>
				<td align="right">-</td>
				<td align="right">-</td>
				<td align="right">-</td>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">食費</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoFood()%>"
						pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoFood().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">生活用品費</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoLive()%>"
						pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoLive().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">娯楽費</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoWaste()%>"
						pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoWaste().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">その他費目</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoOther()%>"
						pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoOther().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">交際費</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber
						value="<%=houseKeepModel.getoEntertainment()%>" pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoEntertainment().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">モノ費</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoGoods()%>"
						pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getoGoods().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">繰り越し金</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber
						value="<%=houseKeepModel.getStayAmount()%>" pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getStayAmount().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">用途不明金</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber
						value="<%=houseKeepModel.getUnknownMoney()%>" pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getUnknownMoney().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">現金</td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getMoney()%>"
						pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=houseKeepModel.getMoney().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<tr>
				<td align="right">-</td>
				<td align="right">-</td>
				<td align="right">-</td>
				<td align="right">-</td>
				<td align="right">-</td>
			</tr>
			<%
				List<OOther> otherDetailList = (List<OOther>) request.getAttribute("otherDetailList");
				for (OOther otherDetail : otherDetailList) {
			%>
			<tr>
				<td align="right">-</td>
				<td align="right"><%=otherDetail.getExpense()%></td>
				<td align="right">-</td>
				<td align="right"><fmt:formatNumber value="<%=otherDetail.getAmount()%>"
						pattern="000,000" /></td>
				<td align="right"><fmt:formatNumber value="<%=otherDetail.getAmount().divide(paymentAmount, 2, RoundingMode.HALF_UP)%>" pattern="##%" /><br>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<br />
</body>
</html>