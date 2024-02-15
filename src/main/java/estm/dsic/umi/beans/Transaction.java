package estm.dsic.umi.beans;

import java.util.Date;

public class Transaction {
    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAWAL = "withdraw";
    public static final String TRANSFER = "transfer";
    private Integer id;
    private Integer srcAccount;
    private Integer destAccount;
    private Double amount;
    private String transactionType;
    private Date date;

    public Transaction(Double amount, Integer srcAccount, Integer des, String deposit, Date date) {
        this.amount = amount;
        this.srcAccount = srcAccount;
        this.destAccount = des;
        this.transactionType = deposit;
        this.date = date;
    }

    public Transaction() {

    }


    /* setters and getters */
    public Integer getId() {
        return id;
    }

    public Integer getSrcAccount() {
        return srcAccount;
    }

    public Integer getDestAccount() {
        return destAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSrcAccount(Integer srcAccount) {
        this.srcAccount = srcAccount;
    }

    public void setDestAccount(Integer destAccount) {
        this.destAccount = destAccount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", srcAccount=" + srcAccount +
                ", destAccount=" + destAccount +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", date=" + date +
                '}';
    }

}
