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
public class PencilButton extends View {

    private Drawable pencilDrawable;

    public PencilButton(Context context) {
        super(context);

        pencilDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_pencil_grey600_48dp);
        pencilDrawable.setBounds(0, 0, pencilDrawable.getIntrinsicWidth(), pencilDrawable.getIntrinsicHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        pencilDrawable.draw(canvas);
    }

    @Override
    public void onMeasure(int measureWidthSpec, int measureHeightSpec) {
        if(pencilDrawable != null) {
            setMeasuredDimension(pencilDrawable.getIntrinsicWidth(), pencilDrawable.getIntrinsicHeight());
        }
    }
}
