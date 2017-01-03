package org.flamie.pixels.enums;

import android.graphics.Color;

/**
 * Created by flamie on 16/12/16 :3
 */
public enum ColorsUI {
    
    PANELS_COLOR(Color.rgb(245, 245, 245)),
    SEEK_BAR_COLOR(Color.rgb(69, 90, 100));

    private int color;

    ColorsUI(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

}
