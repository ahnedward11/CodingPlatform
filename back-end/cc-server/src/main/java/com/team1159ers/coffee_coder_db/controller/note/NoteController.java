
package com.team1159ers.coffee_coder_db.controller.note;

import com.team1159ers.coffee_coder_db.model.note.Note;
import com.team1159ers.coffee_coder_db.service.note.NoteService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value =  "/note", consumes = MediaType.ALL_VALUE)
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public Note add(@RequestBody Note note) {
        return noteService.saveNote(note);
    }

    @GetMapping("/getAll")
    public List<Note> list(){
        return noteService.getAllNotes();
    }
}
