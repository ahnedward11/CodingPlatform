
package com.team1159ers.mock_csulb_db.service.user;

import com.team1159ers.mock_csulb_db.model.user.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    List<String> getAllEmails();
    User getUserByCsulbId(int csulbId);
    User getUserByEmail(String email);
}
