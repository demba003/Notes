package pl.kamil.notes.db;

public interface NoteModel {
    int getId();
    String getNoteData();
    long getUpdateTimestamp();
}
