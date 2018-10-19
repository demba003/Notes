package pl.kamil.notes.screen.note;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import pl.kamil.notes.R;
import pl.kamil.notes.db.NoteEntity;
import pl.kamil.notes.utils.SimpleTextWatcher;

public class NoteFragment extends Fragment {

    EditText noteData;

    public NoteFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteData = view.findViewById(R.id.noteText);
        noteData.setText(((NoteActivity)getActivity()).getNote().getNoteData());

        noteData.addTextChangedListener((SimpleTextWatcher) editable -> {
            ((NoteActivity)getActivity()).getNote().setNoteData(editable.toString());
        });
    }
}
