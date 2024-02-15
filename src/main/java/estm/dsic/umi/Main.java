package estm.dsic.umi;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.UserDaoJDBC;

public class Main {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setEmail("mesbahi.ed@gmail.com");
        user1.setPassword("123456");
        UserDaoJDBC.getInstance().create(user1);
        System.out.println(UserDaoJDBC.getInstance().getById(user1.getId()));
    }
}