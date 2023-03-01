
package com.team1159ers.mock_csulb_db.service.user;

import com.team1159ers.mock_csulb_db.model.user.User;
import com.team1159ers.mock_csulb_db.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getAllEmails() {
        return userRepository.getAllEmails();
    }

    @Override
    public User getUserByCsulbId(int csulbId) {
        return userRepository.getUserByCsulbId(csulbId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
