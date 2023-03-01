
package com.team1159ers.coffee_coder_db.model.dailyexercisetestcase;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;

import javax.persistence.*;

@Entity
@Table(name = "daily_exercise_test_cases")
public class DailyExerciseTestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", nullable = false)
    private int testId;

    @Column(name = "input", length = 100_000, nullable = false)
    private String testInput;

    @Column(name = "output", length = 100_000, nullable = false)
    private String testOutput;

    @JsonBackReference(value = "daily_exercise_test")
    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id", nullable = false)
    private DailyExercise dailyExercise;

    public DailyExerciseTestCase() {
        this.dailyExercise = new DailyExercise();
        this.testInput = "<input>";
        this.testOutput = "<output>";
    }

    public DailyExerciseTestCase(DailyExercise dailyExercise, String input, String output) {
        this.dailyExercise = dailyExercise;
        this.testInput = input;
        this.testOutput = output;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestInput() {
        return testInput;
    }

    public void setTestInput(String testInput) {
        this.testInput = testInput;
    }

    public String getTestOutput() {
        return testOutput;
    }

    public void setTestOutput(String testOutput) {
        this.testOutput = testOutput;
    }

    public DailyExercise getDailyExercise() {
        return dailyExercise;
    }

    public void setDailyExercise(DailyExercise dailyExercise) {
        this.dailyExercise = dailyExercise;
    }
}
