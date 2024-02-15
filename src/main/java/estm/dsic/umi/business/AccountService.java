package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AccountService extends Remote {
    public Transaction deposit(Account Account, Double amount) throws RemoteException;
    public Transaction withdraw(Account Account, Double amount)  throws RemoteException;
    public Transaction transfer(Account fromAccount, Account toAccount, Double amount)  throws RemoteException;

    public Account createAccount(Account account)  throws RemoteException;
    public List<Account> getUserAccounts(User user)  throws RemoteException;
    
}
