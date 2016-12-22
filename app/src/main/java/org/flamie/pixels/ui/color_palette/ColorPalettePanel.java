package org.flamie.pixels.ui.color_palette;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import org.flamie.pixels.ui.CanvasView;

import java.util.ArrayList;
import java.util.Random;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 17/12/16 :3
 */
public class ColorPalettePanel extends ViewGroup {

    private ArrayList<ColorButton> colorButtons = new ArrayList<>(11);
    private ArrayList<Integer> colors = new ArrayList<>(11);

    private ColorPalettePanelView colorPalettePanelView;

    public ColorPalettePanel(Context context) {
        super(context);
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            colors.add(Color.rgb(random.nextInt(254) + 1, random.nextInt(254) + 1, random.nextInt(254) + 1));
        }

        colorPalettePanelView = new ColorPalettePanelView(context);
        addView(colorPalettePanelView);

        for (int i = 0; i < 11; i++) {
            ColorButton colorButton = new ColorButton(context, colors.get(i));
            colorButtons.add(colorButton);
        }

        for(int i = 0; i < colorButtons.size(); i++) {
            colorButtons.get(i).setColor(colors.get(i));
        }

        for (final ColorButton colorButton : colorButtons) {
            colorButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    CanvasView.color = ((ColorButton) v).getColor();
                }
            });
            addView(colorButton);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        colorPalettePanelView.layout(0, 0, dp(440), dp(35));
        int left = dp(-30);
        int right = 0;
        for (ColorButton colorButton : colorButtons) {
            left += dp(35);
            right += left + dp(30);
            colorButton.layout(left, dp(5), right, dp(30));
        }
    }

    public ArrayList<ColorButton> getColorButtons() {
        return colorButtons;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(440), dp(35));
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }
}
