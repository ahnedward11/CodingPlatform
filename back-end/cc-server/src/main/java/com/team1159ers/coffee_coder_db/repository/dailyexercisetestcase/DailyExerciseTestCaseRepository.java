
package com.team1159ers.coffee_coder_db.repository.dailyexercisetestcase;

import com.team1159ers.coffee_coder_db.model.codingproblemtestcase.CodingProblemTestCase;
import com.team1159ers.coffee_coder_db.model.dailyexercisetestcase.DailyExerciseTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyExerciseTestCaseRepository extends JpaRepository<DailyExerciseTestCase, Integer> {
    @Query("SELECT detc " +
        "FROM DailyExerciseTestCase detc " +
        "WHERE detc.dailyExercise.exerciseId = :exerciseId")
    List<DailyExerciseTestCase> getDailyExerciseTestCasesByExerciseId(@Param("exerciseId") int exerciseId);
}
