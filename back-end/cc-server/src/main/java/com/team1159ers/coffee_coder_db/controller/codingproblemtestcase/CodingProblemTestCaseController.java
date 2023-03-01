
package com.team1159ers.coffee_coder_db.controller.codingproblemtestcase;

import com.team1159ers.coffee_coder_db.model.codingproblemtestcase.CodingProblemTestCase;
import com.team1159ers.coffee_coder_db.service.codingproblemtestcase.CodingProblemTestCaseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/coding_problem_test_case", consumes = MediaType.ALL_VALUE)
public class CodingProblemTestCaseController {

    private final CodingProblemTestCaseService codingProblemTestCaseService;

    public CodingProblemTestCaseController(CodingProblemTestCaseService codingProblemTestCaseService) {
        this.codingProblemTestCaseService = codingProblemTestCaseService;
    }

    @PostMapping("/add")
    public String add(@RequestBody CodingProblemTestCase codingProblemTestCase) {
        codingProblemTestCaseService.saveCodingProblemTestCase(codingProblemTestCase);
        return "New coding problem test case has been added.";
    }

    @GetMapping("/getAll")
    public List<CodingProblemTestCase> list(){
        return codingProblemTestCaseService.getAllCodingProblemTestCases();
    }

    @GetMapping("/getByProblemId")
    public List<CodingProblemTestCase> getByProblemId(@RequestParam("problemId") int problemId) {
        return codingProblemTestCaseService.getCodingProblemTestCasesByCodingProblemId(problemId);
    }
}
