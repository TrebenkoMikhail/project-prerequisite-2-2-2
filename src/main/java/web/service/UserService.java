package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.User;
import web.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> findAll() {
        return userRepository.getAllUsers();
    }
    @Transactional
    public void addUser(User user) {
        userRepository.addUser(user);
    }
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }
}
