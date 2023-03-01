
package com.team1159ers.coffee_coder_db.service.dailyexerciseexample;

import com.team1159ers.coffee_coder_db.model.dailyexerciseexample.DailyExerciseExample;

import java.util.List;

public interface DailyExerciseExampleService {
    DailyExerciseExample saveDailyExerciseExample(DailyExerciseExample dailyExerciseExample);
    List<DailyExerciseExample> saveAllDailyExerciseExamples(List<DailyExerciseExample> dailyExerciseExamples);
    List<DailyExerciseExample> getAllDailyExerciseExamples();
}
