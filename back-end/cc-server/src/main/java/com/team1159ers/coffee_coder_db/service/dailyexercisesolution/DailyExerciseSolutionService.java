
package com.team1159ers.coffee_coder_db.service.dailyexercisesolution;

import com.team1159ers.coffee_coder_db.model.dailyexercisesolution.DailyExerciseSolution;

import java.util.List;

public interface DailyExerciseSolutionService {
    DailyExerciseSolution saveDailyExerciseSolution(DailyExerciseSolution dailyExerciseSolution);
    List<DailyExerciseSolution> getAllDailyExerciseSolutions();
}
