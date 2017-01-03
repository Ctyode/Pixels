package org.flamie.pixels.ui.size_panel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import org.flamie.pixels.enums.ColorsUI;
import org.flamie.pixels.util.PanelUtils;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 03.01.17 :3
 */

public class SizePanelView extends View {

    private Paint panelPaint;

    public SizePanelView(Context context) {
        super(context);
        panelPaint = new Paint();
        panelPaint.setColor(ColorsUI.PANELS_COLOR.getColor());
        panelPaint.setAntiAlias(true);
        panelPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(PanelUtils.twoCornersRoundedRect(0, 0, dp(300), dp(35), dp(5), dp(5), 2), panelPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(300), dp(35));
    }

}
