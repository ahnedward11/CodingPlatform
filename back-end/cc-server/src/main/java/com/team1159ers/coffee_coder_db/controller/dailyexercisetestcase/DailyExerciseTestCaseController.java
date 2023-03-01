
package com.team1159ers.coffee_coder_db.controller.dailyexercisetestcase;

import com.team1159ers.coffee_coder_db.model.dailyexercisetestcase.DailyExerciseTestCase;
import com.team1159ers.coffee_coder_db.service.dailyexercisetestcase.DailyExerciseTestCaseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/daily_exercise_test_case", consumes = MediaType.ALL_VALUE)
public class DailyExerciseTestCaseController {

    private final DailyExerciseTestCaseService dailyExerciseTestCaseService;

    public DailyExerciseTestCaseController(DailyExerciseTestCaseService dailyExerciseTestCaseService) {
        this.dailyExerciseTestCaseService = dailyExerciseTestCaseService;
    }

    @PostMapping("/add")
    public String add(@RequestBody DailyExerciseTestCase dailyExerciseTestCase) {
        dailyExerciseTestCaseService.saveDailyExerciseTestCase(dailyExerciseTestCase);
        return "New daily exercise test case has been added.";
    }

    @GetMapping("/getAll")
    public List<DailyExerciseTestCase> list(){
        return dailyExerciseTestCaseService.getAllDailyExerciseTestCases();
    }

    @GetMapping("/getByExerciseId")
    public List<DailyExerciseTestCase> getByExerciseId(@RequestParam("exerciseId") int exerciseId) {
        return dailyExerciseTestCaseService.getDailyExerciseTestCasesByExerciseId(exerciseId);
    }
}
