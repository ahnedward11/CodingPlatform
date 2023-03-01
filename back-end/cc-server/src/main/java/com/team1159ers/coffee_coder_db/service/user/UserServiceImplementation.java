
package com.team1159ers.coffee_coder_db.service.user;

import com.team1159ers.coffee_coder_db.repository.user.UserRepository;
import com.team1159ers.coffee_coder_db.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int getNumOfUsers() {
        return userRepository.getNumOfUsers();
    }

    /**
     * Saves the user if the mock CSULB database contains a user of the same ID.
     * @param user the user with fields csulbId, email, profileName, ...
     * @return the saved user, or null if the verification failed
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByCsulbId(int csulbId) {
        return userRepository.getUserByCsulbId(csulbId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public void updateProfileName(int csulbId, String profileName) {
        userRepository.updateProfileName(csulbId, profileName);
    }

    @Override
    public void updateProfileImgSrc(int csulbId, String profileImgSrc) {
        userRepository.updateProfileImgSrc(csulbId, profileImgSrc);
    }

    @Override
    public void updateProblemId(int csulbId, int problemId) {
        userRepository.updateProblemId(csulbId, problemId);
    }
}
