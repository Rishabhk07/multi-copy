package com.condingblocks.multicopy.views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.condingblocks.multicopy.Adapters.ClipBoardAdapter;
import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Utils.Serializer;
import com.condingblocks.multicopy.model.ClipboardTextModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClipboardFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ClipBoardAdapter clipBoardAdapter;
    ArrayList<String> clipArray;
    public static final String TAG = "ClipBoardFragment";
    public ClipboardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: in clipboard fragment");
        refreshData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_clipboard, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvClipboardList);
        linearLayoutManager = new LinearLayoutManager(getContext());
        clipArray = new ArrayList<>();
        clipBoardAdapter = new ClipBoardAdapter(clipArray,getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(clipBoardAdapter);
        refreshData();
        return root;
    }

    public void refreshData(){
        ClipboardTextModel clipboardText = Serializer.getStringFromSharedPrefs(getContext());
        clipArray.clear();
        clipArray.addAll(clipboardText.getTextArrayList());
        clipBoardAdapter.notifyDataSetChanged();
    }

}
