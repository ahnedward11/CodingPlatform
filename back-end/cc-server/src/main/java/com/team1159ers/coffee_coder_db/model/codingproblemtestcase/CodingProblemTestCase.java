
package com.team1159ers.coffee_coder_db.model.codingproblemtestcase;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;

import javax.persistence.*;

@Entity
@Table(name = "coding_problem_test_cases")
public class CodingProblemTestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", nullable = false)
    private int testId;

    @Column(name = "input", length = 100_000, nullable = false)
    private String testInput;

    @Column(name = "output", length = 100_000, nullable = false)
    private String testOutput;

    @JsonBackReference(value = "coding_problem_test")
    @ManyToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "problem_id", nullable = false)
    private CodingProblem codingProblem;

    public CodingProblemTestCase() {
        this.codingProblem = new CodingProblem();
        this.testInput = "<input>";
        this.testOutput = "<output>";
    }

    public CodingProblemTestCase(CodingProblem codingProblem, String input, String output) {
        this.codingProblem = codingProblem;
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

    public CodingProblem getCodingProblem() {
        return codingProblem;
    }

    public void setCodingProblem(CodingProblem codingProblem) {
        this.codingProblem = codingProblem;
    }
}
