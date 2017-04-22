package com.condingblocks.multicopy.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class TextCaptureService extends Service {
    ClipboardManager clipboardManager;
    public static final String TAG = "Capture Service";
    ArrayList<String> buffer = new ArrayList<>();
    public TextCaptureService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        Log.d(TAG, "onStartCommand: ");


        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                final ClipData clipData = clipboardManager.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);
                String sb = "";
                buffer.add(item.getText().toString());
                for (String b : buffer){
                    Log.d(TAG, "onClick: " + b);

                    sb += b;
                    sb += "\n";
                }

                ClipData thisClip = ClipData.newPlainText("MyClip",sb);
                clipboardManager.removePrimaryClipChangedListener(this);
                clipboardManager.setPrimaryClip(thisClip);
                clipboardManager.addPrimaryClipChangedListener(this);

            }
        });
        
        return super.onStartCommand(intent, flags, startId);
    }
}
