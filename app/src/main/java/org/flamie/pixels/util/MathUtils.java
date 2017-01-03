package org.flamie.pixels.util;

/**
 * Created by flamie on 04.01.17 :3
 */

public class MathUtils {

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

}
