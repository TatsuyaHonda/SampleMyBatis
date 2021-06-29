package house.keep.budget;

import java.util.Date;
import java.util.List;

import house.keep.budget.model.BudgetRowModel;

public interface BudgetCalculator {

	// test edit
	List<BudgetRowModel> calculate(Date targetMonthDate);

}
