package estm.dsic.umi.dao;

import java.util.List;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;

public interface TransactionDoa {
    public void create(Transaction transaction);
    public List<Transaction> getAllTransactionsOfAnAccount(Account account);
}
