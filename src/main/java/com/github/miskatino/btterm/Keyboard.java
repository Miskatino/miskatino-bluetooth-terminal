package com.github.miskatino.btterm;

import android.graphics.*;

public class Keyboard {

    private static final char KEY_BKSP = (char) 0x2190;
    private static final char KEY_SHIFT = (char) 0x2191;
    private static final char KEY_ENTER = (char) 0x21B2;
    private static final char KEY_STOP = (char) 0x21AF;
    
    private static final int ROWS = 5;
    private static final int COLS = 10;
    
    private Paint p = new Paint();
    
    private boolean shifted = false;
    
    private static char[][] letters1 = {
        {'+', '-', '*', '/', '%', '<', '=', '>', ';', KEY_BKSP},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
        {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
        {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', KEY_SHIFT},
        {'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', ' ', KEY_ENTER},
    };
    
    private static char[][] letters2 = {
        {'~', '|', '_', '\\', '%', '[', ']', '"', ':', KEY_STOP},
        {'!', '@', '#', '$', '?', '^', '&', '*', '(', ')'},
        {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'},
        {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', KEY_SHIFT},
        {'Z', 'X', 'C', 'V', 'B', 'N', 'M', '.', ' ', KEY_ENTER},
    };
    
    private float keySize(int w) {
        return w / (float) COLS;
    }
    
    public void drawKeys(Canvas c, int w, int h) {
        float keySz = keySize(w);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(keySz * 0.7f);
        p.setTextAlign(Paint.Align.CENTER);
        float vShift = (p.descent() + p.ascent()) / 2;
        for (int row = 0; row < ROWS; row += 1) {
            for (int col = 0; col < COLS; col += 1) {
                float x = keySz * (col + 0.5f);
                float y = h - keySz * (4.5f - row) - 1;
                p.setColor(Color.BLUE);
                c.drawCircle(x, y, keySz * 0.45f, p);
                p.setColor(Color.YELLOW);
                c.drawText(Character.toString(getLetter(row, col)), x, y - vShift, p);
            }
        }
    }
    
    public int getHeight(int w, int h) {
        return (int)(keySize(w) * ROWS + 0.5);
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
                shifted = !shifted;
                return 1;
            case KEY_ENTER:
                return '\r';
            case KEY_STOP:
                shifted = false;
                return 3;
            default:
                shifted = false;
                return c;
        }
    }
    
    private char getLetter(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return 0;
        }
        char[][] ltrs = shifted ? letters2 : letters1;
        return ltrs[row][col];
    }
}

