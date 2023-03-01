
package com.team1159ers.coffee_coder_db.service.codingproblemexample;

import com.team1159ers.coffee_coder_db.model.codingproblemexample.CodingProblemExample;
import com.team1159ers.coffee_coder_db.repository.codingproblemexample.CodingProblemExampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingProblemExampleServiceImplementation implements CodingProblemExampleService {

    private final CodingProblemExampleRepository codingProblemExampleRepository;

    public CodingProblemExampleServiceImplementation(CodingProblemExampleRepository codingProblemExampleRepository) {
        this.codingProblemExampleRepository = codingProblemExampleRepository;
    }

    @Override
    public CodingProblemExample saveCodingProblemExample(CodingProblemExample codingProblemExample) {
        return codingProblemExampleRepository.save(codingProblemExample);
    }

    @Override
    public List<CodingProblemExample> saveAllCodingProblemExamples(List<CodingProblemExample> codingProblemExamples) {
        return codingProblemExampleRepository.saveAll(codingProblemExamples);
    }

    @Override
    public List<CodingProblemExample> getAllCodingProblemExamples() {
        return codingProblemExampleRepository.findAll();
    }
}
