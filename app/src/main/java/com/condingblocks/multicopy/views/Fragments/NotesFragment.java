package com.condingblocks.multicopy.views.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.condingblocks.multicopy.Adapters.NotesAdapter;
import com.condingblocks.multicopy.Interfaces.onNotesEdit;
import com.condingblocks.multicopy.R;

import com.condingblocks.multicopy.Utils.Constants;
import com.condingblocks.multicopy.model.NotesModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment implements onNotesEdit {

    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    ArrayList<NotesModel> notesList = new ArrayList<>();
    public static final String TAG = "NotesFragment";
    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_notes, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvNotesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        notesList = new ArrayList<>();
        Log.d(TAG, "onCreateView: " + notesList.toString());
        notesAdapter = new NotesAdapter(notesList,getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(notesAdapter);
        refreshData();
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //since adapter is calling an activity
        Log.d(TAG, "onActivityResult: ");
        if(requestCode == Constants.NOTES_RESULT){
            Log.d(TAG, "onActivityResult: ");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData(){
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<NotesModel> notesModelRealmQuery = realm.where(NotesModel.class);
        RealmResults<NotesModel> query = notesModelRealmQuery.findAll();
        notesList.clear();
        notesList.addAll(query);
        notesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNotesEdit(String notes, int position) {
        Log.d(TAG, "onNotesEdit: " + notes);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        notesList.get(position).setNote(notes);
        notesAdapter.notifyItemChanged(position);
        realm.copyToRealm(notesList.get(position));
        realm.commitTransaction();
    }
}
