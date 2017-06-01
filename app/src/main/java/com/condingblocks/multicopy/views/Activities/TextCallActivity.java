package com.condingblocks.multicopy.views.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.condingblocks.multicopy.Interfaces.RemoveCallback;
import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Utils.Serializer;
import com.condingblocks.multicopy.views.Custom.MultiCopy;

import java.util.ArrayList;

public class TextCallActivity extends AppCompatActivity {
    public static final String TAG = "TextCallActivity";
    FrameLayout frameLayout;
    View view;
    RemoveCallback removeCallback;
    String thisCopiedText;
    public static final boolean textCall_LOG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_call);
        thisCopiedText = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString();
        ArrayList<String> copiedArrayList = Serializer.getStringFromSharedPrefs(this).getTextArrayList();
        copiedArrayList.add(thisCopiedText);
        Serializer.setStringToArrayPrefs(TextCallActivity.this, copiedArrayList);
        if (textCall_LOG)
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
}
