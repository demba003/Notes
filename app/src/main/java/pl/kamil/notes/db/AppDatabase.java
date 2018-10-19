package pl.kamil.notes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {NoteEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    private static final String DATABASE_NAME = "database";

    public abstract NoteDao noteDao();

    public synchronized static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .build();
        }
        return instance;
    }
}
