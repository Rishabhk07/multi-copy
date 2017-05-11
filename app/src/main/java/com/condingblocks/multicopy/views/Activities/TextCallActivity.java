package com.condingblocks.multicopy.views.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.condingblocks.multicopy.Constants.SharedPrefs;
import com.condingblocks.multicopy.Interfaces.RemoveCallback;
import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Utils.Serializer;
import com.condingblocks.multicopy.views.Custom.MultiCopy;

import java.util.ArrayList;
import java.util.Set;

public class TextCallActivity extends AppCompatActivity{
    public static final String TAG = "TextCallActivity";
    FrameLayout frameLayout;
    View view;
    RemoveCallback removeCallback;
    String thisCopiedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_call);
         thisCopiedText = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString();
        Log.d(TAG, "onCreate: TextCallActivty");
        MultiCopy multiCopy = new MultiCopy(this);
        frameLayout = (FrameLayout) findViewById(R.id.activity_text_call);
         removeCallback = new RemoveCallback() {
            @Override
            public void onViewRemoved() {

                finish();
                Log.d(TAG, "onViewRemoved: ");
            }
        };
        view = multiCopy.addToWindowManager(thisCopiedText, removeCallback);
        frameLayout.addView(view);
    }
    

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        frameLayout.removeView(view);
        ArrayList<String> copiedArrayList = Serializer.getStringFromSharedPrefs(this);
        copiedArrayList.add(thisCopiedText);
        Serializer.setStringToArrayPrefs(TextCallActivity.this,copiedArrayList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
