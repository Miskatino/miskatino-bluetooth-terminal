package com.github.miskatino.btterm;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.bluetooth.*;
import java.io.*;
import java.util.*;

public class MainActivity extends Activity implements View.OnTouchListener {
    
    private GraphicView view;
    
    private BluetoothSocket bts;
    
    private Handler handler;
    
    private Runnable receiver;
    
    private static final int DELAY = 50;
    
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = new GraphicView(this, getIntent().getExtras().getInt("kbdrows"));
        setContentView(view);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle b = getIntent().getExtras();
        connectDevice(b.getCharSequence("mac").toString());
        receiver = new Runnable() {
            public void run() {
                onTimer();
            }
        };
        handler = new Handler();
        handler.postDelayed(receiver, DELAY);
    }
    
    private void connectDevice(String mac) {
        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
        try {
            BluetoothDevice btd = bta.getRemoteDevice(mac);
            bts = btd.createInsecureRfcommSocketToServiceRecord(
                    UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            bts.connect();
        } catch (Exception e) {
            bts = null;
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }
    
    private void onTimer() {
        try {
            InputStream in = bts.getInputStream();
            int count = 0;
            while (in.available() > 0) {
                view.disp.addChar((char) in.read());
                count += 1;
            }
            if (count > 0) {
                view.invalidate();
            }
        } catch (Exception e) {
            
        }
        if (receiver != null) {
            handler.postDelayed(receiver, DELAY);
        }
    }
    
    public void sendKey(char c) {
        try {
            bts.getOutputStream().write(c);
        } catch (Exception e) {
            Toast.makeText(this, "Error3: " + e, Toast.LENGTH_LONG).show();
        }
    }
    
    @Override
    protected void onPause() {
        handler.removeCallbacks(receiver);
        receiver = null;
        try {
            bts.getInputStream().close();
        } catch (Exception e) {
            Toast.makeText(this, "Error1: " + e, Toast.LENGTH_LONG).show();
        }
        try {
            bts.getOutputStream().close();
        } catch (Exception e) {
            Toast.makeText(this, "Error2: " + e, Toast.LENGTH_LONG).show();
        }
        try {
            bts.close();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
        super.onPause();
    }
    
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        return this.view.onTouch(event);
    }
    
}

