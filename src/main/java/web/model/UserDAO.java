package web.model;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    void addUser(User user)  ;
    void updateUser(User user) throws SQLException;  ;
    User getUserById(Long id)  ;
    void deleteUserById(Long id)  ;

}
