package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.AccountDao;
import estm.dsic.umi.dao.AccountDaoJDBC;

import java.util.List;

public class DefaultAccountService implements AccountService {
    private static DefaultAccountService instance;
    private AccountDao accountDao;
    private TransactionService transactionService;

    private DefaultAccountService(AccountDao accountDao, TransactionService transactionService) {
        this.accountDao = accountDao;
        this.transactionService = transactionService;
    }

    public static DefaultAccountService getInstance() {
        if (instance == null) {
            instance = new DefaultAccountService(AccountDaoJDBC.getInstance(), DefaultTransactionService.getInstance());
        }
        return instance;
    }

    @Override
    public Transaction deposit(Account Account, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSrcAccount(Account.getId());
        transaction.setDestAccount(Account.getId());
        transaction.setTransactionType(Transaction.DEPOSIT);
        transaction.setDate(new java.util.Date());
        return transactionService.createTransaction(transaction);
    }

    @Override
    public void withdraw(Account Account, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSrcAccount(Account.getId());
        transaction.setDestAccount(Account.getId());
        transaction.setTransactionType(Transaction.WITHDRAW);
        transaction.setDate(new java.util.Date());
        transactionService.createTransaction(transaction);
    }

    @Override
    public void transfer(Account fromAccount, Account toAccount, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSrcAccount(fromAccount.getId());
        transaction.setDestAccount(toAccount.getId());
        transaction.setTransactionType(Transaction.TRANSFER);
        transaction.setDate(new java.util.Date());
        transactionService.createTransaction(transaction);
    }

    @Override
    public Account createAccount(Account account) {
        return accountDao.create(account);
    }

    @Override
    public List<Account> getUserAccounts(User user) {
        return accountDao.getAccountsOfAUser(user);
    }
}
