package pl.kamil.notes.screen.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.kamil.notes.R;
import pl.kamil.notes.db.AppDatabase;
import pl.kamil.notes.db.NoteEntity;
import pl.kamil.notes.screen.note.NoteActivity;

public class ListActivity extends AppCompatActivity {
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recycler = findViewById(R.id.recycler);

        findViewById(R.id.fab).setOnClickListener(v -> {
            Intent intent = new Intent(this, NoteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Single
                .fromCallable(() -> AppDatabase.getInstance(this).noteDao().loadAllNotes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(noteEntities -> {
                    NoteAdapter adapter = new NoteAdapter(this, noteEntities, this::onCardClick);
                    recycler.setLayoutManager(new LinearLayoutManager(this));
                    recycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                })
                .subscribe();
    }

    void onCardClick(NoteEntity entity) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("NOTE_ENTITY", entity);
        startActivity(intent);
    }
}
