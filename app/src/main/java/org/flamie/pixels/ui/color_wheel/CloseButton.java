package org.flamie.pixels.ui.color_wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import org.flamie.pixels.R;

/**
 * Created by flamie on 20/12/16 :3
 */
public class CloseButton extends View {

    private Drawable closeDrawable;

    public CloseButton(Context context) {
        super(context);

        closeDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_close_grey600_24dp);
        closeDrawable.setBounds(0, 0, closeDrawable.getIntrinsicWidth(), closeDrawable.getIntrinsicHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        closeDrawable.draw(canvas);
    }

    @Override
    public void onMeasure(int measureWidthSpec, int measureHeightSpec) {
        if(closeDrawable != null) {
            setMeasuredDimension(closeDrawable.getIntrinsicWidth(), closeDrawable.getIntrinsicHeight());
        }
    }

}
