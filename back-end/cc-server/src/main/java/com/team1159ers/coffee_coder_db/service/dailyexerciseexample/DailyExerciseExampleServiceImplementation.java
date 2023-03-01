
package com.team1159ers.coffee_coder_db.service.dailyexerciseexample;

import com.team1159ers.coffee_coder_db.model.dailyexerciseexample.DailyExerciseExample;
import com.team1159ers.coffee_coder_db.repository.dailyexerciseexample.DailyExerciseExampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyExerciseExampleServiceImplementation implements DailyExerciseExampleService {

    private final DailyExerciseExampleRepository dailyExerciseExampleRepository;

    public DailyExerciseExampleServiceImplementation(DailyExerciseExampleRepository dailyExerciseExampleRepository) {
        this.dailyExerciseExampleRepository = dailyExerciseExampleRepository;
    }

    @Override
    public DailyExerciseExample saveDailyExerciseExample(DailyExerciseExample dailyExerciseExample) {
        return dailyExerciseExampleRepository.save(dailyExerciseExample);
    }

    @Override
    public List<DailyExerciseExample> saveAllDailyExerciseExamples(List<DailyExerciseExample> dailyExerciseExample) {
        return dailyExerciseExampleRepository.saveAll(dailyExerciseExample);
    }

    @Override
    public List<DailyExerciseExample> getAllDailyExerciseExamples() {
        return dailyExerciseExampleRepository.findAll();
    }
}
