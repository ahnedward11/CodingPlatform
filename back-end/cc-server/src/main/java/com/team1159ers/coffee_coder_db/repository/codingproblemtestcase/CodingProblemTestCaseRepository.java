
package com.team1159ers.coffee_coder_db.repository.codingproblemtestcase;

import com.team1159ers.coffee_coder_db.model.codingproblemtestcase.CodingProblemTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodingProblemTestCaseRepository extends JpaRepository<CodingProblemTestCase, Integer> {
    @Query("SELECT cptc " +
                "FROM CodingProblemTestCase cptc " +
                "WHERE cptc.codingProblem.problemId = :codingProblemId")
    List<CodingProblemTestCase> getCodingProblemTestCasesByCodingProblemId(@Param("codingProblemId") int codingProblemId);
}
