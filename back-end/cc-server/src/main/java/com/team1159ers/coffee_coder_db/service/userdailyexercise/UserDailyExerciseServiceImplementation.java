
package com.team1159ers.coffee_coder_db.service.userdailyexercise;

import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.model.user.User;
import com.team1159ers.coffee_coder_db.model.userdailyexercise.UserDailyExercise;
import com.team1159ers.coffee_coder_db.repository.userdailyexercise.UserDailyExerciseRepository;
import com.team1159ers.coffee_coder_db.service.codingproblem.CodingProblemService;
import com.team1159ers.coffee_coder_db.service.dailyexercise.DailyExerciseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDailyExerciseServiceImplementation implements UserDailyExerciseService {

    private final UserDailyExerciseRepository userDailyExerciseRepository;

    private final CodingProblemService codingProblemService;
    private final DailyExerciseService dailyExerciseService;

    public UserDailyExerciseServiceImplementation(UserDailyExerciseRepository userDailyExerciseRepository,
                                                  CodingProblemService codingProblemService,
                                                  DailyExerciseService dailyExerciseService) {
        this.userDailyExerciseRepository = userDailyExerciseRepository;
        this.codingProblemService = codingProblemService;
        this.dailyExerciseService = dailyExerciseService;
    }

    @Override
    public UserDailyExercise saveUserDailyExercise(UserDailyExercise userDailyExercise) {
        return userDailyExerciseRepository.save(userDailyExercise);
    }

    @Override
    public List<UserDailyExercise> getAllUserDailyExercises() {
        return userDailyExerciseRepository.findAll();
    }

    @Override
    public List<UserDailyExercise> initializeUserDailyExercises(User user, int codingProblemId) {
        List<UserDailyExercise> userDailyExercises = new ArrayList<>();
        List<Integer> dailyExerciseIds = codingProblemService.getCategoryIds(codingProblemId);

        for (int dailyExerciseId: dailyExerciseIds) {
            DailyExercise dailyExercise = dailyExerciseService.getDailyExerciseById(dailyExerciseId);
            UserDailyExercise userDailyExercise = new UserDailyExercise(user, dailyExercise);
            userDailyExercises.add(userDailyExerciseRepository.save(userDailyExercise));
        }

        return userDailyExercises;
    }

    @Override
    public UserDailyExercise getUserDailyExercise(int csulbId, int exerciseId) {
        return userDailyExerciseRepository.getUserDailyExercise(csulbId, exerciseId);
    }

    @Override
    public List<UserDailyExercise> getUserDailyExercisesByProblemId(int csulbId, int problemId) {
        return userDailyExerciseRepository.getUserDailyExercisesByProblemId(csulbId, problemId);
    }

    @Override
    public void updateCodeContent(int csulbId, String codeContent) {
        userDailyExerciseRepository.updateCodeContent(csulbId, codeContent);
    }

    @Override
    public void updateIsSolved(int csulbId, int exerciseId, boolean isSolved) {
        userDailyExerciseRepository.updateIsSolved(csulbId, exerciseId, isSolved);
    }

    @Override
    public void deleteUserDailyExercise(int csulbId, int exerciseId) {
        userDailyExerciseRepository.deleteUserDailyExercise(csulbId, exerciseId);
    }
}
