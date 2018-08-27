package com.bluecode.mhmd.prototypes;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bluecode.mhmd.prototypes.Component.CircleProgressBar;

public class FitnessApp1 extends Activity implements SensorEventListener {

    private CircleProgressBar myCircleProgress;
    private SensorManager mSensorManager;
    private Sensor mStep;

    private int stepDetector = 0;
    private int counterSteps = 0;
    private int stepCounter = 0;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.bluecode.mhmd.prototypes.R.layout.activity_main);
        myCircleProgress = findViewById(com.bluecode.mhmd.prototypes.R.id.progressBar);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStep = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_STEP_COUNTER:
                if (counterSteps < 1) {
                    counterSteps = (int)sensorEvent.values[0];
                }
                progress = (int)sensorEvent.values[0] - counterSteps;
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                stepDetector++;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mStep, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
        