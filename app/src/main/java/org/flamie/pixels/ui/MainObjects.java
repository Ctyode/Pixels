package org.flamie.pixels.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.flamie.pixels.R;
import org.flamie.pixels.ui.color_palette.ColorPalettePanel;
import org.flamie.pixels.ui.color_palette.PlusButton;
import org.flamie.pixels.ui.color_wheel.CloseButton;
import org.flamie.pixels.ui.color_wheel.ColorWheel;
import org.flamie.pixels.ui.color_wheel.ColorWheelPanel;
import org.flamie.pixels.ui.tools_panel.EraserButton;
import org.flamie.pixels.ui.tools_panel.PencilButton;
import org.flamie.pixels.ui.tools_panel.ToolsPanel;

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
        final CanvasView canvasView = new CanvasView(context);
        final ColorPalettePanel colorPalettePanel = new ColorPalettePanel(context);
        EraserButton eraserButton = new EraserButton(context);
        PencilButton pencilButton = new PencilButton(context);
        PlusButton plusButton = new PlusButton(context);
        final ColorWheelPanel colorWheelPanel = new ColorWheelPanel(context);
        colorWheelPanel.setVisibility(INVISIBLE);
        final CloseButton closeButton = new CloseButton(context);
        closeButton.setVisibility(INVISIBLE);
        final ColorWheel colorWheel = new ColorWheel(context);
        colorWheel.setVisibility(INVISIBLE);
        final TextView okButton = new TextView(context);
        okButton.setText(R.string.ok_button);
        okButton.setTextSize(dp(4));
        okButton.setTextColor(Color.GRAY);
        okButton.setVisibility(INVISIBLE);

        LayoutParams eraserButtonLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        eraserButtonLayoutParams.height = dp(10);
        eraserButtonLayoutParams.width = dp(10);
        eraserButtonLayoutParams.topMargin = dp(30);

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
        pencilButtonLayoutParams.topMargin = dp(80);

        pencilButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasView.pencilMode = true;
                CanvasView.eraserMode = false;
            }
        });

        LayoutParams colorPalettePanelLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        colorPalettePanelLayoutParams.addRule(CENTER_HORIZONTAL);

        LayoutParams toolsPanelLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        toolsPanelLayoutParams.addRule(CENTER_VERTICAL);

        LayoutParams canvasViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        canvasViewLayoutParams.addRule(CENTER_IN_PARENT);
        canvasViewLayoutParams.height = dp(200);
        canvasViewLayoutParams.width = dp(300);

        LayoutParams plusButtonLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        plusButtonLayoutParams.leftMargin = dp(470);

        plusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                colorWheelPanel.setVisibility(VISIBLE);
                closeButton.setVisibility(VISIBLE);
                colorWheel.setVisibility(VISIBLE);
                okButton.setVisibility(VISIBLE);
            }
        });

        closeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                colorWheelPanel.setVisibility(INVISIBLE);
                closeButton.setVisibility(INVISIBLE);
                colorWheel.setVisibility(INVISIBLE);
                okButton.setVisibility(INVISIBLE);
            }
        });

        LayoutParams colorWheelPanelLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        colorWheelPanelLayoutParams.addRule(CENTER_IN_PARENT);

        LayoutParams closeButtonLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        closeButtonLayoutParams.leftMargin = dp(365);
        closeButtonLayoutParams.topMargin = dp(70);

        LayoutParams multiColorPickerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        multiColorPickerLayoutParams.addRule(CENTER_IN_PARENT);

        LayoutParams okButtonParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        okButtonParams.addRule(CENTER_HORIZONTAL);
        okButtonParams.topMargin = dp(200);

        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPalettePanel.getColors().add(0, colorWheel.getColor());
                colorPalettePanel.getColors().remove(colorPalettePanel.getColors().size() - 1);
                for(int i = 0; i < 11; i++) {
                    colorPalettePanel.getColorButtons().get(i).setColor(colorPalettePanel.getColors().get(i));
                }
            }
        });

        addView(canvasView, canvasViewLayoutParams);
        addView(toolsPanel, toolsPanelLayoutParams);
        addView(eraserButton, eraserButtonLayoutParams);
        addView(pencilButton, pencilButtonLayoutParams);
        addView(colorPalettePanel, colorPalettePanelLayoutParams);
        addView(plusButton, plusButtonLayoutParams);
        addView(colorWheelPanel, colorWheelPanelLayoutParams);
        addView(closeButton, closeButtonLayoutParams);
        addView(colorWheel, multiColorPickerLayoutParams);
        addView(okButton);
    }

}
