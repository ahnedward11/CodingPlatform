
package com.team1159ers.coffee_coder_db.model.note;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team1159ers.coffee_coder_db.model.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", nullable = false)
    private int noteId;

    @Column(name = "note_title", length = 25, nullable = false)
    private String noteTitle;

    @Column(name = "note_content", length = 100_000, nullable = false)
    private String noteContent;

    @Column(name = "date_created", length = 50, nullable = false)
    private String dateCreated;

    @JsonBackReference(value = "user_note")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "csulb_id", nullable = false)
    private User user;

    public Note() {
        this.noteTitle = "";
        this.noteContent = "";
        this.dateCreated = LocalDate.now().toString();
        this.user = new User();
    }


    public Note(String noteTitle, String noteContent, User user) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.dateCreated = LocalDate.now().toString();
        this.user = user;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
