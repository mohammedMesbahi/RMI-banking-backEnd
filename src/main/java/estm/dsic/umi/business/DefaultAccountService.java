package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.AccountDao;
import estm.dsic.umi.dao.AccountDaoJDBC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DefaultAccountService extends UnicastRemoteObject implements AccountService {
    private static DefaultAccountService instance;
    private AccountDao accountDao;
    private TransactionService transactionService;

    private DefaultAccountService(AccountDao accountDao, TransactionService transactionService) throws RemoteException {
        this.accountDao = accountDao;
        this.transactionService = transactionService;
    }

    public static DefaultAccountService getInstance()  throws RemoteException {
        if (instance == null) {
            instance = new DefaultAccountService(AccountDaoJDBC.getInstance(), DefaultTransactionService.getInstance());
        }
        return instance;
    }

    @Override
    public Transaction deposit(Account Account, Double amount)  throws RemoteException {
        return AccountDaoJDBC.getInstance().deposit(Account, amount);
    }

    @Override
    public Transaction withdraw(Account Account, Double amount)  throws RemoteException{
        return AccountDaoJDBC.getInstance().withdraw(Account, amount);
    }

    @Override
    public Transaction transfer(Account fromAccount, Account toAccount, Double amount)  throws RemoteException{
        return AccountDaoJDBC.getInstance().transfer(fromAccount, toAccount, amount);
    }

    @Override
    public Account createAccount(Account account)  throws RemoteException{
        return accountDao.create(account);
    }

    @Override
    public List<Account> getUserAccounts(User user)  throws RemoteException{
        return accountDao.getAccountsOfAUser(user);
    }
}
