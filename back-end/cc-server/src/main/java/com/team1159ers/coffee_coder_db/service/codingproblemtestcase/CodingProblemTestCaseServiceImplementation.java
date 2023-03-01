
package com.team1159ers.coffee_coder_db.service.codingproblemtestcase;

import com.team1159ers.coffee_coder_db.model.codingproblemtestcase.CodingProblemTestCase;
import com.team1159ers.coffee_coder_db.repository.codingproblemtestcase.CodingProblemTestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingProblemTestCaseServiceImplementation implements CodingProblemTestCaseService {

    private final CodingProblemTestCaseRepository codingProblemTestCaseRepository;

    public CodingProblemTestCaseServiceImplementation(CodingProblemTestCaseRepository codingProblemTestCaseRepository) {
        this.codingProblemTestCaseRepository = codingProblemTestCaseRepository;
    }

    @Override
    public CodingProblemTestCase saveCodingProblemTestCase(CodingProblemTestCase codingProblemTestCase) {
        return codingProblemTestCaseRepository.save(codingProblemTestCase);
    }

    @Override
    public List<CodingProblemTestCase> saveAllCodingProblemTestCases(List<CodingProblemTestCase> codingProblemTestCases) {
        return codingProblemTestCaseRepository.saveAll(codingProblemTestCases);
    }


    @Override
    public List<CodingProblemTestCase> getAllCodingProblemTestCases() {
        return codingProblemTestCaseRepository.findAll();
    }

    @Override
    public List<CodingProblemTestCase> getCodingProblemTestCasesByCodingProblemId(int codingProblemId) {
        return codingProblemTestCaseRepository.getCodingProblemTestCasesByCodingProblemId(codingProblemId);
    }
}
