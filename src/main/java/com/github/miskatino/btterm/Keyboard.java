package com.github.miskatino.btterm;

import android.graphics.*;

public class Keyboard {

    static final char KEY_BKSP = (char) 0x2190;
    static final char KEY_SHIFT = (char) 0x2191;
    static final char KEY_ENTER = (char) 0x21B2;
    static final char KEY_STOP = (char) 0x21AF;
    
    Paint p = new Paint();
    
    int shifted = 0;
    
    int rows, cols;
    
    char[][][] letters;
    
    private float keySize(int w) {
        return w / (float) cols;
    }
    
    public void drawKeys(Canvas c, int w, int h) {
        float keySz = keySize(w);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(keySz * 0.7f);
        p.setTextAlign(Paint.Align.CENTER);
        float vShift = (p.descent() + p.ascent()) / 2;
        for (int row = 0; row < rows; row += 1) {
            for (int col = 0; col < cols; col += 1) {
                float x = keySz * (col + 0.5f);
                float y = h - keySz * (rows - 0.5f - row) - 1;
                p.setColor(Color.BLUE);
                c.drawCircle(x, y, keySz * 0.45f, p);
                p.setColor(Color.YELLOW);
                c.drawText(Character.toString(getLetter(row, col)), x, y - vShift, p);
            }
        }
    }
    
    public int getHeight(int w, int h) {
        return (int)(keySize(w) * rows + 0.5);
    }
    
    public char pressed(float x, float y, int w, int h) {
        float sz = keySize(w);
        int row = (int) ((y - (h - getHeight(w, h))) / sz);
        int col = (int) (x / sz);
        char c = getLetter(row, col);
        switch (c) {
            case KEY_BKSP:
                return '\b';
            case KEY_SHIFT:
                shifted = 1 - shifted;
                return 1;
            case KEY_ENTER:
                return '\r';
            case KEY_STOP:
                shifted = 0;
                return 3;
            default:
                shifted = 0;
                return c;
        }
    }
    
    private char getLetter(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return 0;
        }
        return letters[shifted][row][col];
    }
}

