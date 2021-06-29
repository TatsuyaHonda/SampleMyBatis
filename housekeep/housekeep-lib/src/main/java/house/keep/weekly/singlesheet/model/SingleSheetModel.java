package house.keep.weekly.singlesheet.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import house.keep.mybatis.model.housekeeper.ActualValueResultEntity;
import house.keep.weekly.model.WeeklySheetModel;

/**
 * 単票モデル
 *
 * @author Tatsuya
 */
public class SingleSheetModel {

	// 給与
	private Date payday;
	private BigDecimal paymentAmount;

	// 単票期間(From)
	private Date singleSheetFrom;

	// 単票期間(To)
	private Date singleSheetTo;

	// 週票リスト
	private List<WeeklySheetModel> weeklySheetModelSortedList;

	// 単票単位明細
	// 明細.家賃 house rent cost
	private BigDecimal houseRentCost;
	// 明細.レックテニス tennis cost
	private BigDecimal tennisCost;
	// 明細.クレジット Credit payment
	private BigDecimal creditPayment;
	// 明細.定期代 Regular train fee
	private BigDecimal regularTrainFee;

	// 実績値明細.口座241
	private ActualValueResultEntity ActualValue241Result;
	// 実績値明細.口座241
	private ActualValueResultEntity ActualValue461Result;

	/**
	 * @return paymentAmount
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	/**
	 * @param paymentAmount セットする paymentAmount
	 */
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	/**
	 * @return payday
	 */
	public Date getPayday() {
		return payday;
	}
	/**
	 * @param payday セットする payday
	 */
	public void setPayday(Date payday) {
		this.payday = payday;
	}
	/**
	 * @return singleSheetFrom
	 */
	public Date getSingleSheetFrom() {
		return singleSheetFrom;
	}
	/**
	 * @param singleSheetFrom セットする singleSheetFrom
	 */
	public void setSingleSheetFrom(Date singleSheetFrom) {
		this.singleSheetFrom = singleSheetFrom;
	}
	/**
	 * @return singleSheetTo
	 */
	public Date getSingleSheetTo() {
		return singleSheetTo;
	}
	/**
	 * @param singleSheetTo セットする singleSheetTo
	 */
	public void setSingleSheetTo(Date singleSheetTo) {
		this.singleSheetTo = singleSheetTo;
	}
	/**
	 * @return weeklySheetModelSortedList
	 */
	public List<WeeklySheetModel> getWeeklySheetModelSortedList() {
		return weeklySheetModelSortedList;
	}
	/**
	 * @param weeklySheetModelSortedList セットする weeklySheetModelSortedList
	 */
	public void setWeeklySheetModelSortedList(
			List<WeeklySheetModel> weeklySheetModelSortedList) {
		this.weeklySheetModelSortedList = weeklySheetModelSortedList;
	}
	/**
	 * @return houseRentCost
	 */
	public BigDecimal getHouseRentCost() {
		return houseRentCost;
	}
	/**
	 * @param houseRentCost セットする houseRentCost
	 */
	public void setHouseRentCost(BigDecimal houseRentCost) {
		this.houseRentCost = houseRentCost;
	}
	/**
	 * @return tennisCost
	 */
	public BigDecimal getTennisCost() {
		return tennisCost;
	}
	/**
	 * @param tennisCost セットする tennisCost
	 */
	public void setTennisCost(BigDecimal tennisCost) {
		this.tennisCost = tennisCost;
	}
	/**
	 * @return creditPayment
	 */
	public BigDecimal getCreditPayment() {
		return creditPayment;
	}
	/**
	 * @param creditPayment セットする creditPayment
	 */
	public void setCreditPayment(BigDecimal creditPayment) {
		this.creditPayment = creditPayment;
	}
	/**
	 * @return regularTrainFee
	 */
	public BigDecimal getRegularTrainFee() {
		return regularTrainFee;
	}
	/**
	 * @param regularTrainFee セットする regularTrainFee
	 */
	public void setRegularTrainFee(BigDecimal regularTrainFee) {
		this.regularTrainFee = regularTrainFee;
	}
	/**
	 * @return actualValue241Result
	 */
	public ActualValueResultEntity getActualValue241Result() {
		return ActualValue241Result;
	}
	/**
	 * @param actualValue241Result セットする actualValue241Result
	 */
	public void setActualValue241Result(ActualValueResultEntity actualValue241Result) {
		ActualValue241Result = actualValue241Result;
	}
	/**
	 * @return actualValue461Result
	 */
	public ActualValueResultEntity getActualValue461Result() {
		return ActualValue461Result;
	}
	/**
	 * @param actualValue461Result セットする actualValue461Result
	 */
	public void setActualValue461Result(ActualValueResultEntity actualValue461Result) {
		ActualValue461Result = actualValue461Result;
	}
}