package house.keep.model.account241;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 単月ごとの通帳241の明細
 *
 */
public class Account241AsMonthModel {

	// 給与
	private DateBigDecimalEntry paymentEntry = null;

	// 賞与
	private DateBigDecimalEntry bonusEntry = null;

	// クレジット
	private DateBigDecimalEntry creditEntry = null;

	// レックテニス
	private DateBigDecimalEntry tennisEntry = null;

	// 家賃
	private DateBigDecimalEntry rentEntry = null;

	// 出金
	private List<DateBigDecimalEntry> withdrawalEntryList = new ArrayList<>();

	// 合計
	private BigDecimal outSumAmount = new BigDecimal(0);

	public void setPaymentEntry(Date date, BigDecimal amount) {
		DateBigDecimalEntry entry = new DateBigDecimalEntry();
		entry.setDate(date);
		entry.setAmount(amount);
		this.paymentEntry = entry;
	}

	public Date getPaymentDate() {
		return paymentEntry.getDate();
	}

	public BigDecimal getPaymentAmount() {
		return paymentEntry.getAmount();
	}

	public void setBonusEntry(Date date, BigDecimal amount) {
		DateBigDecimalEntry entry = new DateBigDecimalEntry();
		entry.setDate(date);
		entry.setAmount(amount);
		this.bonusEntry = entry;
	}

	public Date getBonusDate() {
		return bonusEntry.getDate();
	}

	public BigDecimal getBonusAmount() {
		return bonusEntry.getAmount();
	}

	public void setCreditEntry(Date date, BigDecimal amount) {
		DateBigDecimalEntry entry = new DateBigDecimalEntry();
		entry.setDate(date);
		entry.setAmount(amount);
		this.creditEntry = entry;
	}

	public Date getCreditDate() {
		return creditEntry.getDate();
	}

	public BigDecimal getCreditAmount() {
		return creditEntry.getAmount();
	}

	public void setTennisEntry(Date date, BigDecimal amount) {
		DateBigDecimalEntry entry = new DateBigDecimalEntry();
		entry.setDate(date);
		entry.setAmount(amount);
		this.tennisEntry = entry;
	}

	public Date getTennisDate() {
		return tennisEntry.getDate();
	}

	public BigDecimal getTennisAmount() {
		BigDecimal amount = (tennisEntry != null)
				? tennisEntry.getAmount()
				: BigDecimal.ZERO;
		return amount;
	}

	public void setRentEntry(Date date, BigDecimal amount) {
		DateBigDecimalEntry entry = new DateBigDecimalEntry();
		entry.setDate(date);
		entry.setAmount(amount);
		this.rentEntry = entry;
	}

	public Date getRentDate() {
		return rentEntry.getDate();
	}

	public BigDecimal getRentAmount() {
		return rentEntry.getAmount();
	}

	public List<DateBigDecimalEntry> getWithdrawalEntryList() {
		return withdrawalEntryList;
	}

	public void setWithdrawalEntryList(List<DateBigDecimalEntry> withdrawalEntryList) {
		this.withdrawalEntryList = withdrawalEntryList;
	}

	public void addWithdrawalEntryList(Date date, BigDecimal amount) {
		DateBigDecimalEntry entry = new DateBigDecimalEntry();
		entry.setDate(date);
		entry.setAmount(amount);
		this.withdrawalEntryList.add(entry);
	}

	public void setOutSumAmount(BigDecimal outSumAmount) {
		this.outSumAmount = outSumAmount;
	}

	public BigDecimal getOutSumAmount() {
		BigDecimal outSumAmount = new BigDecimal(0);
		if (creditEntry != null) {
			outSumAmount = outSumAmount.add(creditEntry.getAmount());
		}
		if (tennisEntry != null) {
			outSumAmount = outSumAmount.add(tennisEntry.getAmount());
		}
		if (rentEntry != null) {
			outSumAmount = outSumAmount.add(rentEntry.getAmount());
		}
		if (withdrawalEntryList != null) {
			for (DateBigDecimalEntry withdrawalEntry : withdrawalEntryList) {
				outSumAmount = outSumAmount.add(withdrawalEntry.getAmount());
			}
		}

		return outSumAmount;
	}
}