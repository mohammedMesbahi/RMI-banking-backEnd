package estm.dsic.umi.dao;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.User;

import java.util.List;

public interface AccountDao {
    public Account create(Account account);
    public List<Account> getAccountsOfAUser(User user);

}
