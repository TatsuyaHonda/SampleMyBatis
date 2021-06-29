package house.keep.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthWeekModelAsWeek {
	// OUT：口座246出費単位
	private BigDecimal out246 = BigDecimal.ZERO;

	private Date paymentDate = null;
	// OUT：食費
	private BigDecimal oFood = BigDecimal.ZERO;
	// OUT：生活用品費
	private BigDecimal oLive = BigDecimal.ZERO;
	// OUT：娯楽費
	private BigDecimal oWaste = BigDecimal.ZERO;
	// OUT：その他費目
	private BigDecimal oOther = BigDecimal.ZERO;
	// OUT：交際費
	private BigDecimal oEntertainment = BigDecimal.ZERO;
	// OUT：常駐家庭用品費
	private BigDecimal oGoods = BigDecimal.ZERO;
	// OUT：用途不明金
	private BigDecimal unknownMoney = BigDecimal.ZERO;
	// OUT：現金
	private BigDecimal cash = BigDecimal.ZERO;

	public BigDecimal getOut246() {
		return out246;
	}
	public void setOut246(BigDecimal out246) {
		this.out246 = out246;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public BigDecimal getoFood() {
		return oFood;
	}
	public void setoFood(BigDecimal oFood) {
		this.oFood = oFood;
	}
	public BigDecimal getoLive() {
		return oLive;
	}
	public void setoLive(BigDecimal oLive) {
		this.oLive = oLive;
	}
	public BigDecimal getoWaste() {
		return oWaste;
	}
	public void setoWaste(BigDecimal oWaste) {
		this.oWaste = oWaste;
	}
	public BigDecimal getoOther() {
		return oOther;
	}
	public void setoOther(BigDecimal oOther) {
		this.oOther = oOther;
	}
	public BigDecimal getoEntertainment() {
		return oEntertainment;
	}
	public void setoEntertainment(BigDecimal oEntertainment) {
		this.oEntertainment = oEntertainment;
	}
	public BigDecimal getoGoods() {
		return oGoods;
	}
	public void setoGoods(BigDecimal oGoods) {
		this.oGoods = oGoods;
	}
	public BigDecimal getUnknownMoney() {
		return unknownMoney;
	}
	public void setUnknownMoney(BigDecimal unknownMoney) {
		this.unknownMoney = unknownMoney;
	}
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
}