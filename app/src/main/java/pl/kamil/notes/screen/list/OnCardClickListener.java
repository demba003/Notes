package pl.kamil.notes.screen.list;

import pl.kamil.notes.db.NoteEntity;

interface OnCardClickListener {
    void onClick(NoteEntity note);
}
