
package com.team1159ers.coffee_coder_db.repository.codingproblem;

import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodingProblemRepository extends JpaRepository<CodingProblem, Integer> {
    @Query("SELECT cp " +
                "FROM CodingProblem cp " +
                "WHERE cp.problemId = :problemId")
    CodingProblem getCodingProblemByProblemId(@Param("problemId") int problemId);

    @Query(nativeQuery = true, value =
            "SELECT de.exercise_id " +
                "FROM coding_problems cp " +
                    "INNER JOIN problem_categories pc ON cp.problem_id = pc.problem_id " +
                    "INNER JOIN daily_exercises de ON pc.exercise_id = de.exercise_id " +
                "WHERE cp.problem_id = :problemId")
    List<Integer> getCategoryIds(@Param("problemId") int problemId);

    @Query(nativeQuery = true, value =
            "SELECT category " +
                "FROM coding_problems cp " +
                    "INNER JOIN problem_categories pc ON cp.problem_id = pc.problem_id " +
                    "INNER JOIN daily_exercises de ON pc.exercise_id = de.exercise_id " +
                "WHERE cp.problem_id = :problemId")
    List<String> getCategoryNames(@Param("problemId") int problemId);

    @Query("SELECT cp " +
                "FROM CodingProblem cp " +
                "WHERE cp.difficultyCategory = :difficulty")
    List<CodingProblem> getProblemsByDifficulty(@Param("difficulty") String difficulty);
}
