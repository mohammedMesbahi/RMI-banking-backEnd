package estm.dsic.umi.business;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.UserDao;
import estm.dsic.umi.dao.UserDaoJDBC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DefaultAuthService extends UnicastRemoteObject implements  AuthService{
    private static DefaultAuthService instance = null;
    private final UserDao userDao;

    public static DefaultAuthService getInstance() throws RemoteException {
        if(instance==null)
            instance=new DefaultAuthService(UserDaoJDBC.getInstance());
        return instance;
    }
    private DefaultAuthService(UserDao userDao) throws RemoteException {
        this.userDao=userDao;
    }

    @Override
    public User authenticate(String email, String password) {
        return userDao.getByEmailAndPassword(email,password);
    }

}
