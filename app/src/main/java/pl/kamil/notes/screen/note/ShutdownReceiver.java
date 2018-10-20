package pl.kamil.notes.screen.note;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import pl.kamil.notes.db.AppDatabase;
import pl.kamil.notes.db.NoteEntity;

public class ShutdownReceiver extends BroadcastReceiver {

    NoteEntity note;

    public ShutdownReceiver(NoteEntity note) {
        this.note = note;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        note.setUpdateTimestamp(System.currentTimeMillis());
        new Thread(() -> AppDatabase.getInstance(context).noteDao().insertNote(note)).start();
    }
}
