
package com.team1159ers.coffee_coder_db.repository.dailyexercise;

import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyExerciseRepository extends JpaRepository<DailyExercise, Integer> {
    @Query("SELECT de " +
                "FROM DailyExercise de " +
                "WHERE de.exerciseId = :exerciseId")
    DailyExercise getDailyExerciseById(@Param("exerciseId") int exerciseId);
}
