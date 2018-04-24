package me.rishabhkhanna.multicopy.Services;


import android.app.Service;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;

public class TextCaptureService extends Service {
    ClipboardManager clipboardManager;
    static boolean toggleService = false;
    public static final String TAG = "Capture Service";
    ArrayList<String> buffer = new ArrayList<>();
    public static final boolean textCapture_LOG = false;
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
        if (textCapture_LOG)
        Log.d(TAG, "onStartCommand: ");
        toggleService = true;
        final ArrayList<String> copiedDataArray = new ArrayList<>();


            clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    if (textCapture_LOG)
                    Log.d(TAG, "onPrimaryClipChanged: before changing");
                    if(toggleService) {
                        if (textCapture_LOG)
                        Log.d(TAG, "onPrimaryClipChanged: ");
                        final ClipData clipData = clipboardManager.getPrimaryClip();
                        if (clipData.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                                || clipData.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_HTML)
                                || clipData.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_INTENT)) {
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
                            Toast.makeText(TextCaptureService.this, "Multi Copied", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(TextCaptureService.this, "only text is allowed", Toast.LENGTH_SHORT).show();
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
        if (textCapture_LOG)
        Log.d(TAG, "onDestroy: ");
        stopSelf();
        toggleService = false;
    }
}
