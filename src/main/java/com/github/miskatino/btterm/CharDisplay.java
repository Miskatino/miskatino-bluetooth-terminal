package com.github.miskatino.btterm;

import java.util.*;
import android.graphics.*;

public class CharDisplay {

    private float dispWidth = 32;
    
    private Paint p = new Paint();
    private Rect fontBounds = new Rect();
    private char lineEndChar = 13;
    
    private List<StringBuilder> text;
    
    public CharDisplay(int charsPerLine) {
        text = new ArrayList<StringBuilder>();
        text.add(new StringBuilder());
        dispWidth = charsPerLine;
    }
    
    private float calcFontSizeForWidth(float charWidth) {
        p.setTextSize(48f);
        p.getTextBounds("A", 0, 1, fontBounds);
        return charWidth * 48f / fontBounds.width();
    }
    
    private float calcFontHeight() {
        p.getTextBounds("Ap", 0, 2, fontBounds);
        return fontBounds.height();
    }
    
    public void drawLines(Canvas c, int w, int h) {
        p.setColor(Color.GREEN);
        p.setTypeface(Typeface.MONOSPACE);
        p.setStyle(Paint.Style.FILL);
        float letterW = w / dispWidth;
        p.setTextSize(calcFontSizeForWidth(letterW));
        float fontHeight = calcFontHeight();
        float y = h - fontHeight / 2;
        reduceLines((int) Math.ceil(y / fontHeight));
        int i = text.size() - 1;
        float cursorX = text.get(i).length() * letterW;
        c.drawLine(cursorX, y - 1, cursorX + letterW, y - 1, p);
        while (i >= 0 && y > 0) {
            c.drawText(text.get(i).toString(), 0, y, p);
            i -= 1;
            y -= fontHeight * 1.2;
        }
    }
    
    private void reduceLines(int lines) {
        if (lines < text.size()) {
            text.subList(0, text.size() - lines).clear();
        }
    }
    
    public void addChar(char c) {
        StringBuilder lastLine = text.get(text.size() - 1);
        if (c >= ' ') {
            lastLine.append(c);
        } else if (c == lineEndChar) {
            text.add(new StringBuilder());
        } else if (c == '\b') {
            if (lastLine.length() > 0) {
                lastLine.setLength(lastLine.length() - 1);
            }
        }
    }
    
    public void addStr(String s) {
        for (int i = 0; i < s.length(); i++) {
            addChar(s.charAt(i));
        }
    }

}

