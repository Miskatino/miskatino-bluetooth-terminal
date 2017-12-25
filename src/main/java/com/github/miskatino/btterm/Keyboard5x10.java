package com.github.miskatino.btterm;

public class Keyboard5x10 extends Keyboard {

    private static char[][][] keys = {
        {
            {'+', '-', '*', '/', '%', '(', ')', '=', ';', KEY_BKSP},
            {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
            {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
            {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', KEY_SHIFT},
            {'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', ' ', KEY_ENTER},
        },
        {
            {'~', '|', '_', '\\', '%', '[', ']', '"', ':', KEY_STOP},
            {'!', '@', '#', '$', '?', '^', '&', '\'', '<', '>'},
            {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'},
            {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', KEY_SHIFT},
            {'Z', 'X', 'C', 'V', 'B', 'N', 'M', '.', ' ', KEY_ENTER},
        },
    };

    public Keyboard5x10() {
        rows = 5;
        cols = 10;
        letters = keys;
    }

}
