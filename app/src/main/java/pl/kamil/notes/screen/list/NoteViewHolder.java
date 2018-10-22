package pl.kamil.notes.screen.list;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pl.kamil.notes.R;

public class NoteViewHolder extends RecyclerView.ViewHolder{
    final TextView date;
    final TextView content;
    final CardView card;

    NoteViewHolder(View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        content = itemView.findViewById(R.id.content);
        card = itemView.findViewById(R.id.card);
    }
}
