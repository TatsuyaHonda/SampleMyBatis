package house.keep.budget.model;

import java.math.BigDecimal;

public class BudgetRowModel {

	/** 費目 - 大項目 */
	private String budgetMajorItem;

	/** 費目 - 中項目 */
	private String budgetMiddleItem;

	/** 対予算 */
	private Integer againstBudget;

	/** 予算金額 */
	private BigDecimal budgetAmount;

	/** 実績金額 */
	private BigDecimal actualAmount;

	/** 残高 */
	private BigDecimal balance;

	/**
	 * @return budgetMajorItem
	 */
	public String getBudgetMajorItem() {
		return budgetMajorItem;
	}

	/**
	 * @param budgetMajorItem セットする budgetMajorItem
	 */
	public void setBudgetMajorItem(String budgetMajorItem) {
		this.budgetMajorItem = budgetMajorItem;
	}

	/**
	 * @return budgetMiddleItem
	 */
	public String getBudgetMiddleItem() {
		return budgetMiddleItem;
	}

	/**
	 * @param budgetMiddleItem セットする budgetMiddleItem
	 */
	public void setBudgetMiddleItem(String budgetMiddleItem) {
		this.budgetMiddleItem = budgetMiddleItem;
	}

	/**
	 * @return againstBudget
	 */
	public Integer getAgainstBudget() {
		return againstBudget;
	}

	/**
	 * @param againstBudget セットする againstBudget
	 */
	public void setAgainstBudget(Integer againstBudget) {
		this.againstBudget = againstBudget;
	}

	/**
	 * @return budgetAmount
	 */
	public BigDecimal getBudgetAmount() {
		return budgetAmount;
	}

	/**
	 * @param budgetAmount セットする budgetAmount
	 */
	public void setBudgetAmount(BigDecimal budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	/**
	 * @return actualAmount
	 */
	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	/**
	 * @param actualAmount セットする actualAmount
	 */
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	/**
	 * @return balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance セットする balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
