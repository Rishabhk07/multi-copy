package com.condingblocks.multicopy.views.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.views.Custom.MultiCopy;

public class TextCallActivity extends AppCompatActivity {
    public static final String TAG = "TextCallActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_call);
        Log.d(TAG, "onCreate: TextCallActivty");
        MultiCopy multiCopy = new MultiCopy(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_text_call);
        View view = multiCopy.addToWindowManager();
        frameLayout.addView(view);
    }
}
