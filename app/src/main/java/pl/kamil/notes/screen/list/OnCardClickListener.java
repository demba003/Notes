package pl.kamil.notes.screen.list;

import pl.kamil.notes.db.NoteEntity;

public interface OnCardClickListener {
    public void onClick(NoteEntity note);
}
