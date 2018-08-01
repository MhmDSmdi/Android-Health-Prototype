package com.bluecode.mhmd.prototypes.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
        rectF.set(0, 0, width , height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectF, backgroundPaint);
    }

    public void setDataSet(List<Pair<Integer, Integer>> pairList) {
        this.pairList = pairList;
    }

    private List<Pair<Integer, Integer>> scaleProcessing(List<Pair<Integer, Integer>> pairList) {
        List<Pair<Integer, Integer>> tempList = new ArrayList<>();
        for (Pair<Integer, Integer> pair : pairList) {

        }
    }
}
