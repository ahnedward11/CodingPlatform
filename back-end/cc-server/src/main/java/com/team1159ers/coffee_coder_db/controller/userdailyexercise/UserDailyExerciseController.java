
package com.team1159ers.coffee_coder_db.controller.userdailyexercise;

import com.team1159ers.coffee_coder_db.model.Code;
import com.team1159ers.coffee_coder_db.model.userdailyexercise.UserDailyExercise;
import com.team1159ers.coffee_coder_db.service.userdailyexercise.UserDailyExerciseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user_daily_exercise", consumes = MediaType.ALL_VALUE)
public class UserDailyExerciseController {

    private final UserDailyExerciseService userDailyExerciseService;

    public UserDailyExerciseController(UserDailyExerciseService userDailyExerciseService) {
        this.userDailyExerciseService = userDailyExerciseService;
    }

    @PostMapping("/add")
    public String add(@RequestBody UserDailyExercise userDailyExercise) {
        userDailyExerciseService.saveUserDailyExercise(userDailyExercise);
        return "New user daily exercise has been added.";
    }

    @GetMapping("/getAll")
    public List<UserDailyExercise> list(){
        return userDailyExerciseService.getAllUserDailyExercises();
    }

    @GetMapping("/getUserDailyExercise")
    public UserDailyExercise getUserDailyExercise(@RequestParam("csulbId") int csulbId, @RequestParam("exerciseId") int exerciseId) {
        return userDailyExerciseService.getUserDailyExercise(csulbId, exerciseId);
    }

    @GetMapping("/getUserDailyExercisesByProblemId")
    public List<UserDailyExercise> getUserDailyExercisesByProblemId(int csulbId, int problemId) {
        return userDailyExerciseService.getUserDailyExercisesByProblemId(csulbId, problemId);
    }

    @PatchMapping("/updateCodeContent")
    public void updateCodeContent(@RequestParam("csulbId") int csulbId, @RequestBody Code code) {
        userDailyExerciseService.updateCodeContent(csulbId, code.getContent());
    }

    @PatchMapping("/updateIsSolved")
    public void updateIsSolved(@RequestParam("csulbId") int csulbId,
                               @RequestParam("exerciseId") int exerciseId,
                               @RequestParam("isSolved") boolean isSolved) {
        userDailyExerciseService.updateIsSolved(csulbId, exerciseId, isSolved);
    }

    @DeleteMapping("/deleteUserDailyExercise")
    public void deleteUserDailyExercise(@RequestParam("csulbId") int csulbId, @RequestParam("exerciseId") int exerciseId) {
        userDailyExerciseService.deleteUserDailyExercise(csulbId, exerciseId);
    }
}
