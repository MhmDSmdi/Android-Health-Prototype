package com.bluecode.mhmd.prototypes.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.bluecode.mhmd.prototypes.R;

import java.util.ArrayList;
import java.util.List;

public class LineChart<V, T> extends View {

    private int minXCoordinate = 0;
    private int maxXCoordinate = 100;
    private int minYCoordinate = 0;
    private int maxYCoordinate = 100;
    private int lineColor = Color.DKGRAY;
    private int backgroundColor = Color.WHITE;
    private float lineWidth = 2;
    private int indicatorRadius = 2;
    private int indicatorColor = Color.BLUE;
    private int zoom = 1;

    private RectF rectF;
    private Paint backgroundPaint;
    private Paint linePaint;
    private Paint indicatorPaint;

    private float xScaleCoordinate;
    private float yScaleCoordinate;


    private List<Pair<Integer, Integer>> pairList;


    public LineChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rectF = new RectF();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LineChart,
                0, 0);
        try {
            minXCoordinate = typedArray.getInt(R.styleable.LineChart_minXCoordinate, minXCoordinate);
            maxXCoordinate = typedArray.getInt(R.styleable.LineChart_maxXCoordinate, maxXCoordinate);
            minYCoordinate = typedArray.getInt(R.styleable.LineChart_minYCoordinate, minYCoordinate);
            maxYCoordinate = typedArray.getInt(R.styleable.LineChart_maxYCoordinate, maxYCoordinate);
            lineColor = typedArray.getInt(R.styleable.LineChart_lineColor, lineColor);
            backgroundColor = typedArray.getInt(R.styleable.LineChart_backgroundColor, backgroundColor);
            lineWidth = typedArray.getDimension(R.styleable.LineChart_lineWidth, lineWidth);
            indicatorRadius = typedArray.getInt(R.styleable.LineChart_indicatorRadius, indicatorRadius);
            indicatorColor = typedArray.getInt(R.styleable.LineChart_indicatorColor, indicatorColor);
            zoom = typedArray.getInt(R.styleable.LineChart_zoom, zoom);
        } finally {
            typedArray.recycle();
        }

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(lineWidth);

        indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        indicatorPaint.setColor(indicatorColor);
        indicatorPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        setMeasuredDimension(width, height);
        xScaleCoordinate = width / (float)(maxXCoordinate - minXCoordinate);
        yScaleCoordinate = height / (float)(maxYCoordinate - minYCoordinate);
        Log.d("Tag", "onMeasure: " + width + "   -  " + height + "  - " + xScaleCoordinate + "  - " + yScaleCoordinate);
        rectF.set(0, 0, width , height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectF, backgroundPaint);
        List<Pair<Float, Float>> pairs = scaleProcessing(pairList);
//        drawNode(canvas, pairs);
        drawLine(canvas, pairs);
    }

    public void setDataSet(List<Pair<Integer, Integer>> pairList) {
        this.pairList = pairList;
        invalidate();
    }

    private List<Pair<Float, Float>> scaleProcessing(List<Pair<Integer, Integer>> pairList) {
        List<Pair<Float, Float>> tempList = new ArrayList<>();
        for (Pair<Integer, Integer> pair : pairList) {
            float newX = xScaleCoordinate * pair.first;
            float newY = yScaleCoordinate * pair.second;
            tempList.add(new Pair<Float, Float>(newX, newY));
        }
        return tempList;
    }

    private void drawNode(Canvas canvas,  List<Pair<Float, Float>> pairs) {
        for (Pair<Float, Float> pair : pairs) {
            canvas.drawCircle(pair.first, maxYCoordinate * yScaleCoordinate -  pair.second, 5, indicatorPaint);
        }
    }

    private float[] pairToArray(List<Pair<Float, Float>> pairs) {
        float[] floats = new float[pairs.size() * 2];
        int flag = 0;
        for (int i = 0; i < pairs.size() ; i++) {
            floats[flag] = pairs.get(i).first;
            floats[flag + 1] = maxYCoordinate * yScaleCoordinate - pairs.get(i).second;
            flag += 2;
        }
        return floats;
    }

    private void drawLine(Canvas canvas, List<Pair<Float, Float>> pairs) {
        Path path = new Path();
        path.moveTo(0,maxYCoordinate * yScaleCoordinate);
        for (Pair<Float, Float> pair : pairs) {
            path.lineTo(pair.first, maxYCoordinate * yScaleCoordinate -  pair.second);
        }
        path.lineTo(maxXCoordinate * xScaleCoordinate,maxYCoordinate * yScaleCoordinate);
        canvas.drawPath(path, indicatorPaint);
    }
}
