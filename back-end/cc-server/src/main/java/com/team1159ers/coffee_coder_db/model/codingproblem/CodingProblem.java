
package com.team1159ers.coffee_coder_db.model.codingproblem;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team1159ers.coffee_coder_db.model.codingproblemexample.CodingProblemExample;
import com.team1159ers.coffee_coder_db.model.codingproblemsolution.CodingProblemSolution;
import com.team1159ers.coffee_coder_db.model.codingproblemtestcase.CodingProblemTestCase;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.model.usercodingproblem.UserCodingProblem;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coding_problems")
public class CodingProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id", nullable = false)
    private int problemId;

    @Column(name = "problem_name", nullable = false)
    private String problemName;

    @Column(name = "difficulty_category", nullable = false)
    private String difficultyCategory;

    @Column(name = "problem_description", length = 100_000, nullable = false)
    private String problemDescription;

    @Column(name = "code_content", length = 100_000, nullable = false)
    private String codeContent;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "problem_categories",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<DailyExercise> dailyExercises;

    @JsonManagedReference(value = "coding_problem_example")
    @OneToMany(mappedBy = "codingProblem")
    private List<CodingProblemExample> problemExamples;

    @JsonManagedReference(value = "coding_problem_test")
    @OneToMany(mappedBy = "codingProblem")
    private List<CodingProblemTestCase> problemTests;

    @JsonManagedReference(value = "coding_problem_instance")
    @OneToMany(mappedBy = "codingProblem")
    private List<UserCodingProblem> userCodingProblems;

    @JsonManagedReference(value = "coding_problem_solution")
    @OneToMany(mappedBy = "codingProblem")
    private List<CodingProblemSolution> problemSolutions;

    public CodingProblem() {
        this.problemName = "";
        this.difficultyCategory = "";
        this.problemDescription = "";
        this.codeContent = "";
        this.dailyExercises = new ArrayList<>();
        this.problemExamples = new ArrayList<>();
        this.problemTests = new ArrayList<>();
        this.userCodingProblems = new ArrayList<>();
        this.problemSolutions = new ArrayList<>();
    }

    public CodingProblem(String problemName, String difficultyCategory,
                         String problemDescription, String codeContent) {
        this.problemName = problemName;
        this.difficultyCategory = difficultyCategory;
        this.problemDescription = problemDescription;
        this.codeContent = codeContent;
        this.dailyExercises = new ArrayList<>();
        this.problemExamples = new ArrayList<>();
        this.problemTests = new ArrayList<>();
        this.userCodingProblems = new ArrayList<>();
        this.problemSolutions = new ArrayList<>();
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getDifficultyCategory() {
        return difficultyCategory;
    }

    public void setDifficultyCategory(String difficultyCategory) {
        this.difficultyCategory = difficultyCategory;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public List<DailyExercise> getDailyExercises() {
        return dailyExercises;
    }

    public List<CodingProblemExample> getProblemExamples() {
        return problemExamples;
    }

    public List<CodingProblemTestCase> getProblemTests() {
        return problemTests;
    }

    public List<UserCodingProblem> getUserCodingProblems() {
        return userCodingProblems;
    }

    public List<CodingProblemSolution> getProblemSolutions() {
        return problemSolutions;
    }

    public void addToDailyExerciseList(DailyExercise newExercise) {
        dailyExercises.add(newExercise);
    }

    public void addToExampleList(CodingProblemExample newExample) {
        problemExamples.add(newExample);
    }

    public void addToTestList(CodingProblemTestCase newTest) {
        problemTests.add(newTest);
    }

    public void addToUserProblemList(UserCodingProblem newProblem) {
        userCodingProblems.add(newProblem);
    }

    public void addToSolutionList(CodingProblemSolution newSolution) {
        problemSolutions.add(newSolution);
    }

    public void setDailyExercises(List<DailyExercise> dailyExercises) {
        this.dailyExercises = dailyExercises;
    }

    public void setProblemExamples(List<CodingProblemExample> problemExamples) {
        this.problemExamples = problemExamples;
    }

    public void setProblemTests(List<CodingProblemTestCase> problemTests) {
        this.problemTests = problemTests;
    }

    public void setUserCodingProblems(List<UserCodingProblem> userCodingProblems) {
        this.userCodingProblems = userCodingProblems;
    }

    public void setProblemSolutions(List<CodingProblemSolution> problemSolutions) {
        this.problemSolutions = problemSolutions;
    }
}
