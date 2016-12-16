package org.flamie.pixels.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

import org.flamie.pixels.ui.panels.ToolsPanel;

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

        LayoutParams toolsPanelLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        toolsPanel.setLayoutParams(toolsPanelLayoutParams);

        addView(toolsPanel);
    }
}
