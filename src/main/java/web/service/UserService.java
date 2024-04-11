package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private Environment env;

    public List<User> getAllUsers() throws SQLException {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Transactional
    public void addUser(User user) throws SQLException {
        entityManager.persist(user);
        entityManager.getEntityManagerFactory().getCache().evictAll();
    }
    @Transactional
    public User getUserById(Long id) throws SQLException {
        return entityManager.find(User.class, id);
    }
    @Transactional
    public void updateUser(User user) throws SQLException {
        entityManager.merge(user);
    }
    @Transactional
    public User deleteUserById(Long id) throws SQLException {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
        return user;
    }
    @Transactional
    public void getConnection() {
        String jdbcUrl = env.getProperty("jdbc.url");
        String username = env.getProperty("jdbc.username");
        String password = env.getProperty("jdbc.password");;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection(Objects.requireNonNull(jdbcUrl), username, password);
            System.out.println("Соединение с базой данных установлено!");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось найти класс драйвера JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка при установке соединения с базой данных");
            e.printStackTrace();
        }
    }
    @Transactional
    public void closeConnection() {
        String jdbcUrl = env.getProperty("jdbc.url");
        String username = env.getProperty("jdbc.username");
        String password = env.getProperty("jdbc.password");;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection(Objects.requireNonNull(jdbcUrl), username, password).close();
            System.out.println("Соединение с базой данных закрыто!");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось найти класс драйвера JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка при установке соединения с базой данных");
            e.printStackTrace();
        }

    }
}
