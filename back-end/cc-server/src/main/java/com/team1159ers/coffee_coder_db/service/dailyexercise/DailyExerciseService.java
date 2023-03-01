
package com.team1159ers.coffee_coder_db.service.dailyexercise;

import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;

import java.util.List;

public interface DailyExerciseService {
    DailyExercise saveDailyExercise(DailyExercise dailyExercise);
    List<DailyExercise> saveAllDailyExercises(List<DailyExercise> dailyExercises);
    DailyExercise getDailyExerciseById(int exerciseId);
    List<DailyExercise> getAllDailyExercises();
}
