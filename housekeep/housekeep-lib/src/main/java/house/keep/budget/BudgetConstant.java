package house.keep.budget;

public interface BudgetConstant {

	public enum BudgetTableHeader {
		BUDGET_MAJOR_ITEM("大項目"),
		BUDGET_MIDDLE_ITEM("中項目"),
		AGAINST_BUDGET("対予算"),
		BUDGET_AMOUNT("予算金額"),
		ACTUAL_AMOUNT("実績金額"),
		BALANCE("残高");

		private final String title;

		private BudgetTableHeader(final String title) {
			this.title = title;
		}

		public String getString() {
			return this.title;
		}
	}

	public enum BudgetMajorItem {
		MONTHLY_BUDGET("当月予算"),
		FIXED_COST("固定費"),
		VARIABLE_COSTS("変動費"),
		GOODS_PURCHASE_COST("物品購入費"),
		LUMP_SUM("一時金"),
		OTHER_BUDGET("その他予算"),
		USAGE_UNKNOWN_GOLD("用途不明金"),
		RESERVE("積立");

		private final String title;

		private BudgetMajorItem(final String title) {
			this.title = title;
		}

		public String getString() {
			return this.title;
		}
	}

	public enum FixedCost {
		HOUSE_RENT("家賃"),
		CREDIT("クレジット"),
		TRAIN_PASS_PRICE("定期代"),
		TENNIS("テニス");

		private final String title;

		private FixedCost(final String title) {
			this.title = title;
		}

		public String getString() {
			return this.title;
		}
	}

	public enum VariableCosts {
		FOOD_EXPENSES("食費"),
		LIVING_GOODS_COSTS("生活用品費"),
		WASTE_EXPENSES("娯楽費"),
		ENTERTAINMENT_EXPENSES("交際費"),
		TRANSPORTATION_CHARGE("交通費チャージ");

		private final String title;

		private VariableCosts(final String title) {
			this.title = title;
		}

		public String getString() {
			return this.title;
		}
	}

	public enum GoodsPurchaseCost {
		PLAIN_CLOTHES("私服代"),
		WORK_SUPPLIES_COSTS("仕事用品費"),
		HOUSEHOLD_EXPENSES("家財費"),
		ART("アート"),
		QUALITYOFLIFE("生活の質"),
		BOOKS("書籍代");

		private final String title;

		private GoodsPurchaseCost(final String title) {
			this.title = title;
		}

		public String getString() {
			return this.title;
		}
	}
}
