package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.TransactionDao;
import estm.dsic.umi.dao.TransactionDaoJDBC;

import java.util.List;

public class DefaultTransactionService implements TransactionService{
    private static DefaultTransactionService instance;
    private TransactionDao transactionDao;
    private DefaultTransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public static DefaultTransactionService getInstance() {
        if (instance == null) {
            instance = new DefaultTransactionService(
                TransactionDaoJDBC.getInstance()
            );
        }
        return instance;
    }
    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionDao.create(transaction);
    }

    @Override
    public List<Transaction> getTransactionsOfAnAccount(Account account) {
        return transactionDao.getAllTransactionsOfAnAccount(account);
    }

    @Override
    public List<Transaction> getTransactionsOfAUser(User user) {
        return transactionDao.getAllTransactionsOfAUser(user);
    }
}
