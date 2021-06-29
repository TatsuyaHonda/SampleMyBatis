package house.keep.model.account241;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 単月ごとの通帳241の明細
 *
 */
public class DateBigDecimalEntry {

	private Date date = null;
	private BigDecimal amount = BigDecimal.ZERO;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}