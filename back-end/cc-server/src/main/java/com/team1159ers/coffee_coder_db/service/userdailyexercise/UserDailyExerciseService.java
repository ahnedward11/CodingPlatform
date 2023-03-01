
package com.team1159ers.coffee_coder_db.service.userdailyexercise;

import com.team1159ers.coffee_coder_db.model.user.User;
import com.team1159ers.coffee_coder_db.model.userdailyexercise.UserDailyExercise;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDailyExerciseService {
    UserDailyExercise saveUserDailyExercise(UserDailyExercise userdailyExercise);
    List<UserDailyExercise> getAllUserDailyExercises();
    List<UserDailyExercise> initializeUserDailyExercises(User user, int codingProblemId);
    UserDailyExercise getUserDailyExercise(int csulbId, int exerciseId);
    List<UserDailyExercise> getUserDailyExercisesByProblemId(int csulbId, int problemId);
    void updateCodeContent(int csulbId, String codeContent);
    void updateIsSolved(int csulbId, int exerciseId, boolean isSolved);
    void deleteUserDailyExercise(int csulbId, int exerciseId);
}
