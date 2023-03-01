
package com.team1159ers.coffee_coder_db.model.dailyexercise;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.dailyexerciseexample.DailyExerciseExample;
import com.team1159ers.coffee_coder_db.model.dailyexercisesolution.DailyExerciseSolution;
import com.team1159ers.coffee_coder_db.model.dailyexercisetestcase.DailyExerciseTestCase;
import com.team1159ers.coffee_coder_db.model.userdailyexercise.UserDailyExercise;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "daily_exercises")
public class DailyExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id", nullable = false)
    private int exerciseId;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "exercise_description", length = 100_000, nullable = false)
    private String exerciseDescription;

    @Column(name = "code_content", length = 100_000, nullable = false)
    private String codeContent;

    @JsonManagedReference(value = "daily_exercise_example")
    @OneToMany(mappedBy = "dailyExercise")
    private List<DailyExerciseExample> exerciseExamples;

    @JsonManagedReference(value = "daily_exercise_test")
    @OneToMany(mappedBy = "dailyExercise")
    private List<DailyExerciseTestCase> exerciseTestCases;

    @JsonBackReference(value = "problem_categories")
    @ManyToMany(mappedBy = "dailyExercises")
    private List<CodingProblem> codingProblems;

    @JsonManagedReference(value = "daily_exercise_solution")
    @OneToMany(mappedBy = "dailyExercise")
    private List<DailyExerciseSolution> exerciseSolutions;

    @JsonManagedReference(value = "daily_exercise_instance")
    @OneToMany(mappedBy = "dailyExercise")
    private List<UserDailyExercise> userDailyExercises;

    public DailyExercise() {
        this.category = "";
        this.exerciseDescription = "";
        this.codeContent = "";
        this.exerciseExamples = new ArrayList<>();
        this.exerciseTestCases = new ArrayList<>();
        this.codingProblems = new ArrayList<>();
        this.exerciseSolutions = new ArrayList<>();
        this.userDailyExercises = new ArrayList<>();
    }

    public DailyExercise(String category, String exerciseDescription, String codeContent) {
        this.category = category;
        this.exerciseDescription = exerciseDescription;
        this.codeContent = codeContent;
        this.exerciseExamples = new ArrayList<>();
        this.exerciseTestCases = new ArrayList<>();
        this.codingProblems = new ArrayList<>();
        this.exerciseSolutions = new ArrayList<>();
        this.userDailyExercises = new ArrayList<>();
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int dailyExerciseId) {
        this.exerciseId = dailyExerciseId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public List<DailyExerciseExample> getExerciseExamples() {
        return exerciseExamples;
    }

    public List<DailyExerciseTestCase> getExerciseTestCases() {
        return exerciseTestCases;
    }

    public List<CodingProblem> getCodingProblems() {
        return codingProblems;
    }

    public List<DailyExerciseSolution> getExerciseSolutions() {
        return exerciseSolutions;
    }

    public void addToExampleList(DailyExerciseExample newExample) {
        exerciseExamples.add(newExample);
    }

    public void addToTestList(DailyExerciseTestCase newTest) {
        exerciseTestCases.add(newTest);
    }

    public void addToCodingProblemList(CodingProblem newProblem) {
        codingProblems.add(newProblem);
    }

    public void addToSolutionList(DailyExerciseSolution newSolution) {
        exerciseSolutions.add(newSolution);
    }

    public void setExerciseExamples(List<DailyExerciseExample> exerciseExamples) {
        this.exerciseExamples = exerciseExamples;
    }

    public void setExerciseTestCases(List<DailyExerciseTestCase> exerciseTestCases) {
        this.exerciseTestCases = exerciseTestCases;
    }

    public void setCodingProblems(List<CodingProblem> codingProblems) {
        this.codingProblems = codingProblems;
    }

    public void setExerciseSolutions(List<DailyExerciseSolution> exerciseSolutions) {
        this.exerciseSolutions = exerciseSolutions;
    }

    public List<UserDailyExercise> getUserDailyExercises() {
        return userDailyExercises;
    }

    public void setUserDailyExercises(List<UserDailyExercise> userDailyExercises) {
        this.userDailyExercises = userDailyExercises;
    }
}
