
package com.team1159ers.coffee_coder_db.controller.codingproblemsolution;

import com.team1159ers.coffee_coder_db.model.codingproblemsolution.CodingProblemSolution;
import com.team1159ers.coffee_coder_db.service.codingproblemsolution.CodingProblemSolutionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/coding_problem_solution", consumes = MediaType.ALL_VALUE)
public class CodingProblemSolutionController {

    private final CodingProblemSolutionService codingProblemSolutionService;

    public CodingProblemSolutionController(CodingProblemSolutionService codingProblemSolutionService) {
        this.codingProblemSolutionService = codingProblemSolutionService;
    }

    @PostMapping("/add")
    public int add(@RequestBody CodingProblemSolution codingProblemSolution) {
        try {
            CodingProblemSolution problemSolution = codingProblemSolutionService
                .getProblemSolution(codingProblemSolution.getCodingProblem().getProblemId());

            if (problemSolution == null) {
                codingProblemSolutionService.saveCodingProblemSolution(codingProblemSolution);
            } else {
                codingProblemSolutionService.updateSolutionCode(
                    problemSolution.getSolutionId(), codingProblemSolution.getSolutionCode());
            }
        } catch (Exception err) {
            err.printStackTrace();
            return -1;
        }

        return 0;
    }

    @GetMapping("/getAll")
    public List<CodingProblemSolution> list(){
        return codingProblemSolutionService.getAllCodingProblemSolutions();
    }

    @GetMapping("/getProblemSolution")
    public CodingProblemSolution getProblemSolution(@RequestParam("problemId") int problemId) {
        CodingProblemSolution solution = codingProblemSolutionService.getProblemSolution(problemId);

        if (solution == null) {
            return new CodingProblemSolution();
        }

        return solution;
    }

    @GetMapping("/getUpvoteCount")
    public int getUpvoteCount() {
        return codingProblemSolutionService.getUpvoteCount();
    }

    @GetMapping("/getDownvoteCount")
    public int getDownvoteCount() {
        return codingProblemSolutionService.getDownvoteCount();
    }

    @GetMapping("/getVoteState")
    public int getVoteState(@RequestParam("solutionId") int solutionId, @RequestParam("userId") int userId) {
        Integer searchUserId = codingProblemSolutionService.getUpvoterId(solutionId, userId);
        if (searchUserId != null && searchUserId > 0) {
            return 1;
        } else {
            searchUserId = codingProblemSolutionService.getDownvoterId(solutionId, userId);
            if (searchUserId != null && searchUserId > 0) {
                return 2;
            } else {
                return 0;
            }
        }
    }

    @PostMapping("/handleUpvote")
    public int handleUpvote(@RequestParam("solutionId") int solutionId, @RequestParam("userId") int userId) {
        try {
            Integer searchUserId = codingProblemSolutionService.getUpvoterId(solutionId, userId);
            if (searchUserId != null && searchUserId > 0) {
                codingProblemSolutionService.deleteUpvoter(solutionId, userId);
            } else {
                searchUserId = codingProblemSolutionService.getDownvoterId(solutionId, userId);
                if (searchUserId != null && searchUserId > 0) {
                    codingProblemSolutionService.deleteDownvoter(solutionId, userId);
                }
                codingProblemSolutionService.addUpvoter(solutionId, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    @PostMapping("/handleDownvote")
    public int handleDownvote(@RequestParam("solutionId") int solutionId, @RequestParam("userId") int userId) {
        try {
            Integer searchUserId = codingProblemSolutionService.getDownvoterId(solutionId, userId);
            if (searchUserId != null && searchUserId > 0) {
                codingProblemSolutionService.deleteDownvoter(solutionId, userId);
            } else {
                searchUserId = codingProblemSolutionService.getUpvoterId(solutionId, userId);
                if (searchUserId != null && searchUserId > 0) {
                    codingProblemSolutionService.deleteUpvoter(solutionId, userId);
                }
                codingProblemSolutionService.addDownvoter(solutionId, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
