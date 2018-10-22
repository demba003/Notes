package pl.kamil.notes.screen.note;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import pl.kamil.notes.R;
import pl.kamil.notes.db.NoteEntity;
import pl.kamil.notes.utils.SimpleTextWatcher;

public class NoteFragment extends Fragment {

    private EditText noteData;
    private NoteEntity note;

    public NoteFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteData = view.findViewById(R.id.noteText);
        note = ((NoteActivity)getActivity()).getNote();
        noteData.setText(note.getNoteData());

        noteData.addTextChangedListener((SimpleTextWatcher) editable -> note.setNoteData(editable.toString()));
    }
}
