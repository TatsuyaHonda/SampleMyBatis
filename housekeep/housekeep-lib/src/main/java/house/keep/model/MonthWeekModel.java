package house.keep.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthWeekModel {
	// 給与日
	private Date paymentDate;
	// IN：口座241
	private BigDecimal oAccount241 = BigDecimal.ZERO;
	// OUT：合計出費
	private BigDecimal outSum = BigDecimal.ZERO;
	// OUT：クレジットカード
	private BigDecimal oCredit = BigDecimal.ZERO;
	//
	private List<MonthWeekModelAsWeek> detailModelList = new ArrayList<>();
	// 残額
	private BigDecimal lastAmount = BigDecimal.ZERO;

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getoAccount241() {
		return oAccount241;
	}

	public void setoAccount241(BigDecimal oAccount241) {
		this.oAccount241 = oAccount241;
	}

	public BigDecimal getOutSum() {
		return outSum;
	}

	public void setOutSum(BigDecimal outSum) {
		this.outSum = outSum;
	}

	public BigDecimal getoCredit() {
		return oCredit;
	}

	public void setoCredit(BigDecimal oCredit) {
		this.oCredit = oCredit;
	}

	public List<MonthWeekModelAsWeek> getDetailModelList() {
		return detailModelList;
	}

	public void setDetailModelList(List<MonthWeekModelAsWeek> detailModelList) {
		this.detailModelList = detailModelList;
	}

	public void addDetailModelList(MonthWeekModelAsWeek detailModel) {
		this.detailModelList.add(detailModel);
	}

	public BigDecimal getLastAmount() {
		return lastAmount;
	}

	public void setLastAmount(BigDecimal lastAmount) {
		this.lastAmount = lastAmount;
	}

}