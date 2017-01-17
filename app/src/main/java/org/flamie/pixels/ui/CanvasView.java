package org.flamie.pixels.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import org.flamie.pixels.ui.size_panel.CustomSeekBar;

import static org.flamie.pixels.util.Dimen.dp;
import static org.flamie.pixels.util.MathUtils.clamp;

/**
 * Created by flamie on 17/12/16 :3
 */
public class CanvasView extends View {

    public static boolean eraserMode = false;
    public static boolean pencilMode = false;
    private static Rect invalidateArea = new Rect();
    private PorterDuffXfermode eraser;

    private float x, y;
    private static final float TOUCH_TOLERANCE = 4;

    private Bitmap bitmap;
    private Canvas canvas;

    private Paint brushPaint;
    private Paint bitmapPaint;

    private Path path;

    public static int color;
    private int lastStrokePosition = -1;
    private int paintWidth = -1;
    public CanvasView(Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);

        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        path = new Path();

        brushPaint = new Paint();
        brushPaint.setAntiAlias(true);
        brushPaint.setDither(true);
        brushPaint.setColor(color);
        brushPaint.setStyle(Paint.Style.STROKE);
        brushPaint.setStrokeJoin(Paint.Join.BEVEL);
        brushPaint.setStrokeCap(Paint.Cap.ROUND);
        brushPaint.setStrokeWidth(12);

        eraser = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // TODO: optimize
        brushPaint.setColor(color);
        //reduced count of calculations
        // also, getPosition() replaced with public field access which is up to 10x faster
        if(paintWidth < 0 || lastStrokePosition != CustomSeekBar.strokePosition)
        {
            lastStrokePosition = CustomSeekBar.strokePosition;
            paintWidth = (int) clamp(dp(lastStrokePosition / 10), dp(0.5f), dp(100));
            brushPaint.setStrokeWidth(paintWidth);
        }

        if(eraserMode && !pencilMode) {
            brushPaint.setXfermode(eraser);
        } else if(pencilMode && !eraserMode) {
            brushPaint.setXfermode(null);
        }

        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        canvas.drawPath(path, brushPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onStart(x, y);
                //invalidate();                  //doesn't perform anything
                break;
            case MotionEvent.ACTION_MOVE:
                onMove(x, y);
                if(invalidateArea.left > invalidateArea.right)
                {
                    int tmp = invalidateArea.left;
                    invalidateArea.left = invalidateArea.right;
                    invalidateArea.right = tmp;
                }
                if(invalidateArea.top > invalidateArea.bottom)
                {
                    int tmp = invalidateArea.top;
                    invalidateArea.top = invalidateArea.bottom;
                    invalidateArea.bottom = tmp;
                }
                invalidate(invalidateArea);                                 //Actually, we need to invalidate only a small piece of the view
                invalidateArea.left = (int) x;
                invalidateArea.top = (int) y;
                //invalidate();
                break;
            case MotionEvent.ACTION_UP:
                onUp();
                if(invalidateArea.left > invalidateArea.right)
                {
                    int tmp = invalidateArea.left;
                    invalidateArea.left = invalidateArea.right;
                    invalidateArea.right = tmp;
                }
                if(invalidateArea.top > invalidateArea.bottom)
                {
                    int tmp = invalidateArea.top;
                    invalidateArea.top = invalidateArea.bottom;
                    invalidateArea.bottom = tmp;
                }
                invalidate(invalidateArea);                                 //see comment above
                break;
        }
        return true;
    }

    private void onStart(float x, float y) {
        //path.reset();                         //anyway it resets in onUp()
        path.moveTo(x, y);
        this.x = x;
        this.y = y;
        invalidateArea.left = (int) x;
        invalidateArea.top = (int) y;
    }

    private void onMove(float x, float y) {
        float dx = Math.abs(x - this.x);
        float dy = Math.abs(y - this.y);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(this.x, this.y, (x + this.x) / 2, (y + this.y) / 2);
            invalidateArea.right = (int) ((x + this.x) / 2);
            invalidateArea.bottom = (int) ((y + this.y) / 2);
            this.x = x;
            this.y = y;
        }
    }

    private void onUp() {
        path.lineTo(x, y);
        invalidateArea.right = (int) (x + this.x);
        invalidateArea.bottom = (int) (y + this.y);
        canvas.drawPath(path, brushPaint);
        path.reset();
    }

}
