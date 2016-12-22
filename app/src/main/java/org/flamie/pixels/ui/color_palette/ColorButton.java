package org.flamie.pixels.ui.color_palette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import org.flamie.pixels.util.PanelUtils;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 18/12/16 :3
 */
public class ColorButton extends View {

    private int color;
    private Paint colorPaint;

    public ColorButton(Context context, int color) {
        super(context);
        this.color = color;

        colorPaint = new Paint();
        colorPaint.setAntiAlias(true);
        colorPaint.setColor(color);
        colorPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(PanelUtils.roundedRectangle(0, 0, dp(30), dp(25), dp(6), dp(6)), colorPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(35), dp(25));
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        this.colorPaint.setColor(color);
        invalidate();
    }
}
