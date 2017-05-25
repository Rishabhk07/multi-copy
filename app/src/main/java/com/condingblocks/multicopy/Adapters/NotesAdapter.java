package com.condingblocks.multicopy.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Utils.Constants;
import com.condingblocks.multicopy.model.NotesModel;
import com.condingblocks.multicopy.views.Activities.NoteEditActvity;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by rishabhkhanna on 14/05/17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    public ArrayList<NotesModel> notesModelArrayList;
    private Context context;
    //contexttual  Action Bar
    private SparseBooleanArray mSelectedItems;


    Gson gson = new Gson();
    public static final String TAG = "NotesAdapter";
    public NotesAdapter(ArrayList<NotesModel> notesModelArrayList, Context context) {
        this.notesModelArrayList = notesModelArrayList;
        this.context = context;
        mSelectedItems = new SparseBooleanArray();
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.notes_layout,parent,false);
        NotesViewHolder holder = new NotesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final NotesViewHolder holder, final int position) {
        final NotesModel thisNote = notesModelArrayList.get(position);
        holder.tvNote.setText(thisNote.getNote());
        holder.tvTimeStamp.setText(thisNote.getCreatedAt());
        holder.noteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NoteEditActvity.class);
                i.putExtra(Constants.NOTES_EDIT_TEXT, thisNote.getNote());
                i.putExtra(Constants.NOTES_EDIT_POSITION,position);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, Pair.create((View)holder.tvNote,"notes_text"));
                ((Activity)context).startActivityForResult(i,Constants.NOTES_RESULT);
            }
        });
        holder.itemView.setBackgroundColor(mSelectedItems.get(position)? 0x9934B5E4: Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return notesModelArrayList.size();
    }


    public class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView tvNote;
        TextView tvTimeStamp;
        CardView noteCardView;
        View selectedOverlay;

        public NotesViewHolder(View itemView) {
            super(itemView);
            tvNote = (TextView) itemView.findViewById(R.id.tvNoteText);
            tvTimeStamp = (TextView) itemView.findViewById(R.id.tvCreatedAt);
            noteCardView = (CardView) itemView.findViewById(R.id.notesCardView);
            selectedOverlay = itemView.findViewById(R.id.selected_overlay);

        }
    }

    //delete items from notes function
    public void delteNotes(){

    }



    //methods required to do selections in android
    //toggle state from selected or deselected
    public void toggleSelection(int position){
        selectView(position,!mSelectedItems.get(position));
    }
    //remove selected items
    public void removeSelection(){
        mSelectedItems = new SparseBooleanArray();
        notifyDataSetChanged();
    }
    //
    public void selectView(int position,boolean value){
        if (value)
            mSelectedItems.put(position,value);
        else
            mSelectedItems.delete(position);
        notifyDataSetChanged();
    }
    //get total selected items
    public int getSelectedCount(){
        return mSelectedItems.size();
    }
    //return all selected ids
    public SparseBooleanArray getmSelectedItems(){
        return mSelectedItems;
    }




}
