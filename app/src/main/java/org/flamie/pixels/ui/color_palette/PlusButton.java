package org.flamie.pixels.ui.color_palette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import org.flamie.pixels.R;

/**
 * Created by flamie on 20/12/16 :3
 */
public class PlusButton extends View {

    private Drawable plusDrawable;

    public PlusButton(Context context) {
        super(context);

        plusDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_plus_grey600_36dp);
        plusDrawable.setBounds(0, 0, plusDrawable.getIntrinsicWidth(), plusDrawable.getIntrinsicHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        plusDrawable.draw(canvas);
    }

    @Override
    public void onMeasure(int measureWidthSpec, int measureHeightSpec) {
        if(plusDrawable != null) {
            setMeasuredDimension(plusDrawable.getIntrinsicWidth(), plusDrawable.getIntrinsicHeight());
        }
    }

}
