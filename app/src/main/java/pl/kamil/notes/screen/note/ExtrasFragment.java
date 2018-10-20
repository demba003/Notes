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

import java.util.Calendar;

import pl.kamil.notes.R;
import pl.kamil.notes.utils.DateUtlis;

public class ExtrasFragment extends Fragment {

    EditText notificationTime;
    EditText editDate;
    Switch notificationsEnabled;

    public ExtrasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        editDate.setText(DateUtlis.getFormattedDate(((NoteActivity)getActivity()).getNote().getUpdateTimestamp()));
        notificationTime.setText(DateUtlis.getFormattedDate(((NoteActivity)getActivity()).getNote().getNotificationTimestamp()));
        notificationsEnabled.setChecked(((NoteActivity)getActivity()).getNote().isNotificationEnabled());

        notificationTime.requestFocus();
        notificationTime.setOnClickListener(v -> {
            new TimePickerDialog(getContext(), (timePicker, i, i1) -> {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, i);
                cal.set(Calendar.MINUTE, i1);
                long timestamp = cal.getTimeInMillis();
                ((NoteActivity)getActivity()).getNote().setNotificationTimestamp(timestamp);
                notificationTime.setText(DateUtlis.getFormattedDate(((NoteActivity)getActivity()).getNote().getNotificationTimestamp()));
            }, 10, 0, true).show();
        });

        notificationsEnabled.setOnCheckedChangeListener((compoundButton, enabled) -> {
            ((NoteActivity)getActivity()).getNote().setNotificationEnabled(enabled);
            notificationTime.setEnabled(enabled);
        });
    }
}
