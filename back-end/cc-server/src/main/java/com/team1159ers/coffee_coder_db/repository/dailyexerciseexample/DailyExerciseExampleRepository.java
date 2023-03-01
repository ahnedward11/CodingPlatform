
package com.team1159ers.coffee_coder_db.repository.dailyexerciseexample;

import com.team1159ers.coffee_coder_db.model.dailyexerciseexample.DailyExerciseExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyExerciseExampleRepository extends JpaRepository<DailyExerciseExample, Integer> {

}
