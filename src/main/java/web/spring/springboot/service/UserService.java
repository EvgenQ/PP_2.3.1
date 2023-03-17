package web.spring.springboot.service;

import web.spring.springboot.model.User;
import java.util.List;

public interface UserService {
    void add(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    void remove(Long id);

    void updateUser(User user);
}
