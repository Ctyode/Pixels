package org.flamie.pixels.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.flamie.pixels.ui.color_palette.ColorPalettePanel;
import org.flamie.pixels.ui.color_wheel.ColorWheelPanel;
import org.flamie.pixels.ui.size_panel.SizePanel;
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
        final SizePanel sizePanel = new SizePanel(context);

        colorWheelPanel.setVisibility(INVISIBLE);

        LayoutParams canvasViewLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LayoutParams toolsPanelLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams colorPalettePanelLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams colorWheelPanelLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams sizePanelLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        canvasViewLayoutParams.addRule(CENTER_IN_PARENT);
        toolsPanelLayoutParams.addRule(CENTER_VERTICAL);
        colorPalettePanelLayoutParams.addRule(CENTER_HORIZONTAL);
        colorWheelPanelLayoutParams.addRule(CENTER_IN_PARENT);
        sizePanelLayoutParams.addRule(CENTER_HORIZONTAL);
        sizePanelLayoutParams.addRule(ALIGN_PARENT_BOTTOM);

        addView(canvasView, canvasViewLayoutParams);
        addView(toolsPanel, toolsPanelLayoutParams);
        addView(colorPalettePanel, colorPalettePanelLayoutParams);
        addView(colorWheelPanel, colorWheelPanelLayoutParams);
        addView(sizePanel, sizePanelLayoutParams);
    }

    public ColorWheelPanel getColorWheelPanel() {
        return colorWheelPanel;
    }
}
