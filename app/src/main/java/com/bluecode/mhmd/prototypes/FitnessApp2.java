package com.bluecode.mhmd.prototypes;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.bluecode.mhmd.prototypes.Component.LineChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FitnessApp2 extends Activity {

    private LineChart<Integer, Integer> lineChart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_chart);
        lineChart = findViewById(R.id.lineChart);
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        Random rnd = new Random();
//        for (int i = 0; i < 100; i++) {
//            pairs.add(new Pair<Integer, Integer>(i, rnd.nextInt(200)));
//        }
        pairs.add(new Pair<Integer, Integer>(5, 10));
        pairs.add(new Pair<Integer, Integer>(15, 20));
        pairs.add(new Pair<Integer, Integer>(23, 30));
        pairs.add(new Pair<Integer, Integer>(35, 10));
        pairs.add(new Pair<Integer, Integer>(45, 32));
        pairs.add(new Pair<Integer, Integer>(63, 12));
        pairs.add(new Pair<Integer, Integer>(70, 23));
//        pairs.add(new Pair<Integer, Integer>(75, 80));
        pairs.add(new Pair<Integer, Integer>(94, 75));
        lineChart.setDataSet(pairs);
    }
}
