package com.team1159ers.coffee_coder_db.service.dailyexercisetestcase;

import com.team1159ers.coffee_coder_db.model.dailyexercisetestcase.DailyExerciseTestCase;
import com.team1159ers.coffee_coder_db.repository.dailyexercisetestcase.DailyExerciseTestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyExerciseTestCaseServiceImplementation implements DailyExerciseTestCaseService {

    private final DailyExerciseTestCaseRepository dailyExerciseTestCaseRepository;

    public DailyExerciseTestCaseServiceImplementation(DailyExerciseTestCaseRepository dailyExerciseTestCaseRepository) {
        this.dailyExerciseTestCaseRepository = dailyExerciseTestCaseRepository;
    }

    @Override
    public DailyExerciseTestCase saveDailyExerciseTestCase(DailyExerciseTestCase dailyExerciseTestCase) {
        return dailyExerciseTestCaseRepository.save(dailyExerciseTestCase);
    }

    @Override
    public List<DailyExerciseTestCase> saveAllDailyExerciseTestCases(List<DailyExerciseTestCase> dailyExerciseTestCases) {
        return dailyExerciseTestCaseRepository.saveAll(dailyExerciseTestCases);
    }

    @Override
    public List<DailyExerciseTestCase> getAllDailyExerciseTestCases() {
        return dailyExerciseTestCaseRepository.findAll();
    }

    @Override
    public List<DailyExerciseTestCase> getDailyExerciseTestCasesByExerciseId(int exerciseId) {
        return dailyExerciseTestCaseRepository.getDailyExerciseTestCasesByExerciseId(exerciseId);
    }
}
