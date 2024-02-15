package estm.dsic.umi;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.JDBCconnection;
import estm.dsic.umi.dao.UserDoaJDBC;

public class Main {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setEmail("mesbahi.ed@gmail.com");
        user1.setPassword("123456");
        UserDoaJDBC.getInstance().create(user1);
        System.out.println(UserDoaJDBC.getInstance().getById(user1.getId()));
    }
}