package com.condingblocks.multicopy.views.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Utils.Constants;
import com.google.gson.Gson;

public class NoteEditActvity extends AppCompatActivity {
    EditText etNotesDetail;
    Gson gson = new Gson();
    public static final String TAG = "NotesEdit";
    int position;
    public static final boolean notesEdit_LOG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit_actvity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Notes");
        toolbar.setTitleTextColor(Color.WHITE);
        Intent i = getIntent();
        String thisNote = i.getStringExtra(Constants.NOTES_EDIT_TEXT);
        position = i.getIntExtra(Constants.NOTES_EDIT_POSITION,0);

        etNotesDetail = (EditText) findViewById(R.id.etNoteEdit);
        if(notesEdit_LOG)
        Log.d(TAG, "onCreate: " + thisNote);
        etNotesDetail.setText(thisNote);
        if(thisNote != null)
        etNotesDetail.setSelection(thisNote.length());
    }

    @Override
    public void onBackPressed() {
        saveResultIntent();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                saveResultIntent();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void saveResultIntent(){
        Intent i = new Intent();
        if(notesEdit_LOG)
        Log.d(TAG, "saveResultIntent: " + etNotesDetail.getText());
        i.putExtra(Constants.ACTIVITY_RESULT_TEXT,etNotesDetail.getText().toString());
        i.putExtra(Constants.ACTIVITY_RESULT_POSITION,position);
        setResult(Constants.NOTES_RESULT,i);
    }
}
