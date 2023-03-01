
package com.team1159ers.coffee_coder_db.service.codingproblemtestcase;

import com.team1159ers.coffee_coder_db.model.codingproblemtestcase.CodingProblemTestCase;

import java.util.List;

public interface CodingProblemTestCaseService {
    CodingProblemTestCase saveCodingProblemTestCase(CodingProblemTestCase codingProblemTestCase);
    List<CodingProblemTestCase> saveAllCodingProblemTestCases(List<CodingProblemTestCase> codingProblemTestCases);
    List<CodingProblemTestCase> getAllCodingProblemTestCases();
    List<CodingProblemTestCase> getCodingProblemTestCasesByCodingProblemId(int codingProblemId);
}
