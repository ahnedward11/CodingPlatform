
package com.team1159ers.coffee_coder_db.service.usercodingproblem;

import com.team1159ers.coffee_coder_db.model.user.User;
import com.team1159ers.coffee_coder_db.model.usercodingproblem.UserCodingProblem;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCodingProblemService {
    UserCodingProblem saveUserCodingProblem(UserCodingProblem userCodingProblem);
    List<UserCodingProblem> getAllUserCodingProblems();
    UserCodingProblem initializeUserCodingProblem(int csulbId, int codingProblemId);
    UserCodingProblem getUserCodingProblem(int csulbId, int codingProblemId);
    void updateCodeContent(int csulbId, String codeContent);
    void updateIsSolved(int csulbId, boolean isSolved);
}
