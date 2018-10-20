package pl.kamil.notes.screen.note;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Random;

import pl.kamil.notes.R;
import pl.kamil.notes.db.NoteEntity;


public class NotificationService extends IntentService {
    private static final String PARAM = "NOTE_ENTITY";

    public NotificationService() {
        super("NotificationService");
    }

    public static void scheduleNotification(Context context, NoteEntity note) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.putExtra(PARAM, note);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            NoteEntity note = (NoteEntity) intent.getSerializableExtra(PARAM);

            while(note.getNotificationTimestamp() > System.currentTimeMillis()) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(note.getNotificationTimestamp() + " " + System.currentTimeMillis());
            }

            sendNotification(note.getNoteData());
        }
    }

    private void sendNotification(String noteContent) {
        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_receipt)
                .setContentTitle(getString(R.string.note_reminder))
                .setContentText(noteContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        int notificationId = new Random().nextInt();

        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
