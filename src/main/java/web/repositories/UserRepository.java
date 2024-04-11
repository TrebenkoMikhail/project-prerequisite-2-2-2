package web.repositories;


import org.springframework.data.repository.CrudRepository;
import web.model.User;

import java.sql.SQLException;
import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    List<User> getAllUsers() throws SQLException;
    void addUser(User user) throws SQLException;
    User deleteUser(Long id) throws SQLException;
    User updateUser(Long id, User user) throws SQLException;
    User getUserById(Long id) throws SQLException;

}
