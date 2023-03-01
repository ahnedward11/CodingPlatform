
package com.team1159ers.coffee_coder_db.model.admin;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team1159ers.coffee_coder_db.AttributeEncryptor;
import com.team1159ers.coffee_coder_db.model.codingproblemsolution.CodingProblemSolution;
import com.team1159ers.coffee_coder_db.model.dailyexercisesolution.DailyExerciseSolution;
import com.team1159ers.coffee_coder_db.model.report.Report;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admins",
        uniqueConstraints = {
                @UniqueConstraint(name = "admin_email", columnNames = "email")
        })
public class Admin {

    @Id
    @Column(name = "csulb_id", nullable = false)
    private int csulbId;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String password;

    @Column(name = "profile_name", length = 25, nullable = false)
    private String profileName;

    @Column(name = "profile_img_src", length = 10_000, nullable = false)
    private String profileImgSrc;

    @Column(name = "role", nullable = false)
    private final String role;

    @JsonManagedReference(value = "admin_exercise_solution")
    @OneToMany(mappedBy = "admin")
    private List<DailyExerciseSolution> exerciseSolutions;

    @JsonManagedReference(value = "admin_problem_solution")
    @OneToMany(mappedBy = "admin")
    private List<CodingProblemSolution> problemSolutions;

    @JsonManagedReference(value = "admin_report")
    @OneToMany(mappedBy = "admin")
    private List<Report> reports;

    // technical account details
    @Column(name = "creation_date", length = 50, nullable = false)
    private String creationDate;
    @Column(name = "last_login_timestamp", length = 50, nullable = false)
    private String lastLoginTimestamp;

    public Admin() {
        this.csulbId = 0;
        this.email = "";
        this.password = "";
        this.profileName = "";
        this.profileImgSrc = "https://icon-library.com/images/anonymous-person-icon/anonymous-person-icon-18.jpg";
        this.role = "ADMIN";
        this.reports = new ArrayList<>();
        this.exerciseSolutions = new ArrayList<>();
        this.problemSolutions = new ArrayList<>();
        this.creationDate = LocalDate.now().toString();
        String timestamp = LocalDateTime.now().toString();
        this.lastLoginTimestamp = timestamp.replace("T", " ").substring(0, timestamp.indexOf("."));
    }

    public Admin(int csulbId, String email, String password, String profileName) {
        this.csulbId = csulbId;
        this.email = email;
        this.password = password;
        this.profileName = profileName;
        // Everything else should be initially empty / set to default
        this.profileImgSrc = "https://icon-library.com/images/anonymous-person-icon/anonymous-person-icon-18.jpg";
        this.role = "ADMIN";
        this.reports = new ArrayList<>();
        this.exerciseSolutions = new ArrayList<>();
        this.problemSolutions = new ArrayList<>();
        this.creationDate = LocalDate.now().toString();
        String timestamp = LocalDateTime.now().toString();
        this.lastLoginTimestamp = timestamp.replace("T", " ").substring(0, timestamp.indexOf("."));
    }

    public int getCsulbId() {
        return csulbId;
    }

    public void setCsulbId(int csulbId) {
        this.csulbId = csulbId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileImgSrc() {
        return profileImgSrc;
    }

    public void setProfileImgSrc(String profileImgSrc) {
        this.profileImgSrc = profileImgSrc;
    }

    public String getRole() {
        return role;
    }

    public void addToReportList(Report newReport) {
        reports.add(newReport);
    }

    public List<DailyExerciseSolution> getExerciseSolutions() {
        return exerciseSolutions;
    }

    public void setExerciseSolutions(List<DailyExerciseSolution> exerciseSolutions) {
        this.exerciseSolutions = exerciseSolutions;
    }

    public List<CodingProblemSolution> getProblemSolutions() {
        return problemSolutions;
    }

    public void setProblemSolutions(List<CodingProblemSolution> problemSolutions) {
        this.problemSolutions = problemSolutions;
    }

    public void removeFromReportList(Report inputReport) {
        reports.remove(inputReport);
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(String lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}

