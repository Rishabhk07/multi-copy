package com.condingblocks.multicopy.Services;

import android.app.IntentService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }
    public static final String TAG = "IntentService";

    @Override
    protected void onHandleIntent(Intent intent) {
        final ArrayList<String> copiedDataArray = new ArrayList<>();

        final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                Log.d(TAG, "onPrimaryClipChanged: ");
                final ClipData clipData = clipboardManager.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);
                String thisText = item.getText().toString();
                String sb = "";
                copiedDataArray.add(thisText);
                for (String text : copiedDataArray) {
                    sb += text + "\n";
                }

                ClipData copiedClip = ClipData.newPlainText("copiedClip", sb);
                clipboardManager.removePrimaryClipChangedListener(this);
                clipboardManager.setPrimaryClip(copiedClip);
                clipboardManager.addPrimaryClipChangedListener(this);
            }
        });
    }


}
