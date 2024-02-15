package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.TransactionDao;
import estm.dsic.umi.dao.TransactionDaoJDBC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DefaultTransactionService extends UnicastRemoteObject implements TransactionService{
    private static DefaultTransactionService instance;
    private TransactionDao transactionDao;
    private DefaultTransactionService(TransactionDao transactionDao) throws RemoteException {
        this.transactionDao = transactionDao;
    }

    public static DefaultTransactionService getInstance()  throws RemoteException {
        if (instance == null) {
            instance = new DefaultTransactionService(
                TransactionDaoJDBC.getInstance()
            );
        }
        return instance;
    }
    @Override
    public Transaction createTransaction(Transaction transaction)  throws RemoteException{
        return transactionDao.create(transaction);
    }

    @Override
    public List<Transaction> getTransactionsOfAnAccount(Account account)  throws RemoteException{
        return transactionDao.getAllTransactionsOfAnAccount(account);
    }

    @Override
    public List<Transaction> getTransactionsOfAUser(User user)  throws RemoteException{
        return transactionDao.getAllTransactionsOfAUser(user);
    }
}
