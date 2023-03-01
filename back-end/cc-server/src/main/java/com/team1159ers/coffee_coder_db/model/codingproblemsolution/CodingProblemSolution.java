
package com.team1159ers.coffee_coder_db.model.codingproblemsolution;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team1159ers.coffee_coder_db.model.admin.Admin;
import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.report.Report;
import com.team1159ers.coffee_coder_db.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coding_problem_solutions")
public class CodingProblemSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id", nullable = false)
    private int solutionId;

    @Column(name = "problem_description", length = 100_000, nullable = false)
    private String problemDescription;

    @Column(name = "solution_code", length = 100_000, nullable = false)
    private String solutionCode;

    @Column(name = "solution_output", length = 100_000, nullable = false)
    private String solutionOutput;

    @JsonBackReference(value = "coding_problem_solution")
    @ManyToOne
    @JoinColumn(name = "coding_problem", referencedColumnName = "problem_id", nullable = false)
    private CodingProblem codingProblem;

    @JsonBackReference(value = "admin_problem_solution")
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "csulb_id", nullable = false)
    private Admin admin;

    @ManyToMany(mappedBy = "upvotedProblemSolutions")
    private List<User> upvoters;

    @ManyToMany(mappedBy = "downvotedProblemSolutions")
    private List<User> downvoters;

    @JsonManagedReference("coding_problem_report")
    @OneToMany(mappedBy = "codingProblemSolution")
    private List<Report> reports;

    public CodingProblemSolution() {
        this.problemDescription = "";
        this.solutionCode = "";
        this.solutionOutput = "";
        this.codingProblem = null;
        this.admin = new Admin();
        this.upvoters = new ArrayList<>();
        this.downvoters = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    public CodingProblemSolution(String problemDescription,
                                 String solutionCode,
                                 String solutionOutput,
                                 CodingProblem codingProblem,
                                 Admin admin) {
        this.problemDescription = problemDescription;
        this.solutionCode = solutionCode;
        this.solutionOutput = solutionOutput;
        this.codingProblem = codingProblem;
        this.admin = admin;
        this.upvoters = new ArrayList<>();
        this.downvoters = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getSolutionCode() {
        return solutionCode;
    }

    public void setSolutionCode(String solutionCode) {
        this.solutionCode = solutionCode;
    }

    public String getSolutionOutput() {
        return solutionOutput;
    }

    public void setSolutionOutput(String solutionOutput) {
        this.solutionOutput = solutionOutput;
    }

    public CodingProblem getCodingProblem() {
        return codingProblem;
    }

    public void setCodingProblem(CodingProblem codingProblem) {
        this.codingProblem = codingProblem;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<User> getUpvoters() {
        return upvoters;
    }

    public void setUpvoters(List<User> upvoters) {
        this.upvoters = upvoters;
    }

    public List<User> getDownvoters() {
        return downvoters;
    }

    public void setDownvoters(List<User> downvoters) {
        this.downvoters = downvoters;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
