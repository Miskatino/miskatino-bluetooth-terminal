package com.github.miskatino.btterm;

import android.graphics.*;
import android.view.*;

public class GraphicView extends View {

    private Paint paint;
    private Canvas cnv;
    private Bitmap bitmap;
    private MainActivity activity;
    private Keyboard kbd;
    private CharDisplay disp;
    
    GraphicView(MainActivity activity) {
        super(activity);
        this.activity = activity;
        paint = new Paint();
        paint.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(activity);
        kbd = new Keyboard();
        disp = new CharDisplay();
    }
    
    boolean onTouch(MotionEvent event) {
        paint.setColor(Color.RED);
        cnv.drawCircle(event.getX(), event.getY(), 7, paint);
        invalidate();
        return true;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        Rect r = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        //canvas.drawBitmap(bitmap, r, r, null);
        kbd.drawKeys(canvas, r.width(), r.height());
        disp.drawLines(canvas, r.width(), r.height() - kbd.getHeight(r.width(), r.height()));
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        if (bitmap != null) {
            bitmap.recycle();
        }
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        cnv = new Canvas();
        cnv.setBitmap(bitmap);
    }
    
}
