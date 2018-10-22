package pl.kamil.notes.screen.note;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import pl.kamil.notes.db.AppDatabase;
import pl.kamil.notes.db.NoteEntity;

public class ShutdownReceiver extends BroadcastReceiver {

    private final NoteEntity note;

    public ShutdownReceiver(NoteEntity note) {
        this.note = note;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        note.setUpdateTimestamp(System.currentTimeMillis());
        new Thread(() -> AppDatabase.getInstance(context).noteDao().insertNote(note)).start();
    }
}
