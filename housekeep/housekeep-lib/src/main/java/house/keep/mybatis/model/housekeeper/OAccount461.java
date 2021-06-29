package house.keep.mybatis.model.housekeeper;

import java.math.BigDecimal;
import java.util.Date;

public class OAccount461 {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_ACCOUNT461.PAYMENT_DATE
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    private Date paymentDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_ACCOUNT461.EXPENSE
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    private String expense;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_ACCOUNT461.IN_AMOUNT
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    private BigDecimal inAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_ACCOUNT461.OUT_AMOUNT
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    private BigDecimal outAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column HOUSEKEEPER.O_ACCOUNT461.SUM
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    private BigDecimal sum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_ACCOUNT461.PAYMENT_DATE
     *
     * @return the value of HOUSEKEEPER.O_ACCOUNT461.PAYMENT_DATE
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_ACCOUNT461.PAYMENT_DATE
     *
     * @param paymentDate the value for HOUSEKEEPER.O_ACCOUNT461.PAYMENT_DATE
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_ACCOUNT461.EXPENSE
     *
     * @return the value of HOUSEKEEPER.O_ACCOUNT461.EXPENSE
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public String getExpense() {
        return expense;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_ACCOUNT461.EXPENSE
     *
     * @param expense the value for HOUSEKEEPER.O_ACCOUNT461.EXPENSE
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public void setExpense(String expense) {
        this.expense = expense == null ? null : expense.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_ACCOUNT461.IN_AMOUNT
     *
     * @return the value of HOUSEKEEPER.O_ACCOUNT461.IN_AMOUNT
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public BigDecimal getInAmount() {
        return inAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_ACCOUNT461.IN_AMOUNT
     *
     * @param inAmount the value for HOUSEKEEPER.O_ACCOUNT461.IN_AMOUNT
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_ACCOUNT461.OUT_AMOUNT
     *
     * @return the value of HOUSEKEEPER.O_ACCOUNT461.OUT_AMOUNT
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public BigDecimal getOutAmount() {
        return outAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_ACCOUNT461.OUT_AMOUNT
     *
     * @param outAmount the value for HOUSEKEEPER.O_ACCOUNT461.OUT_AMOUNT
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column HOUSEKEEPER.O_ACCOUNT461.SUM
     *
     * @return the value of HOUSEKEEPER.O_ACCOUNT461.SUM
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public BigDecimal getSum() {
        return sum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column HOUSEKEEPER.O_ACCOUNT461.SUM
     *
     * @param sum the value for HOUSEKEEPER.O_ACCOUNT461.SUM
     *
     * @mbg.generated Sat May 04 18:42:39 JST 2019
     */
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}