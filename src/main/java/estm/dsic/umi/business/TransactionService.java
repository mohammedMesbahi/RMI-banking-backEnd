package estm.dsic.umi.business;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TransactionService extends Remote {

    public Transaction createTransaction(Transaction transaction)  throws RemoteException;
    public List<Transaction> getTransactionsOfAnAccount(Account account)  throws RemoteException;
    public List<Transaction> getTransactionsOfAUser(User user)  throws RemoteException;
}
