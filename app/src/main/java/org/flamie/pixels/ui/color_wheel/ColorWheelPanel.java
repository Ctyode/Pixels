package org.flamie.pixels.ui.color_wheel;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.flamie.pixels.R;
import org.flamie.pixels.ui.color_palette.ColorPalettePanel;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 20/12/16 :3
 */
public class ColorWheelPanel extends ViewGroup {

    private final CloseButton closeButton;
    private final ColorWheel colorWheel;
    private final ColorWheelPanelView colorWheelPanelView;
    private final TextView addButton;

    public ColorWheelPanel(Context context, final ColorPalettePanel colorPalettePanel) {
        super(context);
        colorWheelPanelView = new ColorWheelPanelView(context);
        closeButton = new CloseButton(context);
        colorWheel = new ColorWheel(context);
        addButton = new TextView(context);

        addButton.setText(R.string.add_button);
        addButton.setTextColor(Color.GRAY); // TODO: change to custom
        addButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13f);

        closeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility(INVISIBLE);
            }
        });

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPalettePanel.getColors().add(0, colorWheel.getColor());
                colorPalettePanel.getColors().remove(colorPalettePanel.getColors().size() - 1);
                for (int i = 0; i < 11; i++) {
                    colorPalettePanel.getColorButtons().get(i).setColor(colorPalettePanel.getColors().get(i));
                }
            }
        });

        addView(colorWheelPanelView);
        addView(closeButton);
        addView(colorWheel);
        addView(addButton);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        colorWheelPanelView.layout(0, 0, dp(200), dp(270));
        closeButton.layout(dp(166), dp(10), dp(190), dp(34));
        colorWheel.layout(0, dp(20), dp(200), dp(260));
        addButton.layout(dp(165), dp(246), dp(195), dp(261));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(200), dp(270));
    }

}
