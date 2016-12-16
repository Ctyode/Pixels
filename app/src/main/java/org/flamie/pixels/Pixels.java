package org.flamie.pixels;

import android.app.Application;
import android.content.Context;

/**
 * Created by flamie on 16/12/16 :3
 */

public class Pixels extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
