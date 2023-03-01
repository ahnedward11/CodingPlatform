package com.team1159ers.coffee_coder_db.model.dailyexercisesolution;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team1159ers.coffee_coder_db.model.admin.Admin;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.model.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "daily_exercise_solutions")

public class DailyExerciseSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id", nullable = false)
    private int solutionId;

    @Column(name = "exercise_description", length = 100_000, nullable = false)
    private String exerciseDescription;

    @Column(name = "solution_code", length = 100_000, nullable = false)
    private String solutionCode;

    @Column(name = "solution_output", length = 100_000, nullable = false)
    private String solutionOutput;

    @JsonBackReference(value = "daily_exercise_solution")
    @ManyToOne
    @JoinColumn(name = "daily_exercise", referencedColumnName = "exercise_id", nullable = false)
    private DailyExercise dailyExercise;

    @JsonBackReference(value = "admin_exercise_solution")
    @ManyToOne
    @JoinColumn(name = "admin", referencedColumnName = "csulb_id", nullable = false)
    private Admin admin;

    @ManyToMany(mappedBy = "upvotedExerciseSolutions")
    List<User> upvoters;

    @ManyToMany(mappedBy = "downvotedExerciseSolutions")
    List<User> downvoters;

    public DailyExerciseSolution() {

    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String problemDescription) {
        this.exerciseDescription = problemDescription;
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

    public DailyExercise getDailyExercise() {
        return dailyExercise;
    }

    public void setDailyExercise(DailyExercise dailyExercise) {
        this.dailyExercise = dailyExercise;
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
}
