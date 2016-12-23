package org.flamie.pixels.ui.color_wheel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.flamie.pixels.util.PanelUtils;

import static org.flamie.pixels.util.Dimen.dp;

/**
 * Created by flamie on 20/12/16 :3
 */
public class ColorWheel extends ViewGroup {

    /** Color wheel customize */
    private final int colorWheelRadius = dp(80);
    private final int colorWheelMarginTop = dp(-30);

    /** Slider customize */
    private final float valueHeight = dp(25);
    private final float valueWidth = dp(200);
    private final float valuePadding = dp(10);
    private final int valueMarginTop = dp(90);

    private ColorWheelView colorWheel;
    private ValueSlider valueSlider;

    private float[] colorHSV = new float[] { 0f, 0f, 1f };
    private float lineX;

    public ColorWheel(Context context) {
        super(context);
        colorWheel = new ColorWheelView(context);
        valueSlider = new ValueSlider(context);

        addView(colorWheel);
        addView(valueSlider);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        colorWheel.layout(getWidth() / 2 - colorWheelRadius,
                          colorWheelMarginTop + getHeight() / 2 - colorWheelRadius,
                          getWidth() / 2 + colorWheelRadius,
                          colorWheelMarginTop + getHeight() / 2 + colorWheelRadius);
        valueSlider.layout(getWidth() / 2 - (int) valueWidth / 2,
                valueMarginTop + getHeight() / 2 - (int) valueHeight / 2,
                getWidth() / 2 + (int) valueWidth / 2,
                valueMarginTop + getHeight() / 2 + (int) valueHeight / 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(widthSize, heightSize);
        setMeasuredDimension(size, size);
    }

    public void setColor(int color) {
        Color.colorToHSV(color, colorHSV);
    }

    public int getColor() {
        return Color.HSVToColor(colorHSV);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle state = new Bundle();
        state.putFloatArray("color", colorHSV);
        state.putParcelable("super", super.onSaveInstanceState());
        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            colorHSV = bundle.getFloatArray("color");
            super.onRestoreInstanceState(bundle.getParcelable("super"));
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    public class ValueSlider extends View {

        private Paint valuePointerPaint;
        private Paint valueSliderPaint;

        private Path valueSliderPath;

        public ValueSlider(Context context) {
            super(context);
            valuePointerPaint = new Paint();
            valuePointerPaint.setStyle(Paint.Style.STROKE);
            valuePointerPaint.setStrokeWidth(4f);

            valueSliderPaint = new Paint();
            valueSliderPaint.setAntiAlias(true);
            valueSliderPaint.setDither(true);

            valueSliderPath = new Path();
        }

        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {
            float[] hsv = new float[] { colorHSV[0], colorHSV[1], 1f };

            LinearGradient linearGradient = new LinearGradient(0, valueHeight, valueWidth, valueHeight, new int[] { Color.BLACK, Color.HSVToColor(hsv), Color.WHITE }, new float[] { 0, valueWidth / 2, valueWidth }, TileMode.CLAMP);
            valueSliderPaint.setShader(linearGradient);

            canvas.drawPath(valueSliderPath, valueSliderPaint);

            valuePointerPaint.setColor(Color.HSVToColor(new float[]{0f, 0f, 1f - colorHSV[2]}));
            canvas.drawLine(clamp(lineX, 0, valueWidth), 0, clamp(lineX, 0, valueWidth), valueHeight, valuePointerPaint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:

                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    if(x >= -valuePadding && y <= valueHeight && x <= valueWidth + valuePadding && y >= 0) {
                        lineX = event.getX();
                        colorHSV[2] = lineX / valueWidth;
                    }

                    invalidate();

                    return true;
            }
            return super.onTouchEvent(event);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            valueSliderPath.addPath(PanelUtils.roundedRectangle(0, 0, valueWidth, valueHeight, 10, 10));
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension((int) valueWidth, (int) valueHeight);
        }

        public float clamp(float val, float min, float max) {
            return Math.max(min, Math.min(max, val));
        }
    }

    public class ColorWheelView extends View {

        private Paint colorWheelPaint;

        private Paint colorPointerPaint;
        private RectF colorPointerCoords;

        private RectF outerWheelRect;
        private RectF innerWheelRect;

        private Bitmap colorWheelBitmap;

        private int outerWheelRadius;
        private int innerWheelRadius;

        public ColorWheelView(Context context) {
            super(context);

            colorPointerPaint = new Paint();
            colorPointerPaint.setStyle(Paint.Style.STROKE);
            colorPointerPaint.setStrokeWidth(2f);
            colorPointerPaint.setARGB(128, 0, 0, 0);

            colorWheelPaint = new Paint();
            colorWheelPaint.setAntiAlias(true);
            colorWheelPaint.setDither(true);

            outerWheelRect = new RectF();
            innerWheelRect = new RectF();

            colorPointerCoords = new RectF();
        }

        private Bitmap createColorWheelBitmap(int width, int height) {

            Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);

            int colorCount = 12;
            int colorAngleStep = 360 / 12;
            int colors[] = new int[colorCount + 1];
            float hsv[] = new float[] { 0f, 1f, 1f };
            for (int i = 0; i < colors.length; i++) {
                hsv[0] = (i * colorAngleStep + 180) % 360;
                colors[i] = Color.HSVToColor(hsv);
            }
            colors[colorCount] = colors[0];

            SweepGradient sweepGradient = new SweepGradient(width / 2, height / 2, colors, null);
            RadialGradient radialGradient = new RadialGradient(width / 2, height / 2, colorWheelRadius, 0xFFFFFFFF, 0x00FFFFFF, TileMode.CLAMP);
            ComposeShader composeShader = new ComposeShader(sweepGradient, radialGradient, PorterDuff.Mode.SRC_OVER);

            colorWheelPaint.setShader(composeShader);

            Canvas canvas = new Canvas(bitmap);
            canvas.drawCircle(width / 2, height / 2, colorWheelRadius, colorWheelPaint);

            return bitmap;
        }

        @Override
        protected void onSizeChanged(int width, int height, int oldw, int oldh) {
            int centerX = width / 2;
            int centerY = height / 2;

            outerWheelRadius = width / 2;
            innerWheelRadius = outerWheelRadius;

            outerWheelRect.set(centerX - outerWheelRadius, centerY - outerWheelRadius, centerX + outerWheelRadius, centerY + outerWheelRadius);
            innerWheelRect.set(centerX - innerWheelRadius, centerY - innerWheelRadius, centerX + innerWheelRadius, centerY + innerWheelRadius);

            colorWheelBitmap = createColorWheelBitmap(colorWheelRadius * 2, colorWheelRadius * 2);

        }

        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            canvas.drawBitmap(colorWheelBitmap, centerX - colorWheelRadius, centerY - colorWheelRadius, null);

            float hueAngle = (float) Math.toRadians(colorHSV[0]);
            int colorPointX = (int) (-Math.cos(hueAngle) * colorHSV[1] * colorWheelRadius) + centerX;
            int colorPointY = (int) (-Math.sin(hueAngle) * colorHSV[1] * colorWheelRadius) + centerY;

            float pointerRadius = 0.075f * colorWheelRadius;
            int pointerX = (int) (colorPointX - pointerRadius / 2);
            int pointerY = (int) (colorPointY - pointerRadius / 2);

            colorPointerCoords.set(pointerX, pointerY, pointerX + pointerRadius, pointerY + pointerRadius);
            canvas.drawOval(colorPointerCoords, colorPointerPaint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:

                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    int cx = x - getWidth() / 2;
                    int cy = y - getHeight() / 2;
                    double d = Math.sqrt(cx * cx + cy * cy);

                    if (d <= colorWheelRadius) {

                        colorHSV[0] = (float) (Math.toDegrees(Math.atan2(cy, cx)) + 180f);
                        colorHSV[1] = Math.max(0f, Math.min(1f, (float) (d / colorWheelRadius)));

                        valueSlider.invalidate();
                        invalidate();
                    }

                    return true;
            }
            return super.onTouchEvent(event);
        }

    }

}