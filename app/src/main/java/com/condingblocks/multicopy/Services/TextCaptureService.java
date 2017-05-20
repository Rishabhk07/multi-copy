package com.condingblocks.multicopy.Services;

import android.app.Dialog;
import android.app.Presentation;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;


import com.condingblocks.multicopy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TextCaptureService extends Service {
    ClipboardManager clipboardManager;
    static boolean toggleService = false;
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
        toggleService = true;
        final ArrayList<String> copiedDataArray = new ArrayList<>();


            clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    Log.d(TAG, "onPrimaryClipChanged: before changing");
                    if(toggleService) {
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
                }
            });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        stopSelf();
        toggleService = false;
    }
}
