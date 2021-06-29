package house.keep.weekly.model;

import java.util.Date;
import java.util.List;

import house.keep.mybatis.model.housekeeper.OOther;

/**
 * 週票モデル
 *
 * @author Tatsuya
 */
public class WeeklySheetModel {

	// 週票期間(From)
	private Date weeklySheetFrom;

	// 週票期間(To)
	private Date weeklySheetTo;

	private List<OOther> otherList;

	//	// 明細.食費 Food expenses
	//	private BigDecimal foodExpenses;
	//	// 明細.生活用品費 Livingware costs
	//	private BigDecimal livingwareCosts;
	//	// 明細.娯楽費 wast expenses
	//	private BigDecimal wastExpenses;
	//	// 明細.外食コンビニ食費 eatingOutAndConvenienceStores
	//	private BigDecimal eatingOutAndConvenienceStores;
	//	// 明細.その他 Other expenses
	//	private BigDecimal otherExpenses;
	//	// 明細.交際費 Entertainment expenses
	//	private BigDecimal entertainmentExpenses;
	//	// 明細.家具服費 Furniture clothing costs
	//	private BigDecimal furnitureClothingCosts;

	/**
	 * @return weeklySheetFrom
	 */
	public Date getWeeklySheetFrom() {
		return weeklySheetFrom;
	}

	/**
	 * @param weeklySheetFrom セットする weeklySheetFrom
	 */
	public void setWeeklySheetFrom(Date weeklySheetFrom) {
		this.weeklySheetFrom = weeklySheetFrom;
	}

	/**
	 * @return weeklySheetTo
	 */
	public Date getWeeklySheetTo() {
		return weeklySheetTo;
	}

	/**
	 * @param weeklySheetTo セットする weeklySheetTo
	 */
	public void setWeeklySheetTo(Date weeklySheetTo) {
		this.weeklySheetTo = weeklySheetTo;
	}
	//	/**
	//	 * @return foodExpenses
	//	 */
	//	public BigDecimal getFoodExpenses() {
	//		return foodExpenses;
	//	}
	//	/**
	//	 * @param foodExpenses セットする foodExpenses
	//	 */
	//	public void setFoodExpenses(BigDecimal foodExpenses) {
	//		this.foodExpenses = foodExpenses;
	//	}
	//	/**
	//	 * @return livingwareCosts
	//	 */
	//	public BigDecimal getLivingwareCosts() {
	//		return livingwareCosts;
	//	}
	//	/**
	//	 * @param livingwareCosts セットする livingwareCosts
	//	 */
	//	public void setLivingwareCosts(BigDecimal livingwareCosts) {
	//		this.livingwareCosts = livingwareCosts;
	//	}
	//	/**
	//	 * @return wastExpenses
	//	 */
	//	public BigDecimal getWastExpenses() {
	//		return wastExpenses;
	//	}
	//	/**
	//	 * @param wastExpenses セットする wastExpenses
	//	 */
	//	public void setWastExpenses(BigDecimal wastExpenses) {
	//		this.wastExpenses = wastExpenses;
	//	}
	//	/**
	//	 * @return eatingOutAndConvenienceStores
	//	 */
	//	public BigDecimal getEatingOutAndConvenienceStores() {
	//		return eatingOutAndConvenienceStores;
	//	}
	//	/**
	//	 * @param eatingOutAndConvenienceStores セットする eatingOutAndConvenienceStores
	//	 */
	//	public void setEatingOutAndConvenienceStores(BigDecimal eatingOutAndConvenienceStores) {
	//		this.eatingOutAndConvenienceStores = eatingOutAndConvenienceStores;
	//	}
	//	/**
	//	 * @return otherExpenses
	//	 */
	//	public BigDecimal getOtherExpenses() {
	//		return otherExpenses;
	//	}
	//	/**
	//	 * @param otherExpenses セットする otherExpenses
	//	 */
	//	public void setOtherExpenses(BigDecimal otherExpenses) {
	//		this.otherExpenses = otherExpenses;
	//	}
	//	/**
	//	 * @return entertainmentExpenses
	//	 */
	//	public BigDecimal getEntertainmentExpenses() {
	//		return entertainmentExpenses;
	//	}
	//	/**
	//	 * @param entertainmentExpenses セットする entertainmentExpenses
	//	 */
	//	public void setEntertainmentExpenses(BigDecimal entertainmentExpenses) {
	//		this.entertainmentExpenses = entertainmentExpenses;
	//	}
	//	/**
	//	 * @return furnitureClothingCosts
	//	 */
	//	public BigDecimal getFurnitureClothingCosts() {
	//		return furnitureClothingCosts;
	//	}
	//	/**
	//	 * @param furnitureClothingCosts セットする furnitureClothingCosts
	//	 */
	//	public void setFurnitureClothingCosts(BigDecimal furnitureClothingCosts) {
	//		this.furnitureClothingCosts = furnitureClothingCosts;
	//	}

	/**
	 * @return otherList
	 */
	public List<OOther> getOtherList() {
		return otherList;
	}

	/**
	 * @param otherList セットする otherList
	 */
	public void setOtherList(List<OOther> otherList) {
		this.otherList = otherList;
	}

}