package com.github.miskatino.btterm;

import android.app.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity implements View.OnTouchListener {
    
    private GraphicView view;
    
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = new GraphicView(this);
        setContentView(view);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        return this.view.onTouch(event);
    }
    
}
