
package com.team1159ers.coffee_coder_db.model.report;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team1159ers.coffee_coder_db.model.admin.Admin;
import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.codingproblemsolution.CodingProblemSolution;
import com.team1159ers.coffee_coder_db.model.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private int reportId;

    @Column(name = "report_content", length = 100_000, nullable = false)
    private String reportContent;

    @Column(name = "date_reported", nullable = false)
    private String dateReported;

    @Column(name = "has_been_reviewed", nullable = false)
    private boolean hasBeenReviewed = false;

    @JsonBackReference("coding_problem_report")
    @ManyToOne
    @JoinColumn(name = "coding_problem_report", referencedColumnName = "solution_id", nullable = false)
    private CodingProblemSolution codingProblemSolution;

    @JsonBackReference(value = "user_report")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "csulb_id", nullable = false)
    private User user;

    @JsonBackReference(value = "admin_report")
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "csulb_id")
    private Admin admin;

    public Report() {
        this.dateReported = LocalDate.now().toString();
        this.hasBeenReviewed = false;
        this.reportContent = "";
        this.admin = null;
        this.codingProblemSolution = new CodingProblemSolution();
        this.user = new User();
    }

    public Report(String reportContent, User user, CodingProblemSolution codingProblemSolution) {
        this.dateReported = LocalDate.now().toString();
        this.hasBeenReviewed = false;
        this.reportContent = reportContent;
        this.admin = null;
        this.codingProblemSolution = codingProblemSolution;
        this.user = user;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public boolean isHasBeenReviewed() {
        return hasBeenReviewed;
    }

    public void setHasBeenReviewed(boolean hasBeenReviewed) {
        this.hasBeenReviewed = hasBeenReviewed;
    }

    public CodingProblemSolution getCodingProblemSolution() {
        return codingProblemSolution;
    }

    public void setCodingProblemSolution(CodingProblemSolution codingProblemSolution) {
        this.codingProblemSolution = codingProblemSolution;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
