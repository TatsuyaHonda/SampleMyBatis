package house.keep.mybatis.model.housekeeper;

import java.math.BigDecimal;
import java.util.Date;

public class OCredit {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_CREDIT.PAYMENT_DATE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    private Date paymentDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_CREDIT.DEBIT_DATE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    private Date debitDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_CREDIT.EXPENSE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    private String expense;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_CREDIT.AMOUNT
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    private BigDecimal amount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_CREDIT.PAYMENT_DATE
     *
     * @return the value of HOUSEKEEPER.O_CREDIT.PAYMENT_DATE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_CREDIT.PAYMENT_DATE
     *
     * @param paymentDate the value for HOUSEKEEPER.O_CREDIT.PAYMENT_DATE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_CREDIT.DEBIT_DATE
     *
     * @return the value of HOUSEKEEPER.O_CREDIT.DEBIT_DATE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    public Date getDebitDate() {
        return debitDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_CREDIT.DEBIT_DATE
     *
     * @param debitDate the value for HOUSEKEEPER.O_CREDIT.DEBIT_DATE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    public void setDebitDate(Date debitDate) {
        this.debitDate = debitDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_CREDIT.EXPENSE
     *
     * @return the value of HOUSEKEEPER.O_CREDIT.EXPENSE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    public String getExpense() {
        return expense;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_CREDIT.EXPENSE
     *
     * @param expense the value for HOUSEKEEPER.O_CREDIT.EXPENSE
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    public void setExpense(String expense) {
        this.expense = expense == null ? null : expense.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_CREDIT.AMOUNT
     *
     * @return the value of HOUSEKEEPER.O_CREDIT.AMOUNT
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_CREDIT.AMOUNT
     *
     * @param amount the value for HOUSEKEEPER.O_CREDIT.AMOUNT
     *
     * @mbg.generated Wed May 01 16:53:22 JST 2019
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

	// 以下、拡張カラム。
	/**
	 *
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column HOUSEKEEPER.O_FOOD.PAYMENT_DATE
	 *
	 * @mbg.generated Wed May 01 11:51:11 JST 2019
	 */
	private Date paymentMonth;

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column HOUSEKEEPER.O_FOOD.PAYMENT_DATE
	 *
	 * @return the value of HOUSEKEEPER.O_FOOD.PAYMENT_DATE
	 *
	 * @mbg.generated Wed May 01 11:51:11 JST 2019
	 */
	public Date getPaymentMonth() {
		return paymentMonth;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column HOUSEKEEPER.O_FOOD.PAYMENT_DATE
	 *
	 * @param paymentMonth the value for HOUSEKEEPER.O_FOOD.PAYMENT_DATE
	 *
	 * @mbg.generated Wed May 01 11:51:11 JST 2019
	 */
	public void setPaymentMonth(Date paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
}