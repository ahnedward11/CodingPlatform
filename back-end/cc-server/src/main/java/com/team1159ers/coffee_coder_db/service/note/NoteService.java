
package com.team1159ers.coffee_coder_db.service.note;

import com.team1159ers.coffee_coder_db.model.note.Note;

import java.util.List;

public interface NoteService {
    Note saveNote(Note note);
    List<Note> getAllNotes();
}
