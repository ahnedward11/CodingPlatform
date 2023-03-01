
package com.team1159ers.coffee_coder_db.service.codingproblemsolution;

import com.team1159ers.coffee_coder_db.model.codingproblemsolution.CodingProblemSolution;
import com.team1159ers.coffee_coder_db.repository.codingproblemsolution.CodingProblemSolutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingProblemSolutionServiceImplementation implements CodingProblemSolutionService {

    private final CodingProblemSolutionRepository codingProblemSolutionRepository;

    public CodingProblemSolutionServiceImplementation(CodingProblemSolutionRepository codingProblemSolutionRepository) {
        this.codingProblemSolutionRepository = codingProblemSolutionRepository;
    }

    @Override
    public CodingProblemSolution saveCodingProblemSolution(CodingProblemSolution codingProblemSolution) {
        return codingProblemSolutionRepository.save(codingProblemSolution);
    }

    @Override
    public List<CodingProblemSolution> getAllCodingProblemSolutions() {
        return codingProblemSolutionRepository.findAll();
    }

    @Override
    public CodingProblemSolution getProblemSolution(int problemId) {
        return codingProblemSolutionRepository.getProblemSolution(problemId);
    }

    @Override
    public void updateSolutionCode(int solutionId, String solutionCode) {
        codingProblemSolutionRepository.updateSolutionCode(solutionId, solutionCode);
    }

    @Override
    public int getUpvoteCount() {
        return codingProblemSolutionRepository.getUpvoteCount();
    }

    @Override
    public int getDownvoteCount() {
        return codingProblemSolutionRepository.getDownvoteCount();
    }

    @Override
    public Integer getUpvoterId(int solutionId, int csulbId) {
        return codingProblemSolutionRepository.getUpvoterId(solutionId, csulbId);
    }

    @Override
    public Integer getDownvoterId(int solutionId, int csulbId) {
        return codingProblemSolutionRepository.getDownvoterId(solutionId, csulbId);
    }

    @Override
    public void addUpvoter(int solutionId, int csulbId) {
        codingProblemSolutionRepository.addUpvoter(solutionId, csulbId);
    }

    @Override
    public void addDownvoter(int solutionId, int csulbId) {
        codingProblemSolutionRepository.addDownvoter(solutionId, csulbId);
    }

    @Override
    public void deleteUpvoter(int solutionId, int csulbId) {
        codingProblemSolutionRepository.deleteUpvoter(solutionId, csulbId);
    }

    @Override
    public void deleteDownvoter(int solutionId, int csulbId) {
        codingProblemSolutionRepository.deleteDownvoter(solutionId, csulbId);
    }
}
