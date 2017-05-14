package com.condingblocks.multicopy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.model.NotesModel;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 14/05/17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    private ArrayList<NotesModel> notesModelArrayList;
    private Context context;

    public NotesAdapter(ArrayList<NotesModel> notesModelArrayList, Context context) {
        this.notesModelArrayList = notesModelArrayList;
        this.context = context;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.notes_layout,parent,false);
        NotesViewHolder holder = new NotesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        NotesModel thisNote = notesModelArrayList.get(position);
        holder.tvNote.setText(thisNote.getNote());
        holder.tvTimeStamp.setText(thisNote.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return notesModelArrayList.size();
    }
    public class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView tvNote;
        TextView tvTimeStamp;
        public NotesViewHolder(View itemView) {
            super(itemView);
            tvNote = (TextView) itemView.findViewById(R.id.tvNoteText);
            tvTimeStamp = (TextView) itemView.findViewById(R.id.tvCreatedAt);
        }
    }
}
