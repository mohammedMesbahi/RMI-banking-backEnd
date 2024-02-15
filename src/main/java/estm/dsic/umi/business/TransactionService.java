package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;

import java.util.List;

public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);
    public List<Transaction> getTransactionsOfAnAccount(Account account);
    public List<Transaction> getTransactionsOfAUser(User user);
}
