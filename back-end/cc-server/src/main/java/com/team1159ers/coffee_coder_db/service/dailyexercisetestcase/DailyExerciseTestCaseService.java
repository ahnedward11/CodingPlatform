
package com.team1159ers.coffee_coder_db.service.dailyexercisetestcase;

import com.team1159ers.coffee_coder_db.model.dailyexercisetestcase.DailyExerciseTestCase;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyExerciseTestCaseService {
    DailyExerciseTestCase saveDailyExerciseTestCase(DailyExerciseTestCase dailyExerciseTestCase);
    List<DailyExerciseTestCase> saveAllDailyExerciseTestCases(List<DailyExerciseTestCase> dailyExerciseTestCases);
    List<DailyExerciseTestCase> getAllDailyExerciseTestCases();
    List<DailyExerciseTestCase> getDailyExerciseTestCasesByExerciseId(int exerciseId);
}
