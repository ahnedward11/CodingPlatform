
package com.team1159ers.coffee_coder_db.controller.codingproblemexample;

import com.team1159ers.coffee_coder_db.model.codingproblemexample.CodingProblemExample;
import com.team1159ers.coffee_coder_db.service.codingproblemexample.CodingProblemExampleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/coding_problem_example", consumes = MediaType.ALL_VALUE)
public class CodingProblemExampleController {

    private final CodingProblemExampleService codingProblemExampleService;

    public CodingProblemExampleController(CodingProblemExampleService codingProblemExampleService) {
        this.codingProblemExampleService = codingProblemExampleService;
    }

    @PostMapping("/add")
    public String add(@RequestBody CodingProblemExample codingProblemExample) {
        codingProblemExampleService.saveCodingProblemExample(codingProblemExample);
        return "New coding problem example has been added.";
    }

    @GetMapping("/getAll")
    public List<CodingProblemExample> list(){
        return codingProblemExampleService.getAllCodingProblemExamples();
    }
}
