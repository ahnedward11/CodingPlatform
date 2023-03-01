
package com.team1159ers.coffee_coder_db.model.userdailyexercise;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.model.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "user_daily_exercises")
public class UserDailyExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_exercise_id", nullable = false)
    private int userExerciseId;

    @Column(name = "code_content", length = 100_000, nullable = false)
    private String codeContent;

    @Column(name = "code_output", length = 100_000, nullable = false)
    private String codeOutput;

    @Column(name = "is_solved", nullable = false)
    private boolean isSolved;

    @Column(name = "parent_user_id", nullable = false)
    private int userId;

    @Column(name = "parent_exercise_id", nullable = false)
    private int exerciseId;

    @Column(name = "parent_problem_id", nullable = false)
    private int problemId;

    @JsonBackReference(value = "user_daily_exercise")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "csulb_id", nullable = false)
    private User user;

    @JsonBackReference(value = "daily_exercise_instance")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id", nullable = false)
    private DailyExercise dailyExercise;

    public UserDailyExercise() {
        this.codeContent = "";
        this.codeOutput = "";
        this.isSolved = false;
        this.userId = 0;
        this.exerciseId = 0;
        this.problemId = 0;
        this.user = new User();
        this.dailyExercise = new DailyExercise();
    }

    public UserDailyExercise(User user, DailyExercise dailyExercise) {
        this.codeContent = dailyExercise.getCodeContent();
        this.codeOutput = "";
        this.isSolved = false;
        this.userId = user.getCsulbId();
        this.exerciseId = dailyExercise.getExerciseId();
        this.problemId = user.getCurrentProblemId();
        this.user = user;
        this.dailyExercise = dailyExercise;
    }

    public int getUserExerciseId() {
        return userExerciseId;
    }

    public void setUserExerciseId(int userExerciseId) {
        this.userExerciseId = userExerciseId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public String getCodeOutput() {
        return codeOutput;
    }

    public void setCodeOutput(String codeOutput) {
        this.codeOutput = codeOutput;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public DailyExercise getDailyExercise() {
        return dailyExercise;
    }

    public void setDailyExercise(DailyExercise dailyExercise) {
        this.dailyExercise = dailyExercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
