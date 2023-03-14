package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void add(String name, String city);

    User getUserById(Long id);

    List<User> getAllUsers();

    List<User> getUsersByCity(String city);

    void remove(Long id);

    User updateUser(Long id, String name, String city);
}
