package com.github.miskatino.btterm;

import android.graphics.*;

public class Keyboard {

    private Paint p = new Paint();
    
    private static char[][] letters = {
        {'+', '-', '*', '/', '<', '=', '>', ';', '%', ','},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
        {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'},
        {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', (char) 0x21B2},
        {(char) 0x21E7, 'Z', 'X', 'C', 'V', 'B', 'N', 'M', (char) 0x2026, ' '},
    };

    public void drawKeys(Canvas c, int w, int h) {
        float keySz = w / 10;
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(keySz * 0.7f);
        p.setTextAlign(Paint.Align.CENTER);
        float vShift = (p.descent() + p.ascent()) / 2;
        for (int row = 0; row < 5; row += 1) {
            for (int col = 0; col < 10; col += 1) {
                float x = keySz * (col + 0.5f);
                float y = h - keySz * (4.5f - row) - 1;
                p.setColor(Color.BLUE);
                c.drawCircle(x, y, keySz * 0.45f, p);
                p.setColor(Color.YELLOW);
                c.drawText(Character.toString(letters[row][col]), x, y - vShift, p);
            }
        }
    }
    
    public int getHeight(int w, int h) {
        return (int)((w / 10) * 5 + 0.5);
    }
    
}
