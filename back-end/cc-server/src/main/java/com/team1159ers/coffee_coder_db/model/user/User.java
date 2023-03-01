
package com.team1159ers.coffee_coder_db.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team1159ers.coffee_coder_db.AttributeEncryptor;
import com.team1159ers.coffee_coder_db.model.codingproblemsolution.CodingProblemSolution;
import com.team1159ers.coffee_coder_db.model.dailyexercisesolution.DailyExerciseSolution;
import com.team1159ers.coffee_coder_db.model.note.Note;
import com.team1159ers.coffee_coder_db.model.report.Report;
import com.team1159ers.coffee_coder_db.model.usercodingproblem.UserCodingProblem;
import com.team1159ers.coffee_coder_db.model.userdailyexercise.UserDailyExercise;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email", columnNames = "email")
        })
public class User {

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

    @Column(name = "current_problem_id", nullable = false)
    private int currentProblemId;

    @Column(name = "role", nullable = false)
    private final String role;

    @JsonManagedReference(value = "user_daily_exercise")
    @OneToMany(mappedBy = "dailyExercise")
    private List<UserDailyExercise> userDailyExercises;

    @JsonManagedReference(value = "user_coding_problem")
    @OneToMany(mappedBy = "codingProblem")
    private List<UserCodingProblem> userCodingProblems;

    @JsonIgnore
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "upvoted_problem_solutions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "solution_id")
    )
    private List<CodingProblemSolution> upvotedProblemSolutions;

    @JsonIgnore
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "downvoted_problem_solutions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "solution_id")
    )
    private List<CodingProblemSolution> downvotedProblemSolutions;

    @JsonIgnore
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "upvoted_exercise_solutions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "solution_id")
    )
    private List<DailyExerciseSolution> upvotedExerciseSolutions;

    @JsonIgnore
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "downvoted_exercise_solutions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "solution_id")
    )
    private List<DailyExerciseSolution> downvotedExerciseSolutions;

    @JsonManagedReference(value = "user_note")
    @OneToMany(mappedBy = "user")
    private List<Note> notes;

    @JsonManagedReference(value = "user_report")
    @OneToMany(mappedBy = "user")
    private List<Report> reports;

    // technical account details
    @Column(name = "creation_date", length = 50, nullable = false)
    private String creationDate;
    @Column(name = "last_login_timestamp", length = 50, nullable = false)
    private String lastLoginTimestamp;

    public User() {
        this.csulbId = 0;
        this.email = "";
        this.password = "";
        this.profileName = "";
        this.profileImgSrc = "https://icon-library.com/images/anonymous-person-icon/anonymous-person-icon-18.jpg";
        this.role = "USER";
        this.currentProblemId = 1;
        this.userDailyExercises = new ArrayList<>();
        this.userCodingProblems = new ArrayList<>();
        this.upvotedProblemSolutions = new ArrayList<>();
        this.downvotedProblemSolutions = new ArrayList<>();
        this.upvotedExerciseSolutions = new ArrayList<>();
        this.downvotedExerciseSolutions = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.reports = new ArrayList<>();
        this.creationDate = LocalDate.now().toString();
        String timestamp = LocalDateTime.now().toString();
        this.lastLoginTimestamp = timestamp.replace("T", " ").substring(0, timestamp.indexOf("."));
    }

    public User(int csulbId, String email, String password, String profileName) {
        this.csulbId = csulbId;
        this.email = email;
        this.password = password;
        this.profileName = profileName;
        // Everything else should be initially empty / set to default
        this.profileImgSrc = "https://icon-library.com/images/anonymous-person-icon/anonymous-person-icon-18.jpg";
        this.role = "USER";
        this.currentProblemId = 1;
        this.userDailyExercises = new ArrayList<>();
        this.userCodingProblems = new ArrayList<>();
        this.upvotedProblemSolutions = new ArrayList<>();
        this.downvotedProblemSolutions = new ArrayList<>();
        this.upvotedExerciseSolutions = new ArrayList<>();
        this.downvotedExerciseSolutions = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.reports = new ArrayList<>();
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

    public List<UserDailyExercise> getUserDailyExercises() {
        return userDailyExercises;
    }

    public void setUserDailyExercises(List<UserDailyExercise> userDailyExercises) {
        this.userDailyExercises = userDailyExercises;
    }

    public List<UserCodingProblem> getUserCodingProblems() {
        return userCodingProblems;
    }

    public void setUserCodingProblems(List<UserCodingProblem> userCodingProblems) {
        this.userCodingProblems = userCodingProblems;
    }

    public List<CodingProblemSolution> getUpvotedProblemSolutions() {
        return upvotedProblemSolutions;
    }

    public void setUpvotedProblemSolutions(List<CodingProblemSolution> upvotedProblemSolutions) {
        this.upvotedProblemSolutions = upvotedProblemSolutions;
    }

    public List<CodingProblemSolution> getDownvotedProblemSolutions() {
        return downvotedProblemSolutions;
    }

    public void setDownvotedProblemSolutions(List<CodingProblemSolution> downvotedProblemSolutions) {
        this.downvotedProblemSolutions = downvotedProblemSolutions;
    }

    public List<DailyExerciseSolution> getUpvotedExerciseSolutions() {
        return upvotedExerciseSolutions;
    }

    public void setUpvotedExerciseSolutions(List<DailyExerciseSolution> upvotedExerciseSolutions) {
        this.upvotedExerciseSolutions = upvotedExerciseSolutions;
    }

    public List<DailyExerciseSolution> getDownvotedExerciseSolutions() {
        return downvotedExerciseSolutions;
    }

    public void setDownvotedExerciseSolutions(List<DailyExerciseSolution> downvotedExerciseSolutions) {
        this.downvotedExerciseSolutions = downvotedExerciseSolutions;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
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

    public int getCurrentProblemId() {
        return currentProblemId;
    }

    public void setCurrentProblemId(int currentProblemId) {
        this.currentProblemId = currentProblemId;
    }

    public String getRole() {
        return role;
    }

    public void addToUserExerciseList(UserDailyExercise newExercise) {
        userDailyExercises.add(newExercise);
    }

    public void addToUserProblemList(UserCodingProblem newProblem) {
        userCodingProblems.add(newProblem);
    }

    public void addToUpvoteList(CodingProblemSolution newSolution) {
        upvotedProblemSolutions.add(newSolution);
    }

    public void removeFromUpvoteList(CodingProblemSolution newSolution) {
        upvotedProblemSolutions.remove(newSolution);
    }

    public void addToDownvoteList(CodingProblemSolution newSolution) {
        downvotedProblemSolutions.add(newSolution);
    }

    public void removeFromDownvoteList(CodingProblemSolution newSolution) {
        upvotedProblemSolutions.remove(newSolution);
    }

    public void addToNoteList(Note newNote) {
        notes.add(newNote);
    }

    public void addToReportList(Report newReport) {
        reports.add(newReport);
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
}


