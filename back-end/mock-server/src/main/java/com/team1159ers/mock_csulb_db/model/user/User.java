
package com.team1159ers.mock_csulb_db.model.user;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "csulb_id_gen", initialValue = 100_000_000, allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "csulb_id_gen")
    private int csulbId;
    private String email;
    private String password;
    private String profileName;
    private final String role;
    // technical account details
    private String creationDate;
    private String lastLoginTimestamp;

    public User() {
        this.email = "";
        this.password = "";
        this.profileName = "";
        this.role = "USER";
        this.creationDate = LocalDate.now().toString();
        String timestamp = LocalDateTime.now().toString();
        this.lastLoginTimestamp = timestamp.replace("T", " ").substring(0, timestamp.indexOf("."));
    }

    public User(String email, String password, String profileName) {
        this.email = email;
        this.password = password;
        this.profileName = profileName;
        this.role = "USER";
        this.creationDate = LocalDate.now().toString();
        String timestamp = LocalDateTime.now().toString();
        this.lastLoginTimestamp = timestamp.replace("T", " ").substring(0, timestamp.indexOf("."));
    }

    public int getCsulbID() {
        return csulbId;
    }

    public void setCsulbID(int csulbID) {
        this.csulbId = csulbID;
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

    public String getRole() {
        return role;
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

