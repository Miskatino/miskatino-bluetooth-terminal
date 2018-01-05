package com.github.miskatino.btterm;

import android.app.*;
import android.os.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.bluetooth.*;
import java.util.*;

public class SetupActivity extends Activity {
    
    private SharedPreferences prefs;
    
    public static class DeviceDescription {
        
        private String name, mac;
        
        private DeviceDescription(BluetoothDevice d) {
            name = d.getName();
            mac = d.getAddress();
        }
        
        public String toString() {
            return name;
        }
        
    }
    
    private Spinner devs;
    private Spinner kbds;
    private Spinner fonts;
    
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.setup);
        Button btn = (Button) findViewById(R.id.connect_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tryToConnect();
			}
        });
        populateDeviceList();
        prefs = getPreferences(Context.MODE_PRIVATE);
        populateKbdChoice(prefs.getInt("kbd", 0));
        populateFontSize(prefs.getInt("font", 1));
    }
    
    private void populateKbdChoice(int defIndex) {
        kbds = (Spinner) findViewById(R.id.kbd_type);
        ArrayAdapter<String> kbdsAda = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, Arrays.asList("5 rows of 10", "7 rows of 7"));
        kbdsAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kbds.setAdapter(kbdsAda);
        kbds.setSelection(defIndex);
    }
    
    private void populateDeviceList() {
        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
        devs = (Spinner) findViewById(R.id.dev_list);
        List<DeviceDescription> devDescr = new ArrayList<>();
        for (BluetoothDevice d : bta.getBondedDevices()) {
            devDescr.add(new DeviceDescription(d));
        }
        ArrayAdapter<DeviceDescription> devsAda = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, devDescr);
        devsAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        devs.setAdapter(devsAda);
    }
    
    private void populateFontSize(int defIndex) {
        fonts = (Spinner) findViewById(R.id.font_size);
        ArrayAdapter<String> fontsAda = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, Arrays.asList("Small: 48 chars", "Medium: 32 chars", "Large: 24 chars"));
        fontsAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fonts.setAdapter(fontsAda);
        fonts.setSelection(defIndex);
    }
    
    private void tryToConnect() {
        DeviceDescription dev = (DeviceDescription) devs.getSelectedItem();
        Bundle b = new Bundle();
        b.putCharSequence("mac", dev.mac);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("kbd", kbds.getSelectedItemPosition());
        editor.putInt("font", fonts.getSelectedItemPosition());
        editor.commit();
        b.putInt("kbdrows", Integer.parseInt(String.valueOf(kbds.getSelectedItem()).replaceFirst("\\s.*", "")));
        b.putInt("font", Integer.parseInt(String.valueOf(fonts.getSelectedItem()).replaceAll("\\D+", "")));
        Intent intent = new Intent(SetupActivity.this, MainActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
    
}

