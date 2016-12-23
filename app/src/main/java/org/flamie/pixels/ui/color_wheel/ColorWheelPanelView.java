package org.flamie.pixels.ui.color_wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import org.flamie.pixels.enums.ColorsUI;
import org.flamie.pixels.util.PanelUtils;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 19/12/16 :3
 */
public class ColorWheelPanelView extends View {

    private Paint panelPaint;

    public ColorWheelPanelView(Context context) {
        super(context);

        panelPaint = new Paint();
        panelPaint.setColor(ColorsUI.PANELS_COLOR.getColor());
        panelPaint.setStyle(Paint.Style.FILL);
        panelPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(PanelUtils.roundedRectangle(0, 0, dp(200), dp(270), dp(4), dp(4)), panelPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(200), dp(270));
    }

}
