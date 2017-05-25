package com.condingblocks.multicopy.Utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.condingblocks.multicopy.Adapters.NotesAdapter;
import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.model.NotesModel;
import com.condingblocks.multicopy.views.Activities.BaseActivity;
import com.condingblocks.multicopy.views.Fragments.NotesFragment;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 25/05/17.
 */

public class Toolbar_ActionMode_Callback implements ActionMode.Callback {
    private Activity activity;
    private NotesAdapter notesAdapter;
    private ArrayList<NotesModel> notes_model;
    private NotesFragment notesFragment;

    public Toolbar_ActionMode_Callback(Activity activity, NotesAdapter notesAdapter, ArrayList<NotesModel> notes_model, NotesFragment notesFragment) {
        this.activity = activity;
        this.notesAdapter = notesAdapter;
        this.notes_model = notes_model;
        this.notesFragment = notesFragment;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        menu.findItem(R.id.delete_menu).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_menu:
                notesFragment.deleteRows();
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
            notesAdapter.removeSelection(); //remove selection
            notesFragment.setNullToActionMode();
//            notesFragment.setNullToActionMode();

    }
}
