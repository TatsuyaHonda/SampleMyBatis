package house.keep.budget.calculator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import house.keep.budget.BudgetCalculator;
import house.keep.budget.BudgetConstant.BudgetMajorItem;
import house.keep.budget.model.BudgetRowModel;
import house.keep.mybatis.MyBatisController;
import house.keep.mybatis.model.housekeeper.OAccount241;

/**
 * 当月予算
 */
@RequestScoped
public class InternalBudgetCalculator implements BudgetCalculator{

	@Inject
	private MyBatisController myBatisController;

	@Override
	public List<BudgetRowModel> calculate(Date targetMonth) {
		List<BudgetRowModel> budgetRowList = new LinkedList<>();

		// 当月予算
		OAccount241 targetMonthAccount241Entity = myBatisController.selectAccount241WherePaymentdate(targetMonth);
		BigDecimal paymentAmount = targetMonthAccount241Entity.getInAmount();
		BudgetRowModel BudgetRowPaymentAmount = new BudgetRowModel();
		BudgetRowPaymentAmount.setBudgetMajorItem(BudgetMajorItem.MONTHLY_BUDGET.name());

		return budgetRowList;
	}

}
