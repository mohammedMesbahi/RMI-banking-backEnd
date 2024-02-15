package estm.dsic.umi;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.User;
import estm.dsic.umi.business.DefaultAccountService;
import estm.dsic.umi.dao.UserDaoJDBC;

public class Main {
    public static void main(String[] args) {
        /*User user1 = new User();
        user1.setEmail("yassin.ed@gmail.com");
        user1.setPassword("123456");
        UserDaoJDBC.getInstance().create(user1);
        System.out.println(UserDaoJDBC.getInstance().getById(user1.getId()));
        */

        /*User user2 = new User();
        user2.setEmail("mesbahi.ed@gmail.com");
        user2.setPassword("123456");
        UserDaoJDBC.getInstance().create(user2);
        System.out.println(UserDaoJDBC.getInstance().getById(user2.getId()));*/


        /* create an account */
        /*Account account1 = new Account();
        account1.setBalance(1000.0);
        account1.setOwnerId(1);

        System.out.println(DefaultAccountService.getInstance().createAccount(account1));*/

        /*Account account2 = new Account();
        account2.setBalance(9000.0);
        account2.setOwnerId(2);

        System.out.println(DefaultAccountService.getInstance().createAccount(account2));*/

        /* fetch user accounts + transactions of each account*/
        /*User userMesbahi = new User();
        userMesbahi.setEmail("mesbahi.ed@gmail.com");
        userMesbahi.setPassword("123456");
        userMesbahi.setId(2);

        System.out.println(DefaultAccountService.getInstance().getUserAccounts(userMesbahi));*/

        /*MAKE A DEPOSIT*/
        User userMesbahi = new User();

        userMesbahi.setId(2);

        userMesbahi.setAccounts(
                DefaultAccountService.getInstance().getUserAccounts(userMesbahi)
        );

        Account account = userMesbahi.getAccounts().get(0);
        // deposit 7000.0
        /*System.out.println(DefaultAccountService.getInstance().deposit(account, 1000.0));*/

        // withdraw 5000.0
        System.out.println(DefaultAccountService.getInstance().withdraw(account, 500.0));
    }
}