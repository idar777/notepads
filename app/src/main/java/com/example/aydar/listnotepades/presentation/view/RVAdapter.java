package com.example.aydar.listnotepades.presentation.view;

/**
 * Created by Aydar on 26.10.17.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aydar.listnotepades.R;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    private CustomItemClickListener listener;
    private List<String> listNotes;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nameNote;
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nameNote = (TextView)itemView.findViewById(R.id.name_note_text_view);
        }
    }

    RVAdapter(List<String> listNotes, CustomItemClickListener listener){
        this.listNotes = listNotes;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        final PersonViewHolder pvh = new PersonViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, pvh.getPosition());
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.nameNote.setText(listNotes.get(i));
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }
}