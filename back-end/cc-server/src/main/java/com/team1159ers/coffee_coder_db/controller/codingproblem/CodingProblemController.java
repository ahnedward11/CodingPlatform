
package com.team1159ers.coffee_coder_db.controller.codingproblem;

import com.team1159ers.coffee_coder_db.JDoodleAPI;
import com.team1159ers.coffee_coder_db.model.Code;
import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.service.codingproblem.CodingProblemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/coding_problem", consumes = MediaType.ALL_VALUE)
public class CodingProblemController {

    private final CodingProblemService codingProblemService;

    public CodingProblemController(CodingProblemService codingProblemService) {
        this.codingProblemService = codingProblemService;
    }

    @PostMapping("/add")
    public String add(@RequestBody CodingProblem codingProblem) {
        codingProblemService.saveCodingProblem(codingProblem);
        return "New coding problem has been added.";
    }

    @GetMapping("/getByProblemId")
    public CodingProblem getCodingProblemByProblemId(@RequestParam("problemId") int problemId) {
        return codingProblemService.getCodingProblemByProblemId(problemId);
    }

    @GetMapping("/getAll")
    public List<CodingProblem> getAllCodingProblems() {
        return codingProblemService.getAllCodingProblems();
    }

    @GetMapping("/getAllCategoryIds")
    public List<Integer> getCategories(int problemId) {
        return codingProblemService.getCategoryIds(problemId);
    }

    @GetMapping("/getAllCategoryNames")
    public List<String> getCategoryNames(int problemId) {
        return codingProblemService.getCategoryNames(problemId);
    }

    @GetMapping("/getProblemsByDifficulty")
    public List<CodingProblem> getProblemsByDifficulty(@RequestParam("difficulty") String difficulty) {
        // Return all problems if the difficulty has no filter
        if (difficulty.equals("All problems")) {
            return codingProblemService.getAllCodingProblems();
        }

        return codingProblemService.getProblemsByDifficulty(difficulty);
    }

    @PostMapping("/compileCode")
    public String compileCode(@RequestBody Code code) {
        return JDoodleAPI.execute(code.getContent());
    }
}
