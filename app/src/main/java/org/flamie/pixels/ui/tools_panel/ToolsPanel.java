package org.flamie.pixels.ui.tools_panel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import org.flamie.pixels.ui.CanvasView;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 16/12/16 :3
 */

public class ToolsPanel extends ViewGroup {

    private final EraserButton eraserButton;
    private final PencilButton pencilButton;
    private final ToolsPanelView toolsPanelView;

    public ToolsPanel(Context context) {
        super(context);
        toolsPanelView = new ToolsPanelView(context);
        addView(toolsPanelView);

        eraserButton = new EraserButton(context);
        eraserButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasView.eraserMode = true;
                CanvasView.pencilMode = false;
            }
        });

        addView(eraserButton);

        pencilButton = new PencilButton(context);
        pencilButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasView.pencilMode = true;
                CanvasView.eraserMode = false;
            }
        });
        addView(pencilButton);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        toolsPanelView.layout(0, 0, dp(50), dp(300));
        eraserButton.layout(0, 0, dp(48), dp(48));
        pencilButton.layout(0, dp(48), dp(48), dp(96));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(50), dp(300));
    }
}
