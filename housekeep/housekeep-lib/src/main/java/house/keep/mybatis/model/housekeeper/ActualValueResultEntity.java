package house.keep.mybatis.model.housekeeper;

import java.math.BigDecimal;

public class ActualValueResultEntity {
	private BigDecimal targetMonthlyBalance; // 月初残高
	private BigDecimal income; // 入金
	private BigDecimal remittance; // 送金
	private BigDecimal budget; // 当月予算
	private BigDecimal monthlyUsage; // 当月使用額
	private BigDecimal monthlyBalance; // 翌月繰越し


	/**
	 * @return budget
	 */
	public BigDecimal getBudget() {
		return budget;
	}
	/**
	 * @param budget セットする budget
	 */
	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
	/**
	 * @return targetMonthlyBalance
	 */
	public BigDecimal getTargetMonthlyBalance() {
		return targetMonthlyBalance;
	}
	/**
	 * @param targetMonthlyBalance セットする targetMonthlyBalance
	 */
	public void setTargetMonthlyBalance(BigDecimal targetMonthlyBalance) {
		this.targetMonthlyBalance = targetMonthlyBalance;
	}
	/**
	 * @return income
	 */
	public BigDecimal getIncome() {
		return income;
	}
	/**
	 * @param income セットする income
	 */
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	/**
	 * @return remittance
	 */
	public BigDecimal getRemittance() {
		return remittance;
	}
	/**
	 * @param remittance セットする remittance
	 */
	public void setRemittance(BigDecimal remittance) {
		this.remittance = remittance;
	}
	/**
	 * @return monthlyUsage
	 */
	public BigDecimal getMonthlyUsage() {
		return monthlyUsage;
	}
	/**
	 * @param monthlyUsage セットする monthlyUsage
	 */
	public void setMonthlyUsage(BigDecimal monthlyUsage) {
		this.monthlyUsage = monthlyUsage;
	}
	/**
	 * @return monthlyBalance
	 */
	public BigDecimal getMonthlyBalance() {
		return monthlyBalance;
	}
	/**
	 * @param monthlyBalance セットする monthlyBalance
	 */
	public void setMonthlyBalance(BigDecimal monthlyBalance) {
		this.monthlyBalance = monthlyBalance;
	}
}
