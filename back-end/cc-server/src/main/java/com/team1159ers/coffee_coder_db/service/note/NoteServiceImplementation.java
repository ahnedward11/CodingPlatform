
package com.team1159ers.coffee_coder_db.service.note;

import com.team1159ers.coffee_coder_db.model.note.Note;
import com.team1159ers.coffee_coder_db.repository.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImplementation implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImplementation(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
}
