
package com.team1159ers.coffee_coder_db.service.user;

import com.team1159ers.coffee_coder_db.model.user.User;

import java.util.List;

public interface UserService {
    int getNumOfUsers();
    User saveUser(User student);
    List<User> getAllUsers();
    User getUserByCsulbId(int csulbId);
    User getUserByEmail(String email);
    void updateProfileName(int csulbId, String profileName);
    void updateProfileImgSrc(int csulbId, String profileImgSrc);
    void updateProblemId(int csulbId, int problemId);
}
