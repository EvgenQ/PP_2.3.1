package web.DAO;

import web.model.User;
import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    void remove(Long id);
}
