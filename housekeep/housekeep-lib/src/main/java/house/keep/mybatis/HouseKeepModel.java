package house.keep.mybatis;

import java.math.BigDecimal;
import java.util.Date;

public class HouseKeepModel {
	// 月
	private Date month;
	// 給与日
	private Date paymentDate;
	// 現金
	private BigDecimal money = BigDecimal.ZERO;
	// IN：口座241
	private BigDecimal oAccount241 = BigDecimal.ZERO;
	// IN：口座461
	private BigDecimal oAccount461 = BigDecimal.ZERO;
	// IN：口座定期
	private BigDecimal oAccountConst = BigDecimal.ZERO;
	// OUT：固定費
	private BigDecimal oConstants = BigDecimal.ZERO;
	// OUT：クレジットカード
	private BigDecimal oCredit = BigDecimal.ZERO;
	// OUT：食費
	private BigDecimal oFood = BigDecimal.ZERO;
	// OUT：生活用品費
	private BigDecimal oLive = BigDecimal.ZERO;
	// OUT：その他費目
	private BigDecimal oOther = BigDecimal.ZERO;
	// OUT：娯楽費
	private BigDecimal oWaste = BigDecimal.ZERO;
	// OUT：交際費
	private BigDecimal oEntertainment = BigDecimal.ZERO;
	// OUT：常駐家庭用品費
	private BigDecimal oGoods = BigDecimal.ZERO;
	// OUT：合計出費
	private BigDecimal outSum = BigDecimal.ZERO;
	// OUT：用途不明金
	private BigDecimal unknownMoney = BigDecimal.ZERO;
	// OUT：繰越し金
	private BigDecimal stayAmount = BigDecimal.ZERO;
	// WORK：IN
	private BigDecimal inAmount = BigDecimal.ZERO;
	// WORK：OUT
	private BigDecimal outAmount = BigDecimal.ZERO;

	// OUT：合計出費
	private BigDecimal startSum461 = BigDecimal.ZERO;
	// OUT：繰越し金
	private BigDecimal inAmount461 = BigDecimal.ZERO;
	// OUT：合計出費
	private BigDecimal outSum461 = BigDecimal.ZERO;
	// OUT：繰越し金
	private BigDecimal stayAmount461 = BigDecimal.ZERO;
	// OUT：合計出費
	private BigDecimal startSumConst = BigDecimal.ZERO;
	// OUT：繰越し金
	private BigDecimal inAmountConst = BigDecimal.ZERO;
	// OUT：合計出費
	private BigDecimal outSumConst = BigDecimal.ZERO;
	// OUT：繰越し金
	private BigDecimal stayAmountConst = BigDecimal.ZERO;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getoAccount241() {
		return oAccount241;
	}

	public void setoAccount241(BigDecimal oAccount241) {
		this.oAccount241 = oAccount241;
	}

	public BigDecimal getoAccount461() {
		return oAccount461;
	}

	public void setoAccount461(BigDecimal oAccount461) {
		this.oAccount461 = oAccount461;
	}

	public BigDecimal getoAccountConst() {
		return oAccountConst;
	}

	public void setoAccountConst(BigDecimal oAccountConst) {
		this.oAccountConst = oAccountConst;
	}

	public BigDecimal getoConstants() {
		return oConstants;
	}

	public void setoConstants(BigDecimal oConstants) {
		this.oConstants = oConstants;
	}

	public BigDecimal getoCredit() {
		return oCredit;
	}

	public void setoCredit(BigDecimal oCredit) {
		this.oCredit = oCredit;
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

	public BigDecimal getOutSum() {
		return outSum;
	}

	public void setOutSum(BigDecimal outSum) {
		this.outSum = outSum;
	}

	public BigDecimal getStayAmount() {
		return stayAmount;
	}

	public void setStayAmount(BigDecimal stayAmount) {
		this.stayAmount = stayAmount;
	}

	public BigDecimal getInAmount() {
		return inAmount;
	}

	public void setInAmount(BigDecimal inAmount) {
		this.inAmount = inAmount;
	}

	public BigDecimal getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(BigDecimal outAmount) {
		this.outAmount = outAmount;
	}

	public BigDecimal getOutSum461() {
		return outSum461;
	}

	public void setOutSum461(BigDecimal outSum461) {
		this.outSum461 = outSum461;
	}

	public BigDecimal getStayAmount461() {
		return stayAmount461;
	}

	public void setStayAmount461(BigDecimal stayAmount461) {
		this.stayAmount461 = stayAmount461;
	}

	public BigDecimal getOutSumConst() {
		return outSumConst;
	}

	public void setOutSumConst(BigDecimal outSumConst) {
		this.outSumConst = outSumConst;
	}

	public BigDecimal getStayAmountConst() {
		return stayAmountConst;
	}

	public void setStayAmountConst(BigDecimal stayAmountConst) {
		this.stayAmountConst = stayAmountConst;
	}

	public BigDecimal getStartSum461() {
		return startSum461;
	}

	public void setStartSum461(BigDecimal startSum461) {
		this.startSum461 = startSum461;
	}

	public BigDecimal getInAmount461() {
		return inAmount461;
	}

	public void setInAmount461(BigDecimal inAmount461) {
		this.inAmount461 = inAmount461;
	}

	public BigDecimal getStartSumConst() {
		return startSumConst;
	}

	public void setStartSumConst(BigDecimal startSumConst) {
		this.startSumConst = startSumConst;
	}

	public BigDecimal getInAmountConst() {
		return inAmountConst;
	}

	public void setInAmountConst(BigDecimal inAmountConst) {
		this.inAmountConst = inAmountConst;
	}

	public BigDecimal getoOther() {
		return oOther;
	}

	public void setoOther(BigDecimal oOther) {
		this.oOther = oOther;
	}

	public BigDecimal getUnknownMoney() {
		return unknownMoney;
	}

	public void setUnknownMoney(BigDecimal unknownMoney) {
		this.unknownMoney = unknownMoney;
	}

}