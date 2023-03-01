
package com.team1159ers.coffee_coder_db.service.codingproblem;

import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.repository.codingproblem.CodingProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingProblemServiceImplementation implements CodingProblemService {

    private final CodingProblemRepository codingProblemRepository;

    public CodingProblemServiceImplementation(CodingProblemRepository codingProblemRepository) {
        this.codingProblemRepository = codingProblemRepository;
    }

    @Override
    public CodingProblem saveCodingProblem(CodingProblem codingProblem) {
        return codingProblemRepository.save(codingProblem);
    }

    @Override
    public CodingProblem getCodingProblemByProblemId(int problemId) {
        return codingProblemRepository.getCodingProblemByProblemId(problemId);
    }

    @Override
    public List<CodingProblem> getAllCodingProblems() {
        return codingProblemRepository.findAll();
    }

    @Override
    public List<Integer> getCategoryIds(int problemId) {
        return codingProblemRepository.getCategoryIds(problemId);
    }

    @Override
    public List<String> getCategoryNames(int problemId) {
        return codingProblemRepository.getCategoryNames(problemId);
    }

    @Override
    public List<CodingProblem> getProblemsByDifficulty(String difficulty) {
        return codingProblemRepository.getProblemsByDifficulty(difficulty);
    }
}
