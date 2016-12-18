package org.flamie.pixels.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.flamie.pixels.ui.buttons.EraserButton;
import org.flamie.pixels.ui.buttons.PencilButton;
import org.flamie.pixels.ui.panels.ColorPalettePanel;
import org.flamie.pixels.ui.panels.ToolsPanel;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 16/12/16 :3
 */
public class MainObjects extends RelativeLayout {

    private Activity activity;

    public MainObjects(Context context, Activity activity) {
        super(context);
        this.activity = activity;
        init();
    }

    public void init() {
        this.setBackgroundColor(Color.rgb(62, 57, 57));
        final Context context = activity.getApplication().getApplicationContext();
        ToolsPanel toolsPanel = new ToolsPanel(context);
        CanvasView canvasView = new CanvasView(context);
        EraserButton eraserButton = new EraserButton(context);
        PencilButton pencilButton = new PencilButton(context);
        ColorPalettePanel colorPalettePanel = new ColorPalettePanel(context);

        LayoutParams colorPalettePanelLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        colorPalettePanelLayoutParams.leftMargin = dp(100);

        LayoutParams toolsPanelLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        LayoutParams canvasViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        canvasViewLayoutParams.addRule(CENTER_IN_PARENT);
        canvasViewLayoutParams.height = dp(200);
        canvasViewLayoutParams.width = dp(300);

        LayoutParams eraserButtonLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        eraserButtonLayoutParams.height = dp(10);
        eraserButtonLayoutParams.width = dp(10);
        eraserButtonLayoutParams.topMargin = dp(190);
        eraserButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasView.eraserMode = true;
                CanvasView.pencilMode = false;
            }
        });

        LayoutParams pencilButtonLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pencilButtonLayoutParams.height = dp(10);
        pencilButtonLayoutParams.width = dp(10);
        pencilButtonLayoutParams.topMargin = dp(140);
        pencilButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasView.eraserMode = false;
                CanvasView.pencilMode = true;
            }
        });

        addView(canvasView, canvasViewLayoutParams);
        addView(toolsPanel, toolsPanelLayoutParams);
        addView(eraserButton, eraserButtonLayoutParams);
        addView(pencilButton, pencilButtonLayoutParams);
        addView(colorPalettePanel, colorPalettePanelLayoutParams);
    }

}
