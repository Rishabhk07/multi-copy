package com.condingblocks.multicopy.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Services.TextCaptureService;

public class MainActivity extends AppCompatActivity {
    Button startBtn;
    Button stopBtn;

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

                startService(i);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(i);
            }
        });

    }
}
