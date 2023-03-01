
package com.team1159ers.coffee_coder_db.controller.usercodingproblem;

import com.team1159ers.coffee_coder_db.model.Code;
import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.user.User;
import com.team1159ers.coffee_coder_db.model.usercodingproblem.UserCodingProblem;
import com.team1159ers.coffee_coder_db.service.codingproblem.CodingProblemService;
import com.team1159ers.coffee_coder_db.service.user.UserService;
import com.team1159ers.coffee_coder_db.service.usercodingproblem.UserCodingProblemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user_coding_problem", consumes = MediaType.ALL_VALUE)
public class UserCodingProblemController {

    private final UserCodingProblemService userCodingProblemService;

    public UserCodingProblemController(UserCodingProblemService userCodingProblemService) {
        this.userCodingProblemService = userCodingProblemService;
    }

    @PostMapping("/add")
    public String add(@RequestBody UserCodingProblem userCodingProblem) {
        userCodingProblemService.saveUserCodingProblem(userCodingProblem);
        return "New user coding problem has been added.";
    }

    @GetMapping("/getUserCodingProblem")
    public UserCodingProblem getUserCodingProblem(@RequestParam("csulbId") int csulbId, @RequestParam("problemId") int problemId) {
        return userCodingProblemService.getUserCodingProblem(csulbId, problemId);
    }

    @GetMapping("/getAll")
    public List<UserCodingProblem> list(){
        return userCodingProblemService.getAllUserCodingProblems();
    }

    @PostMapping("/addUserCodingProblem")
    public int addUserCodingProblem(@RequestParam("csulbId") int csulbId,
                                    @RequestParam("problemId") int problemId) {
        try {
            UserCodingProblem userCodingProblem = userCodingProblemService
                    .getUserCodingProblem(csulbId, problemId);

            if (userCodingProblem == null) {
                userCodingProblemService.initializeUserCodingProblem(csulbId, problemId);
            }
        } catch (Exception err) {
            err.printStackTrace();
            return -1;
        }

        return 0;
    }

    @PatchMapping("/updateCodeContent")
    public void updateCodeContent(@RequestParam("csulbId") int csulbId, @RequestBody Code code) {
        userCodingProblemService.updateCodeContent(csulbId, code.getContent());
    }

    @PatchMapping("/updateIsSolved")
    public void updateIsSolved(@RequestParam("csulbId") int csulbId, @RequestParam("isSolved") boolean isSolved) {
        userCodingProblemService.updateIsSolved(csulbId, isSolved);
    }
}
