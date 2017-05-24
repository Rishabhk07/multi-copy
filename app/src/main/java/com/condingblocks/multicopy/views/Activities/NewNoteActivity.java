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

import java.text.DateFormat;
import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    EditText etNotes;
    public static final String TAG = "NewNotesActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit_actvity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("New Note");
        toolbar.setTitleTextColor(Color.WHITE);
        etNotes = (EditText) findViewById(R.id.etNoteEdit);
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
        Log.d(TAG, "saveResultIntent: " + etNotes.getText());
        i.putExtra(Constants.ACTIVITY_NEW_NOTE_TEXT,etNotes.getText().toString());
        i.putExtra(Constants.ACTIVITY_NEW_NOTE_CREATED, DateFormat.getDateTimeInstance().format(new Date()));
        setResult(Constants.NEW_NOTE_ACTIVTY_KEY,i);
        Log.d(TAG, "saveResultIntent: ");
    }
}
