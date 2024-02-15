package estm.dsic.umi.business;

import estm.dsic.umi.beans.User;

public interface AuthService {
    User authenticate(String email,String password);
}
