package org.flamie.pixels.ui.color_palette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import org.flamie.pixels.enums.ColorsUI;
import org.flamie.pixels.util.PanelUtils;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 18/12/16 :3
 */
public class ColorPalettePanelView extends View {

    private Paint colorPalettePanelPaint;

    public ColorPalettePanelView(Context context) {
        super(context);
        colorPalettePanelPaint = new Paint();
        colorPalettePanelPaint.setColor(ColorsUI.PANELS_COLOR.getColor());
        colorPalettePanelPaint.setStyle(Paint.Style.FILL);
        colorPalettePanelPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(PanelUtils.twoCornersRoundedRect(0, 0, dp(440), dp(35), dp(4), dp(4), 1), colorPalettePanelPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(440), dp(35));
    }

}
