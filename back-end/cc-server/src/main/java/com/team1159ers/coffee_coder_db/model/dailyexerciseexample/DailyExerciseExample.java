
package com.team1159ers.coffee_coder_db.model.dailyexerciseexample;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;

import javax.persistence.*;

@Entity
@Table(name = "daily_exercise_examples")
public class DailyExerciseExample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_id", nullable = false)
    private int exampleId;

    @Column(name = "input", length = 100_000, nullable = false)
    private String exampleInput;

    @Column(name = "output", length = 100_000, nullable = false)
    private String exampleOutput;

    @JsonBackReference(value = "daily_exercise_example")
    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id", nullable = false)
    private DailyExercise dailyExercise;

    public DailyExerciseExample() {
        this.dailyExercise = new DailyExercise();
        this.exampleInput = "<input>";
        this.exampleOutput = "<output>";
    }

    public DailyExerciseExample(DailyExercise dailyExercise, String input, String output) {
        this.dailyExercise = dailyExercise;
        this.exampleInput = input;
        this.exampleOutput = output;
    }

    public int getExampleId() {
        return exampleId;
    }

    public void setExampleId(int exampleId) {
        this.exampleId = exampleId;
    }

    public String getExampleInput() {
        return exampleInput;
    }

    public void setExampleInput(String exampleInput) {
        this.exampleInput = exampleInput;
    }

    public String getExampleOutput() {
        return exampleOutput;
    }

    public void setExampleOutput(String exampleOutput) {
        this.exampleOutput = exampleOutput;
    }

    public DailyExercise getDailyExercise() {
        return dailyExercise;
    }

    public void setDailyExercise(DailyExercise dailyExercise) {
        this.dailyExercise = dailyExercise;
    }
}
