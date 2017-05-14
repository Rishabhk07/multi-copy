package com.condingblocks.multicopy.views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.condingblocks.multicopy.Adapters.CopyDataAdapter;
import com.condingblocks.multicopy.Adapters.NotesAdapter;
import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.model.NotesModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    RecyclerView recyclerView;
    public static final String TAG = "NotesFragment";
    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_notes, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvNotesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<NotesModel> notesModelRealmQuery = realm.where(NotesModel.class);
        RealmResults<NotesModel> query = notesModelRealmQuery.findAll();
        ArrayList<NotesModel> notesList = new ArrayList<>();
        notesList.addAll(query);
        Log.d(TAG, "onCreateView: " + notesList.toString());
        NotesAdapter notesAdapter = new NotesAdapter(notesList,getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(notesAdapter);
        return root;

    }

}
