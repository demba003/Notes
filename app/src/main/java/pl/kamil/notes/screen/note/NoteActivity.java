package pl.kamil.notes.screen.note;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import pl.kamil.notes.R;
import pl.kamil.notes.db.AppDatabase;
import pl.kamil.notes.db.NoteEntity;

public class NoteActivity extends AppCompatActivity {

    private NoteEntity note = new NoteEntity();
    private NoteEntity original = new NoteEntity();
    private BroadcastReceiver receiver;

    NoteEntity getNote() {
        return note;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.create_your_note);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        NoteEntity openedNote = (NoteEntity) getIntent().getSerializableExtra("NOTE_ENTITY");
        if (openedNote != null) {
            note = openedNote;
            original = new NoteEntity(note);
            setTitle(R.string.edit_your_note);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new NoteFragment())
                .commit();

        receiver = new ShutdownReceiver(note);
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_SHUTDOWN));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new NoteFragment())
                        .commit();
                return true;
            case R.id.navigation_dashboard:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new ExtrasFragment())
                        .commit();
                return true;
        }
        return false;
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove:
                new Thread(() -> AppDatabase.getInstance(NoteActivity.this).noteDao().removeNote(note)).start();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        saveIfChanged();
        super.onBackPressed();
    }

    private void saveIfChanged() {
        if (!note.equals(original)) {
            note.setUpdateTimestamp(System.currentTimeMillis());
            new Thread(() -> AppDatabase.getInstance(NoteActivity.this).noteDao().insertNote(note)).start();
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
