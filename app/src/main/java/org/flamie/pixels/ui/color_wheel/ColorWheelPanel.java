package org.flamie.pixels.ui.color_wheel;

import android.content.Context;
import android.view.ViewGroup;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 20/12/16 :3
 */
public class ColorWheelPanel extends ViewGroup {

    private ColorWheelPanelView colorWheelPanelView;

    public ColorWheelPanel(Context context) {
        super(context);

        colorWheelPanelView = new ColorWheelPanelView(context);
        addView(colorWheelPanelView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        colorWheelPanelView.layout(0, 0, dp(200), dp(250));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(200), dp(250));
    }

}
