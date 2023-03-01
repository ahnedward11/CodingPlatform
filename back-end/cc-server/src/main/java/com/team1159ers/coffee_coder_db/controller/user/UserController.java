
package com.team1159ers.coffee_coder_db.controller.user;

import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.model.usercodingproblem.UserCodingProblem;
import com.team1159ers.coffee_coder_db.model.userdailyexercise.UserDailyExercise;
import com.team1159ers.coffee_coder_db.service.codingproblem.CodingProblemService;
import com.team1159ers.coffee_coder_db.service.dailyexercise.DailyExerciseService;
import com.team1159ers.coffee_coder_db.service.user.UserService;
import com.team1159ers.coffee_coder_db.model.user.User;
import com.team1159ers.coffee_coder_db.service.usercodingproblem.UserCodingProblemService;
import com.team1159ers.coffee_coder_db.service.userdailyexercise.UserDailyExerciseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.ALL_VALUE)
public class UserController {

    private final UserService userService;
    private final CodingProblemService codingProblemService;
    private final DailyExerciseService dailyExerciseService;
    private final UserCodingProblemService userCodingProblemService;
    private final UserDailyExerciseService userDailyExerciseService;

    public UserController(UserService userService, CodingProblemService codingProblemService, DailyExerciseService dailyExerciseService, UserCodingProblemService userCodingProblemService, UserDailyExerciseService userDailyExerciseService) {
        this.userService = userService;
        this.codingProblemService = codingProblemService;
        this.dailyExerciseService = dailyExerciseService;
        this.userCodingProblemService = userCodingProblemService;
        this.userDailyExerciseService = userDailyExerciseService;
    }

    @PostMapping("/add")
    public int add(@RequestBody User user, @RequestParam("csulbId") String csulbId) {
        try {
            // Since `csulbId` is an auto-generated primary key, we must set it manually
            int csulbIdAsInt = Integer.parseInt(csulbId);
            User userToSearch = userService.getUserByCsulbId(csulbIdAsInt);

            if (userToSearch == null) {
                user.setCsulbId(csulbIdAsInt);
                userService.saveUser(user);

                // Initialize the UserCodingProblem and its relevant UserDailyExercises
                CodingProblem codingProblem = codingProblemService
                        .getCodingProblemByProblemId(user.getCurrentProblemId());
                UserCodingProblem userCodingProblem = new UserCodingProblem(user, codingProblem);
                userCodingProblemService.saveUserCodingProblem(userCodingProblem);

                List<Integer> dailyExerciseIds = codingProblemService
                        .getCategoryIds(codingProblem.getProblemId());
                for (int dailyExerciseId: dailyExerciseIds) {
                    DailyExercise dailyExercise = dailyExerciseService.getDailyExerciseById(dailyExerciseId);
                    UserDailyExercise userDailyExercise = new UserDailyExercise(user, dailyExercise);
                    userDailyExerciseService.saveUserDailyExercise(userDailyExercise);
                }

                return 0;
            } else {
                throw new DataIntegrityViolationException("Account already exists.");
            }

            // Account with matching CSULB ID or email already exists
        } catch (DataIntegrityViolationException e) {
            return -1;
        }
    }

    @GetMapping("/getAll")
    public List<User> list() {
        return userService.getAllUsers();
    }

    @GetMapping("/getNumOfUsers")
    public int getNumOfUsers() {
        return userService.getNumOfUsers();
    }

    @GetMapping("/getByCsulbId")
    public User getUserByCsulbId(@RequestParam("csulbId") String csulbId) {
        User user;
        try {
            int csulbIdAsInt = Integer.parseInt(csulbId);
            user = userService.getUserByCsulbId(csulbIdAsInt);
        } catch (NumberFormatException e) {
            user = new User();
        }

        return Objects.requireNonNullElseGet(user, User::new);
    }

    @GetMapping("/getByEmail")
    public User getUserByEmail(@RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);

        return Objects.requireNonNullElseGet(user, User::new);
    }

    @PatchMapping("/updateProfileName")
    public User updateProfileName(@RequestParam("csulbId") int csulbId,
                                  @RequestParam("profileName") String profileName) {
        userService.updateProfileName(csulbId, profileName);
        return userService.getUserByCsulbId(csulbId);
    }

    @PatchMapping("/updateProfileImgSrc")
    public User updateProfileImgSrc(@RequestParam("csulbId") int csulbId,
                                  @RequestParam("profileImgSrc") String profileImgSrc) {
        userService.updateProfileImgSrc(csulbId, profileImgSrc);
        return userService.getUserByCsulbId(csulbId);
    }

    @PatchMapping("/updateProblemId")
    public User updateProblemId(@RequestParam("csulbId") int csulbId,
                                      @RequestParam("problemId") int problemId) {
        userService.updateProblemId(csulbId, problemId);
        return userService.getUserByCsulbId(csulbId);
    }
}
