package com.condingblocks.multicopy.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Services.TextCaptureService;

public class MainActivity extends AppCompatActivity {
    Button startBtn;
    Button stopBtn;
    public static int OVERLAY_REQ_CODE = 1234;
    public static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        startBtn = (Button) findViewById(R.id.btnStart);
        stopBtn = (Button) findViewById(R.id.btnStop);

        final Intent i = new Intent(MainActivity.this , TextCaptureService.class);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: ");
                if(startService(i) != null){
                    Log.d(TAG, "onClick: Service already started");
                }else{
                    Log.d(TAG, "onClick: Service is returning null");
                }
                callWindowPermission();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(i);
            }
        });

    }

    public void callWindowPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(MainActivity.this)){
                Intent i = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(i,OVERLAY_REQ_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == OVERLAY_REQ_CODE){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(!Settings.canDrawOverlays(MainActivity.this)){
                    Intent i = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(i,OVERLAY_REQ_CODE);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
