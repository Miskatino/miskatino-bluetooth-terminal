package com.github.miskatino.btterm;

public class Keyboard7x7 extends Keyboard {

    private static char[][][] keys = {
        {
            {'+', '-', '*', '/', '(', ')', KEY_BKSP},
            {'0', '1', '2', '3', '4', '=', KEY_ENTER},
            {'5', '6', '7', '8', '9', ';', ','},
            {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
            {'H', 'I', 'J', 'K', 'L', 'M', 'N'},
            {'O', 'P', 'Q', 'R', 'S', 'T', 'U'},
            {'V', 'W', 'X', 'Y', 'Z', ' ', KEY_SHIFT},
        },
        {
            {'~', '|', '_', '\\', '[', ']', KEY_STOP},
            {'"', '!', '@', '#', '$', '?', KEY_ENTER},
            {'^', '&', '<', '>', '\'', ':', '.'},
            {'a', 'b', 'c', 'd', 'e', 'f', 'g'},
            {'h', 'i', 'j', 'k', 'l', 'm', 'n'},
            {'o', 'p', 'q', 'r', 's', 't', 'u'},
            {'v', 'w', 'x', 'y', 'z', '%', KEY_SHIFT},
        },
    };
    

    public Keyboard7x7() {
        rows = 7;
        cols = 7;
        letters = keys;
    }

}
