package web.DAO;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void add(String name, String city) {
        if (!(name.length() == 0 && city.length() == 0)) {
            entityManager.persist(new User(name, city));
        }
    }

    @Override
    public void remove(Long id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    @Override
    public User update(Long id, String name, String city) {
        User user = entityManager.find(User.class,id);
        user.setUsername(name);
        user.setCity(city);
        entityManager.merge(user);
        return entityManager.find(User.class,id);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("from User").getResultList();
        return users;
    }

    @Override
    public List<User> getUsersByCity(String city) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.city = :city")
                .setParameter("city", city)
                .getResultList();
    }
}
