package house.keep.budget;

import javax.enterprise.context.RequestScoped;

import house.keep.budget.BudgetConstant.BudgetMajorItem;

@RequestScoped
public class BudgetCalculatorFactoryImpl implements BudgetCalculatorFactory {

	@Override
	public BudgetCalculator makeBudgetCalculator(BudgetMajorItem budgetCalculatorType) {
		switch (budgetCalculatorType) {
		case MONTHLY_BUDGET:
			break;
		case FIXED_COST:
			break;
		case VARIABLE_COSTS:
			break;
		case GOODS_PURCHASE_COST:
			break;
		case LUMP_SUM:
			break;
		case OTHER_BUDGET:
			break;
		case USAGE_UNKNOWN_GOLD:
			break;
		case RESERVE:
			break;
		}
		return null;
	}

}
