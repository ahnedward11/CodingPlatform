
package com.team1159ers.coffee_coder_db.service.codingproblemsolution;

import com.team1159ers.coffee_coder_db.model.codingproblemsolution.CodingProblemSolution;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CodingProblemSolutionService {
    CodingProblemSolution saveCodingProblemSolution(CodingProblemSolution codingProblemSolution);
    List<CodingProblemSolution> getAllCodingProblemSolutions();
    CodingProblemSolution getProblemSolution(int problemId);
    void updateSolutionCode(int solutionId, String solutionCode);
    int getUpvoteCount();
    int getDownvoteCount();
    Integer getUpvoterId(int solutionId, int csulbId);
    Integer getDownvoterId(int solutionId, int csulbId);
    void addUpvoter(int solutionId, int csulbId);
    void addDownvoter(int solutionId, int csulbId);
    void deleteUpvoter(int solutionId, int csulbId);
    void deleteDownvoter(int solutionId, int csulbId);
}
