package org.flamie.pixels.ui.buttons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import org.flamie.pixels.R;

/**
 * Created by flamie on 17/12/16 :3
 */
public class EraserButton extends View {

    private Drawable eraserDrawable;

    public EraserButton(Context context) {
        super(context);

        eraserDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_eraser_grey600_48dp);
        eraserDrawable.setBounds(0, 0, eraserDrawable.getIntrinsicWidth(), eraserDrawable.getIntrinsicHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        eraserDrawable.draw(canvas);
    }

    @Override
    public void onMeasure(int measureWidthSpec, int measureHeightSpec) {
        if(eraserDrawable != null) {
            setMeasuredDimension(eraserDrawable.getIntrinsicWidth(), eraserDrawable.getIntrinsicHeight());
        }
    }
}
