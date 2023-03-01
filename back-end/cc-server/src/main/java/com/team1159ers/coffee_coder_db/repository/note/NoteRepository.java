
package com.team1159ers.coffee_coder_db.repository.note;

import com.team1159ers.coffee_coder_db.model.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

}
