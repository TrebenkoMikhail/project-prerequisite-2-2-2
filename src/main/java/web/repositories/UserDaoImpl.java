package web.repositories;


import org.springframework.stereotype.Repository;
import web.model.User;
import web.model.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        try {
            return entityManager.createQuery("select u from User u").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public void addUser(User user)  {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override

    public User getUserById(Long id)  {
        return entityManager.find(User.class, id);
    }
    @Override
    public void updateUser(User user)   {
        entityManager.merge(user);
    }
    @Override
    public void deleteUserById(Long id)  {
        try{
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
