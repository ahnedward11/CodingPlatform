
package com.team1159ers.coffee_coder_db.repository.dailyexercisesolution;

import com.team1159ers.coffee_coder_db.model.dailyexercisesolution.DailyExerciseSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyExerciseSolutionRepository extends JpaRepository<DailyExerciseSolution, Integer> {

}
