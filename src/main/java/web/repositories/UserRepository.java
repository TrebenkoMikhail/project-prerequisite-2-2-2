package web.repositories;


import web.model.User;

import java.sql.SQLException;
import java.util.List;


public interface UserRepository {
    List<User> getAllUsers();
    void addUser(User user) throws SQLException;
    void deleteUser(Long id);

}
