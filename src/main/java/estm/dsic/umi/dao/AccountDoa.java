package estm.dsic.umi.dao;

import java.util.List;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.User;

public interface AccountDoa {
    public void create(Account account);
    public List<Account> getAllAccountsOfAUser(User user);
    
}
