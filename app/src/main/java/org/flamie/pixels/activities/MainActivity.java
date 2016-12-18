package org.flamie.pixels.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.flamie.pixels.ui.MainObjects;
import org.flamie.pixels.util.Dimen;

/**
 * Created by flamie on 16/12/16 :3
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dimen.init(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        MainObjects mainObjects = new MainObjects(getApplicationContext(), this);
        setContentView(mainObjects);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

}
