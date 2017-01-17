package org.flamie.pixels.ui.size_panel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import org.flamie.pixels.enums.ColorsUI;
import org.flamie.pixels.util.PanelUtils;

import static org.flamie.pixels.util.Dimen.dp;
import static org.flamie.pixels.util.MathUtils.clamp;

/**
 * Created by flamie on 03.01.17 :3
 */

public class CustomSeekBar extends View {

    /** Seek bar customization */
    private int seekBarWidth = dp(250);
    private int seekBarHeight = dp(10);
    private int seekBarRadius = dp(2);
    private int seekBarColor = ColorsUI.SEEK_BAR_COLOR.getColor();

    /** Seek bar's stroke customization */
    private int strokeHeight = dp(20);
    private int strokeWidth = dp(5);
    private int strokeColor = Color.BLACK;

    private final Paint seekBarPaint;
    private final Paint strokePaint;

    public static int strokePosition;
    private int valuePadding = dp(10);

    public CustomSeekBar(Context context) {
        super(context);
        seekBarPaint = new Paint();
        seekBarPaint.setColor(seekBarColor);
        seekBarPaint.setAntiAlias(true);
        seekBarPaint.setStyle(Paint.Style.FILL);

        strokePaint = new Paint();
        strokePaint.setColor(strokeColor);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(PanelUtils.roundedRectangle(0, strokeHeight / 4, seekBarWidth, strokeHeight / 4 + seekBarHeight, dp(seekBarRadius), dp(seekBarRadius)), seekBarPaint);
        canvas.drawLine(clamp(strokePosition, 0, seekBarWidth), 0 , clamp(strokePosition, 0, seekBarWidth), strokeHeight, strokePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();

                if(x >= -valuePadding && y <= seekBarHeight && x <= seekBarWidth + valuePadding && y >= 0) {
                    strokePosition = (int) event.getX();
                    invalidate();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(strokeHeight >= seekBarHeight) {
            setMeasuredDimension(seekBarWidth, strokeHeight);
        } else {
            setMeasuredDimension(seekBarWidth, seekBarHeight);
        }
    }

    public int getSeekBarWidth() {
        return seekBarWidth;
    }

    public int getStrokeHeight() {
        return strokeHeight;
    }

    public static int getPosition() {
        return strokePosition;
    }
}
