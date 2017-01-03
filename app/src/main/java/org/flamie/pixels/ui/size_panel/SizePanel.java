package org.flamie.pixels.ui.size_panel;

import android.content.Context;
import android.view.ViewGroup;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 03.01.17 :3
 */

public class SizePanel extends ViewGroup {

    private final SizePanelView sizePanelView;
    private final CustomSeekBar customSeekBar;

    public SizePanel(Context context) {
        super(context);
        sizePanelView = new SizePanelView(context);
        addView(sizePanelView);

        customSeekBar = new CustomSeekBar(context);
        addView(customSeekBar);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        sizePanelView.layout(0, 0, dp(300), dp(35));
        customSeekBar.layout(dp(25), dp(9), customSeekBar.getSeekBarWidth() + dp(25), customSeekBar.getStrokeHeight() + dp(9));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(300), dp(35));
    }
}
