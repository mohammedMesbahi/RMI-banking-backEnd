package estm.dsic.umi.business;

import estm.dsic.umi.beans.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Integer id);
    User getUser(User user);
}
