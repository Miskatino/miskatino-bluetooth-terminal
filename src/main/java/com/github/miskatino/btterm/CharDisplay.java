package com.github.miskatino.btterm;

import java.util.*;
import android.graphics.*;

public class CharDisplay {

    private static final int DISP_WIDTH = 32;
    
    private Paint p = new Paint();
    private Rect fontBounds = new Rect();
    
    private List<StringBuilder> text;
    
    public CharDisplay() {
        text = new ArrayList<StringBuilder>();
        text.add(new StringBuilder("10 print 'zloba'"));
        text.add(new StringBuilder("20 if x > 5; let m = 3.14159265358"));
        text.add(new StringBuilder("run"));
    }
    
    public void drawLines(Canvas c, int w, int h) {
        p.setTypeface(Typeface.MONOSPACE);
        p.setTextSize(48f);
        p.getTextBounds("A", 0, 1, fontBounds);
        p.setTextSize(w * 48f / DISP_WIDTH / fontBounds.width());
        p.getTextBounds("Ap", 0, 2, fontBounds);
        p.setColor(Color.GREEN);
        float y = h - fontBounds.height() / 2;
        int i = text.size() - 1;
        while (i >= 0 && y > 0) {
            c.drawText(text.get(i).toString(), 0, y, p);
            i -= 1;
            y -= fontBounds.height() * 1.2;
        }
    }

}

