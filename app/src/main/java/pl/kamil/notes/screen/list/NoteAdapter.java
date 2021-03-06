package pl.kamil.notes.screen.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import pl.kamil.notes.R;
import pl.kamil.notes.db.NoteEntity;
import pl.kamil.notes.utils.DateUtils;

class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private final Context context;
    private final List<NoteEntity> notes;
    private final OnCardClickListener onClickListener;

    NoteAdapter(Context context, List<NoteEntity> notes, OnCardClickListener onClickListener) {
        this.context = context;
        this.notes = notes;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new NoteViewHolder(inflater.inflate(R.layout.element_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteEntity note = notes.get(position);
        holder.date.setText(DateUtils.getFormattedDate(note.getUpdateTimestamp()));
        holder.content.setText(note.getNoteData());
        holder.card.setOnClickListener(view -> onClickListener.onClick(note));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
