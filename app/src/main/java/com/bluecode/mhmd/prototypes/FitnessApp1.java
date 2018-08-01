package com.bluecode.mhmd.prototypes;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bluecode.mhmd.prototypes.Component.CircleProgressBar;

public class FitnessApp1 extends Activity {

    CircleProgressBar myCircleProgress;

    int progress = 0;

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (progress < 100) {
                    progress ++;
                    myCircleProgress.setProgress(progress);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.bluecode.mhmd.prototypes.R.layout.activity_main);

        myCircleProgress = findViewById(com.bluecode.mhmd.prototypes.R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(500);
                        myHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }

}
        