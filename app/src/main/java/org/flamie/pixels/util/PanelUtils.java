package org.flamie.pixels.util;

import android.graphics.Path;

/**
 * Created by flamie on 16/12/16 :3
 */
public class PanelUtils {

    /**
     * @param typeOfCorners 1 - bottom corners, 2 - top, 3 - left, 4 - right
     */

    public static Path twoCornersRoundedRect(float left, float top, float right, float bottom, float rx, float ry, int typeOfCorners) {
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        switch (typeOfCorners) {
            case 1:
                path.moveTo(left, bottom + -ry);
                path.rQuadTo(0, ry, rx, ry);
                path.rLineTo(widthMinusCorners, 0);
                path.rQuadTo(rx, 0, rx, -ry);
                path.rLineTo(0, -heightMinusCorners);
                path.rLineTo(0, -ry);
                path.rLineTo(-width, 0);
                path.rLineTo(0, ry);
                path.rLineTo(0, heightMinusCorners);
                break;
            case 2:
                path.moveTo(right, top + ry);
                path.rQuadTo(0, -ry, -rx, -ry);
                path.rLineTo(-widthMinusCorners, 0);
                path.rQuadTo(-rx, 0, -rx, ry);
                path.rLineTo(0, heightMinusCorners);
                path.rLineTo(0, ry);
                path.rLineTo(width, 0);
                path.rLineTo(0, -ry);
                path.rLineTo(0, -heightMinusCorners);
                break;
            case 3:
                path.moveTo(right, top + ry);
                path.rQuadTo(0, -ry, -rx, -ry);
                path.rLineTo(-widthMinusCorners, 0);
                path.rLineTo(-rx, 0);
                path.rLineTo(0, ry);
                path.rLineTo(0, heightMinusCorners);
                path.rLineTo(0, ry);
                path.rLineTo(rx,0);
                path.rLineTo(widthMinusCorners, 0);
                path.rQuadTo(rx, 0, rx, -ry);
                path.rLineTo(0, -heightMinusCorners);
                break;
            case 4:
                path.moveTo(left, bottom + -ry);
                path.rQuadTo(0, ry, rx, ry);
                path.rLineTo(widthMinusCorners, 0);
                path.rLineTo(rx, 0);
                path.rLineTo(0, -ry);
                path.rLineTo(0, -heightMinusCorners);
                path.rLineTo(0, -ry);
                path.rLineTo(-rx, 0);
                path.rLineTo(-widthMinusCorners, 0);
                path.rQuadTo(-rx, 0, -rx, ry);
                path.rLineTo(0, heightMinusCorners);
                break;
        }

        path.close();

        return path;
    }

    /**
     * @param typeOfCorners 1 - top right, 2 - bottom right, 3 - top left, 4 - bottom left
     */

    public static Path oneCornerRoundedRect(float left, float top, float right, float bottom, float rx, float ry, int typeOfCorners) {
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        switch (typeOfCorners) {
            case 1:
                path.moveTo(right, top + ry);
                path.rQuadTo(0, -ry, -rx, -ry);
                path.rLineTo(-widthMinusCorners, 0);
                path.rLineTo(-rx, 0);
                path.rLineTo(0,ry);
                path.rLineTo(0, heightMinusCorners);
                path.rLineTo(0, ry);
                path.rLineTo(rx,0);
                path.rLineTo(widthMinusCorners, 0);
                path.rLineTo(rx,0);
                path.rLineTo(0, -ry);
                break;
            case 2:
                path.moveTo(right, top + ry);
                path.rLineTo(0, -ry);
                path.rLineTo(-rx,0);
                path.rLineTo(-widthMinusCorners, 0);
                path.rLineTo(-rx, 0);
                path.rLineTo(0,ry);
                path.rLineTo(0, heightMinusCorners);
                path.rLineTo(0, ry);
                path.rLineTo(rx,0);
                path.rLineTo(widthMinusCorners, 0);
                path.rQuadTo(rx, 0, rx, -ry);
                break;
            case 3:
                path.moveTo(right, top + ry);
                path.rLineTo(0, -ry);
                path.rLineTo(-rx,0);
                path.rLineTo(-widthMinusCorners, 0);
                path.rQuadTo(-rx, 0, -rx, ry);
                path.rLineTo(0, heightMinusCorners);
                path.rLineTo(0, ry);
                path.rLineTo(rx,0);
                path.rLineTo(widthMinusCorners, 0);
                path.rLineTo(rx,0);
                path.rLineTo(0, -ry);
                break;
            case 4:
                path.moveTo(right, top + ry);
                path.rLineTo(0, -ry);
                path.rLineTo(-rx,0);
                path.rLineTo(-widthMinusCorners, 0);
                path.rLineTo(-rx, 0);
                path.rLineTo(0,ry);
                path.rLineTo(0, heightMinusCorners);
                path.rQuadTo(0, ry, rx, ry);
                path.rLineTo(widthMinusCorners, 0);
                path.rLineTo(rx,0);
                path.rLineTo(0, -ry);
                break;
        }

        path.close();

        return path;
    }


}
