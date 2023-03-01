
package com.team1159ers.coffee_coder_db.model.usercodingproblem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "user_coding_problems")
public class UserCodingProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_problem_id", nullable = false)
    private int userProblemId;

    @Column(name = "code_content", length = 100_000, nullable = false)
    private String codeContent;

    @Column(name = "code_output", length = 100_000, nullable = false)
    private String codeOutput;

    @Column(name = "is_solved", nullable = false)
    private boolean isSolved;

    @Column(name = "parent_user_id", nullable = false)
    private int userId;

    @Column(name = "parent_problem_id", nullable = false)
    private int problemId;

    @JsonBackReference(value = "user_coding_problem")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "csulb_id", nullable = false)
    private User user;

    @JsonBackReference(value = "coding_problem_instance")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "problem_id", referencedColumnName = "problem_id", nullable = false)
    private CodingProblem codingProblem;

    public UserCodingProblem() {
        this.codeContent = "";
        this.codeOutput = "";
        this.isSolved = false;
        this.userId = 0;
        this.problemId = 0;
        this.user = new User();
        this.codingProblem = new CodingProblem();
    }

    public UserCodingProblem(User user, CodingProblem codingProblem) {
        this.codeContent = codingProblem.getCodeContent();
        this.codeOutput = "";
        this.isSolved = false;
        this.userId = user.getCsulbId();
        this.problemId = codingProblem.getProblemId();
        this.user = user;
        this.codingProblem = codingProblem;
    }

    public int getUserProblemId() {
        return userProblemId;
    }

    public void setUserProblemId(int userProblemId) {
        this.userProblemId = userProblemId;
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

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public CodingProblem getCodingProblem() {
        return codingProblem;
    }

    public void setCodingProblem(CodingProblem codingProblem) {
        this.codingProblem = codingProblem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
