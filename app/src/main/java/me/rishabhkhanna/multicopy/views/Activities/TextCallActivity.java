package me.rishabhkhanna.multicopy.views.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import me.rishabhkhanna.multicopy.Interfaces.RemoveCallback;
import me.rishabhkhanna.multicopy.Interfaces.onPasteIntent;
import me.rishabhkhanna.multicopy.R;
import me.rishabhkhanna.multicopy.Utils.Serializer;
import me.rishabhkhanna.multicopy.views.Custom.MultiCopy;

import java.util.ArrayList;

public class TextCallActivity extends AppCompatActivity {
    public static final String TAG = "TextCallActivity";
    FrameLayout frameLayout;
    View view;
    RemoveCallback removeCallback;
    onPasteIntent onPasteIntent;
    String thisCopiedText;
    Boolean readOnly = false;
    public static final boolean textCall_LOG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_call);
        thisCopiedText = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString();
        readOnly = getIntent().getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY,false);
        ArrayList<String> copiedArrayList = Serializer.getStringFromSharedPrefs(this).getTextArrayList();
        copiedArrayList.add(thisCopiedText);
        Serializer.setStringToArrayPrefs(TextCallActivity.this, copiedArrayList);
        if (textCall_LOG)
        Log.d(TAG, "onCreate: TextCallActivty");
        MultiCopy multiCopy = new MultiCopy(this);
        frameLayout = (FrameLayout) findViewById(R.id.activity_text_call);
        onPasteIntent = new onPasteIntent() {
            @Override
            public void onPasteSetIntent(String buffer) {
                Intent intent = new Intent();
                intent.putExtra(Intent.EXTRA_PROCESS_TEXT,buffer);
                setResult(RESULT_OK,intent);
            }
        };

        removeCallback = new RemoveCallback() {
            @Override
            public void onViewRemoved() {
                finish();
                Log.d(TAG, "onViewRemoved: ");
            }
        };
        view = multiCopy.addToWindowManager(thisCopiedText, removeCallback,readOnly ,onPasteIntent);
        frameLayout.addView(view);
    }
}
