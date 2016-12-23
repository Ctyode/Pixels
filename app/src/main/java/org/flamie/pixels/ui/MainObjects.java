package org.flamie.pixels.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.flamie.pixels.ui.color_palette.ColorPalettePanel;
import org.flamie.pixels.ui.color_wheel.ColorWheelPanel;
import org.flamie.pixels.ui.tools_panel.ToolsPanel;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 16/12/16 :3
 */
public class MainObjects extends RelativeLayout {

    private Activity activity;
    private ColorWheelPanel colorWheelPanel;

    public MainObjects(Context context, Activity activity) {
        super(context);
        this.activity = activity;
        init();
    }

    public void init() {
        this.setBackgroundColor(Color.rgb(62, 57, 57));
        final Context context = activity.getApplication().getApplicationContext();
        final ToolsPanel toolsPanel = new ToolsPanel(context);
        final CanvasView canvasView = new CanvasView(context);
        final ColorPalettePanel colorPalettePanel = new ColorPalettePanel(context, this);
        colorWheelPanel = new ColorWheelPanel(context, colorPalettePanel);

        colorWheelPanel.setVisibility(INVISIBLE);

        LayoutParams canvasViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams toolsPanelLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams colorPalettePanelLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams colorWheelPanelLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        canvasViewLayoutParams.addRule(CENTER_IN_PARENT);
        canvasViewLayoutParams.height = dp(200);
        canvasViewLayoutParams.width = dp(300);
        toolsPanelLayoutParams.addRule(CENTER_VERTICAL);
        colorPalettePanelLayoutParams.addRule(CENTER_HORIZONTAL);
        colorWheelPanelLayoutParams.addRule(CENTER_IN_PARENT);

        addView(canvasView, canvasViewLayoutParams);
        addView(toolsPanel, toolsPanelLayoutParams);
        addView(colorPalettePanel, colorPalettePanelLayoutParams);
        addView(colorWheelPanel, colorWheelPanelLayoutParams);
    }

    public ColorWheelPanel getColorWheelPanel() {
        return colorWheelPanel;
    }
}
