package com.github.miskatino.btterm;

import android.graphics.*;
import android.view.*;

public class GraphicView extends View {

    private Paint paint;
    private Canvas cnv;
    private Bitmap bitmap;
    private MainActivity activity;
    
    GraphicView(MainActivity activity) {
        super(activity);
        this.activity = activity;
        paint = new Paint();
        paint.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(activity);
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
        canvas.drawBitmap(bitmap, r, r, null);
        drawKeys(canvas, r.width(), r.height());
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(32);
        canvas.drawText("Wiper", 30, 30, paint);
    }
    
    void drawKeys(Canvas c, int w, int h) {
        char[][] letters = {
            {'+', '-', '*', '/', '<', '=', '>', ';', '%', ','},
            {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
            {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'},
            {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', (char) 0x21B2},
            {(char) 0x21E7, 'Z', 'X', 'C', 'V', 'B', 'N', 'M', (char) 0x2026, ' '},
        };
        Paint p = new Paint();
        float keySz = w / 10;
        p.setColor(Color.YELLOW);
        p.setStyle(Paint.Style.STROKE);
        p.setTextSize(keySz * 0.7f);
        p.setTextAlign(Paint.Align.CENTER);
        float vShift = (p.descent() + p.ascent()) / 2;
        for (int row = 0; row < 5; row += 1) {
            for (int col = 0; col < 10; col += 1) {
                float x = keySz * (col + 0.5f);
                float y = h - keySz * (4.5f - row) - 1;
                c.drawCircle(x, y, keySz * 0.45f, p);
                c.drawText(Character.toString(letters[row][col]), x, y - vShift, p);
            }
        }
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
