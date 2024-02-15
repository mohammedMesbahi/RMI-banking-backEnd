package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;

import java.util.List;

public interface AccountService {
    public Transaction deposit(Account Account, Double amount);
    public Transaction withdraw(Account Account, Double amount);
    public Transaction transfer(Account fromAccount, Account toAccount, Double amount);

    public Account createAccount(Account account);
    public List<Account> getUserAccounts(User user);
    
}
