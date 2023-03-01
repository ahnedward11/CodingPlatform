
package com.team1159ers.coffee_coder_db.controller.dailyexercisesolution;

import com.team1159ers.coffee_coder_db.model.dailyexercisesolution.DailyExerciseSolution;
import com.team1159ers.coffee_coder_db.service.dailyexercisesolution.DailyExerciseSolutionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/daily_exercise_solution", consumes = MediaType.ALL_VALUE)
public class DailyExerciseSolutionController {

    private final DailyExerciseSolutionService dailyExerciseSolutionService;

    public DailyExerciseSolutionController(DailyExerciseSolutionService dailyExerciseSolutionService) {
        this.dailyExerciseSolutionService = dailyExerciseSolutionService;
    }

    @PostMapping("/add")
    public String add(@RequestBody DailyExerciseSolution dailyExerciseSolution) {
        dailyExerciseSolutionService.saveDailyExerciseSolution(dailyExerciseSolution);
        return "New daily exercise solution has been added.";
    }

    @GetMapping("/getAll")
    public List<DailyExerciseSolution> list(){
        return dailyExerciseSolutionService.getAllDailyExerciseSolutions();
    }
}
