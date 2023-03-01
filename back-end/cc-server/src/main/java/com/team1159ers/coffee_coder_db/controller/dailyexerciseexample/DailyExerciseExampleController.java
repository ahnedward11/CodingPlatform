
package com.team1159ers.coffee_coder_db.controller.dailyexerciseexample;

import com.team1159ers.coffee_coder_db.model.dailyexerciseexample.DailyExerciseExample;
import com.team1159ers.coffee_coder_db.service.dailyexerciseexample.DailyExerciseExampleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/daily_exercise_example", consumes = MediaType.ALL_VALUE)
public class DailyExerciseExampleController {

    private final DailyExerciseExampleService dailyExerciseExampleService;

    public DailyExerciseExampleController(DailyExerciseExampleService dailyExerciseExampleService) {
        this.dailyExerciseExampleService = dailyExerciseExampleService;
    }

    @PostMapping("/add")
    public String add(@RequestBody DailyExerciseExample dailyExerciseExample) {
        dailyExerciseExampleService.saveDailyExerciseExample(dailyExerciseExample);
        return "New daily exercise example has been added.";
    }

    @GetMapping("/getAll")
    public List<DailyExerciseExample> list(){
        return dailyExerciseExampleService.getAllDailyExerciseExamples();
    }
}
