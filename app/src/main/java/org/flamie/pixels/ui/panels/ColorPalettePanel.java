package org.flamie.pixels.ui.panels;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import org.flamie.pixels.enums.ColorsUI;
import org.flamie.pixels.ui.CanvasView;
import org.flamie.pixels.ui.buttons.ColorButton;

import java.util.ArrayList;
import java.util.Random;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 17/12/16 :3
 */
public class ColorPalettePanel extends ViewGroup {

    private ArrayList<ColorButton> colorButtons = new ArrayList<>(11);

    private ColorPalettePanelView colorPalettePanelView;

    public ColorPalettePanel(Context context) {
        super(context);
        Random random = new Random();

        Paint colorPalettePanelPaint = new Paint();
        colorPalettePanelPaint.setColor(ColorsUI.PANELS_COLOR.getColor());
        colorPalettePanelPaint.setStyle(Paint.Style.FILL);
        colorPalettePanelPaint.setAntiAlias(true);

        colorPalettePanelView = new ColorPalettePanelView(context);
        addView(colorPalettePanelView);

        for (int i = 0; i < 11; i++) {
            ColorButton colorButton = new ColorButton(context, Color.rgb(random.nextInt(254) + 1, random.nextInt(254) + 1, random.nextInt(254) + 1));
            colorButtons.add(colorButton);
        }

        for (final ColorButton colorButton : colorButtons) {
            colorButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    CanvasView.color = colorButton.getColor();
                }
            });
            addView(colorButton);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        colorPalettePanelView.layout(0, 0, dp(500), dp(50));
        int left = dp(-30);
        int right = 0;
        for (ColorButton colorButton : colorButtons) {
            left += dp(35);
            right += left + dp(30);
            colorButton.layout(left, dp(5), right, dp(30));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(390), dp(50));
    }

}
