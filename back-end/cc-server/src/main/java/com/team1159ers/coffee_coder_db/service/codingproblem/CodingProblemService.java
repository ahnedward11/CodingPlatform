
package com.team1159ers.coffee_coder_db.service.codingproblem;

import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodingProblemService {
    CodingProblem saveCodingProblem(CodingProblem codingProblem);
    CodingProblem getCodingProblemByProblemId(int problemId);
    List<CodingProblem> getAllCodingProblems();
    List<Integer> getCategoryIds(int problemId);
    List<String> getCategoryNames(int problemId);
    List<CodingProblem> getProblemsByDifficulty(String difficulty);
}
