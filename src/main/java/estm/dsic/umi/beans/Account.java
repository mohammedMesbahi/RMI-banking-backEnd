package estm.dsic.umi.beans;

import java.util.List;

public class Account {
    private int id;
    private int ownerId;
    private double balance;

    private List<Transaction> transactions;
    /* setters and getters */
    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwnerId(int ownerId) {
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
    


}
