
package com.team1159ers.coffee_coder_db.model.codingproblemexample;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;

import javax.persistence.*;

@Entity
@Table(name = "coding_problem_examples")
public class CodingProblemExample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_id", nullable = false)
    private int exampleId;

    @Column(name = "input", length = 100_000, nullable = false)
    private String exampleInput;

    @Column(name = "output", length = 100_000, nullable = false)
    private String exampleOutput;

    @JsonBackReference(value = "coding_problem_example")
    @ManyToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "problem_id", nullable = false)
    private CodingProblem codingProblem;

    public CodingProblemExample() {
        this.codingProblem = new CodingProblem();
        this.exampleInput = "<input>";
        this.exampleOutput = "<output>";
    }

    public CodingProblemExample(CodingProblem codingProblem, String input, String output) {
        this.codingProblem = codingProblem;
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

    public CodingProblem getCodingProblem() {
        return codingProblem;
    }

    public void setCodingProblem(CodingProblem codingProblem) {
        this.codingProblem = codingProblem;
    }
}
