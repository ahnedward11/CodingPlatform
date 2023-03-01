
package com.team1159ers.coffee_coder_db.service.dailyexercisesolution;

import com.team1159ers.coffee_coder_db.model.dailyexercisesolution.DailyExerciseSolution;
import com.team1159ers.coffee_coder_db.repository.dailyexercisesolution.DailyExerciseSolutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyExerciseSolutionServiceImplementation implements DailyExerciseSolutionService {

    private final DailyExerciseSolutionRepository dailyExerciseSolutionRepository;

    public DailyExerciseSolutionServiceImplementation(DailyExerciseSolutionRepository dailyExerciseSolutionRepository) {
        this.dailyExerciseSolutionRepository = dailyExerciseSolutionRepository;
    }

    @Override
    public DailyExerciseSolution saveDailyExerciseSolution(DailyExerciseSolution dailyExerciseSolution) {
        return dailyExerciseSolutionRepository.save(dailyExerciseSolution);
    }

    @Override
    public List<DailyExerciseSolution> getAllDailyExerciseSolutions() {
        return dailyExerciseSolutionRepository.findAll();
    }
}
