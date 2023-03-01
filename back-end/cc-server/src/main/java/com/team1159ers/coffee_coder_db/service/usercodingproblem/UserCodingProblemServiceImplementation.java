
package com.team1159ers.coffee_coder_db.service.usercodingproblem;

import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.user.User;
import com.team1159ers.coffee_coder_db.model.usercodingproblem.UserCodingProblem;
import com.team1159ers.coffee_coder_db.repository.usercodingproblem.UserCodingProblemRepository;
import com.team1159ers.coffee_coder_db.service.codingproblem.CodingProblemService;
import com.team1159ers.coffee_coder_db.service.dailyexercise.DailyExerciseService;
import com.team1159ers.coffee_coder_db.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCodingProblemServiceImplementation implements UserCodingProblemService {

    private final UserCodingProblemRepository userCodingProblemRepository;

    private final UserService userService;
    private final CodingProblemService codingProblemService;

    public UserCodingProblemServiceImplementation(UserCodingProblemRepository userCodingProblemRepository,
                                                  UserService userService,
                                                  CodingProblemService codingProblemService) {
        this.userCodingProblemRepository = userCodingProblemRepository;
        this.userService = userService;
        this.codingProblemService = codingProblemService;
    }

    @Override
    public UserCodingProblem saveUserCodingProblem(UserCodingProblem userCodingProblem) {
        return userCodingProblemRepository.save(userCodingProblem);
    }

    @Override
    public List<UserCodingProblem> getAllUserCodingProblems() {
        return userCodingProblemRepository.findAll();
    }

    @Override
    public UserCodingProblem initializeUserCodingProblem(int csulbId, int codingProblemId) {
        User user = userService.getUserByCsulbId(csulbId);
        CodingProblem codingProblem = codingProblemService.getCodingProblemByProblemId(codingProblemId);
        UserCodingProblem userCodingProblem = new UserCodingProblem(user, codingProblem);
        return userCodingProblemRepository.save(userCodingProblem);
    }

    @Override
    public UserCodingProblem getUserCodingProblem(int csulbId, int codingProblemId) {
        return userCodingProblemRepository.getUserCodingProblem(csulbId, codingProblemId);
    }

    @Override
    public void updateCodeContent(int csulbId, String codeContent) {
        userCodingProblemRepository.updateCodeContent(csulbId, codeContent);
    }

    @Override
    public void updateIsSolved(int csulbId, boolean isSolved) {
        userCodingProblemRepository.updateIsSolved(csulbId, isSolved);
    }
}
