package com.github.miskatino.btterm;

import android.graphics.*;
import android.view.*;

public class GraphicView extends View {

    private Paint paint;
    private MainActivity activity;
    private Keyboard kbd;
    CharDisplay disp;
    private int width, height;
    private long lastTouch;
    
    GraphicView(MainActivity activity, int kbdRows) {
        super(activity);
        this.activity = activity;
        paint = new Paint();
        paint.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(activity);
        kbd = kbdRows == 5 ? new Keyboard5x10() : new Keyboard7x7();
        disp = new CharDisplay();
        lastTouch = 0;
    }
    
    boolean onTouch(MotionEvent event) {
        if (!touchDelayPassed()) {
            return true;
        }
        char c = kbd.pressed(event.getX(), event.getY(), width, height);
        if (c != 0) {
            if (c != 1) {
                activity.sendKey(c);
            }
            invalidate();
        }
        return true;
    }
    
    private boolean touchDelayPassed() {
        long cur = System.currentTimeMillis();
        if (cur - lastTouch < 350) {
            return false;
        }
        lastTouch = cur;
        return true;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        kbd.drawKeys(canvas, width, height);
        disp.drawLines(canvas, width, height - kbd.getHeight(width, height));
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        width = w;
        height = h;
    }
    
}
