
package com.team1159ers.coffee_coder_db.service.codingproblemexample;

import com.team1159ers.coffee_coder_db.model.codingproblemexample.CodingProblemExample;

import java.util.List;

public interface CodingProblemExampleService {
    CodingProblemExample saveCodingProblemExample(CodingProblemExample codingProblemExample);
    List<CodingProblemExample> saveAllCodingProblemExamples(List<CodingProblemExample> codingProblemExamples);
    List<CodingProblemExample> getAllCodingProblemExamples();
}
