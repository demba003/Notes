package pl.kamil.notes.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "notes")
public class NoteEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String noteData;
    private long updateTimestamp;
    private boolean notificationEnabled;
    private long notificationTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteData() {
        return noteData;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

    public long getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public long getNotificationTimestamp() {
        return notificationTimestamp;
    }

    public void setNotificationTimestamp(long notificationTimestamp) {
        this.notificationTimestamp = notificationTimestamp;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public NoteEntity() {}

    @Ignore
    public NoteEntity(int id, String noteData, long updateTimestamp) {
        this.id = id;
        this.noteData = noteData;
        this.updateTimestamp = updateTimestamp;
    }

    public NoteEntity(NoteEntity note) {
        this.id = note.getId();
        this.noteData = note.getNoteData();
        this.updateTimestamp = note.getUpdateTimestamp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteEntity that = (NoteEntity) o;
        return getId() == that.getId() &&
                getUpdateTimestamp() == that.getUpdateTimestamp() &&
                getNotificationTimestamp() == that.getNotificationTimestamp() &&
                Objects.equals(getNoteData(), that.getNoteData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNoteData(), getUpdateTimestamp(), getNotificationTimestamp());
    }
}
