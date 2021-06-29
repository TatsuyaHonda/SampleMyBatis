package house.keep.budget;

import house.keep.budget.BudgetConstant.BudgetMajorItem;

public interface BudgetCalculatorFactory {

	BudgetCalculator makeBudgetCalculator(BudgetMajorItem budgetCalculatorType);

}
