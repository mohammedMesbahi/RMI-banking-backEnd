package estm.dsic.umi.business;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.UserDao;
import estm.dsic.umi.dao.UserDaoJDBC;

public class DefaultUserService implements UserService{
    private static DefaultUserService instance ;
    private UserDao userDao;

    public DefaultUserService(UserDao instance) {
        this.userDao = instance;
    }

    public static DefaultUserService getInstance(){
        if(instance == null){
            instance = new DefaultUserService(UserDaoJDBC.getInstance());
        }
        return instance;
    }
    @Override
    public User createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public User getUser(User user) {
        return userDao.get(user);
    }
}
