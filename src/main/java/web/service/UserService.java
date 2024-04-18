package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.User;
import web.model.UserDAO;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDao;

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public void updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

}
