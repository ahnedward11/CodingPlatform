
package com.team1159ers.coffee_coder_db.service.dailyexercise;

import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.repository.dailyexercise.DailyExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyExerciseServiceImplementation implements DailyExerciseService {

    private final DailyExerciseRepository dailyExerciseRepository;

    public DailyExerciseServiceImplementation(DailyExerciseRepository dailyExerciseRepository) {
        this.dailyExerciseRepository = dailyExerciseRepository;
    }

    @Override
    public DailyExercise saveDailyExercise(DailyExercise dailyExercise) {
        return dailyExerciseRepository.save(dailyExercise);
    }

    @Override
    public DailyExercise getDailyExerciseById(int exerciseId) {
        return dailyExerciseRepository.getDailyExerciseById(exerciseId);
    }

    @Override
    public List<DailyExercise> saveAllDailyExercises(List<DailyExercise> dailyExercises) {
        return dailyExerciseRepository.saveAll(dailyExercises);
    }

    @Override
    public List<DailyExercise> getAllDailyExercises() {
        return dailyExerciseRepository.findAll();
    }
}
