package org.flamie.pixels.ui.panels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import org.flamie.pixels.enums.ColorsUI;
import org.flamie.pixels.util.PanelUtils;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 16/12/16 :3
 */

public class ToolsPanel extends View {

    private Paint toolsPanelPaint;

    public ToolsPanel(Context context) {
        super(context);
        toolsPanelPaint = new Paint();
        toolsPanelPaint.setColor(ColorsUI.PANELS_COLOR.getColor());
        toolsPanelPaint.setStyle(Paint.Style.FILL);
        toolsPanelPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(PanelUtils.twoCornersRoundedRect(0, (canvas.getHeight() / 2) - dp(150), dp(50), (canvas.getHeight() / 2) + dp(150), dp(4), dp(4), 3), toolsPanelPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
