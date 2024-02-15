package estm.dsic.umi.dao;

import java.util.List;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;

public interface TransactionDao {
    public Transaction create(Transaction transaction);
    public List<Transaction> getAllTransactionsOfAnAccount(Account account);
    public List<Transaction> getAllTransactionsOfAUser(User user);
}
