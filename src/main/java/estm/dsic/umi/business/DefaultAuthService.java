package estm.dsic.umi.business;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.UserDao;
import estm.dsic.umi.dao.UserDaoJDBC;

public class DefaultAuthService implements  AuthService{
    private static DefaultAuthService instance = null;
    private final UserDao userDao;

    public static DefaultAuthService getInstance() {
        if(instance==null)
            instance=new DefaultAuthService(UserDaoJDBC.getInstance());
        return instance;
    }
    private DefaultAuthService(UserDao userDao) {
        this.userDao=userDao;
    }

    @Override
    public User authenticate(String email, String password) {
        return userDao.getByEmailAndPassword(email,password);
    }

}
