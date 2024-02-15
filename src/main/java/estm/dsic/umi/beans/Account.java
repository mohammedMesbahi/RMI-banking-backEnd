package estm.dsic.umi.beans;

import java.util.List;

public class Account {
    private Integer id;
    private Integer ownerId;
    private Double balance;

    private List<Transaction> transactions;
    /* setters and getters */
    public Integer getId() {
        return id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void transfer(Account account, double amount) {
        withdraw(amount);
        account.deposit(amount);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }


}
