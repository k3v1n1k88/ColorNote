package com.example.lap10581_local.colornotes.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

public class LinedEditTextView extends android.support.v7.widget.AppCompatEditText {
    private Paint mPaint = new Paint();

    public LinedEditTextView(Context context) {
        super(context);
        initPaint();
    }

    public LinedEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LinedEditTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x80000000);
    }

    @Override protected void onDraw(Canvas canvas) {
        int left = getLeft();
        int right = getRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int height = getMaxHeight();
        int lineHeight = getLineHeight();
        int count = (height-paddingTop-paddingBottom) / lineHeight;

        for (int i = 0; i < getHeight(); i++) {
            int baseline = lineHeight * (i+1)+paddingTop;
            canvas.drawLine(left+paddingLeft, baseline, right-paddingRight, baseline, mPaint);

        }
        Log.d("info",String.valueOf(count));
        super.onDraw(canvas);
    }
}