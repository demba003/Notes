package pl.kamil.notes.screen.note;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Calendar;

import pl.kamil.notes.R;
import pl.kamil.notes.db.NoteEntity;
import pl.kamil.notes.utils.DateUtils;

public class ExtrasFragment extends Fragment {

    private EditText notificationTime;
    private EditText editDate;
    private Switch notificationsEnabled;
    private NoteEntity note;

    public ExtrasFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_extra_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificationTime = view.findViewById(R.id.timepick);
        editDate = view.findViewById(R.id.editDate);
        notificationsEnabled = view.findViewById(R.id.notificationsEnabled);

        note = ((NoteActivity) getActivity()).getNote();


        if (note.getUpdateTimestamp() != 0) {
            editDate.setText(DateUtils.getFormattedDate(note.getUpdateTimestamp()));
        } else {
            editDate.setText(DateUtils.getFormattedDate(System.currentTimeMillis()));
        }

        if (note.getNotificationTimestamp() != 0) {
            notificationTime.setText(DateUtils.getFormattedDate(note.getNotificationTimestamp()));
        }
        notificationTime.setEnabled(note.isNotificationEnabled());
        notificationTime.requestFocus();
        notificationTime.setOnClickListener(this::onSelectTimeClicked);

        notificationsEnabled.setChecked(note.isNotificationEnabled());
        notificationsEnabled.setOnCheckedChangeListener((compoundButton, enabled) -> {
            note.setNotificationEnabled(enabled);
            notificationTime.setEnabled(enabled);
        });
    }

    private void onSelectTimeClicked(View v) {
        Calendar cal = Calendar.getInstance();
        new TimePickerDialog(
                getContext(),
                this::onTimeSet,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
        ).show();
    }

    private void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        long timestamp = cal.getTimeInMillis();
        note.setNotificationTimestamp(timestamp);
        notificationTime.setText(DateUtils.getFormattedDate(timestamp));
        if(note.isNotificationEnabled()) {
            NotificationService.scheduleNotification(getContext(), note);
        }
    }
}
