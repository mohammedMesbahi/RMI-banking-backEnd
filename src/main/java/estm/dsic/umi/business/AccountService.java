package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
public interface AccountService {
    public void deposit(Account Account, Double amount);
    public void withdraw(Account Account, Double amount);
    public void transfer(Account fromAccount, Account toAccount, Double amount);

    public Account getAccountById(Integer id);
    public Account createAccount(Account account);
    public Account getAccount(Account account);
    
}
