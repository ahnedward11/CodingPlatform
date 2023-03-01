
package com.team1159ers.coffee_coder_db.controller.dailyexercise;

import com.team1159ers.coffee_coder_db.JDoodleAPI;
import com.team1159ers.coffee_coder_db.model.Code;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.service.dailyexercise.DailyExerciseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/daily_exercise", consumes = MediaType.ALL_VALUE)
public class DailyExerciseController {

    private final DailyExerciseService dailyExerciseService;

    public DailyExerciseController(DailyExerciseService dailyExerciseService) {
        this.dailyExerciseService = dailyExerciseService;
    }

    @PostMapping("/add")
    public String add(@RequestBody DailyExercise dailyExercise) {
        dailyExerciseService.saveDailyExercise(dailyExercise);
        return "New daily exercise has been added.";
    }

    @GetMapping("/getAll")
    public List<DailyExercise> list(){
        return dailyExerciseService.getAllDailyExercises();
    }

    @PostMapping("/compileCode")
    public String compileCode(@RequestBody Code code) {
        return JDoodleAPI.execute(code.getContent());
    }
}
