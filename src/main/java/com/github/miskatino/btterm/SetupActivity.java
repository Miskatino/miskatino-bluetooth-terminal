package com.github.miskatino.btterm;

import android.app.*;
import android.os.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.bluetooth.*;
import java.util.*;

public class SetupActivity extends Activity {
    
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
    
    private void tryToConnect() {
        DeviceDescription dev = (DeviceDescription) devs.getSelectedItem();
        Bundle b = new Bundle();
        b.putCharSequence("dev", dev.name);
        b.putCharSequence("mac", dev.mac);
        Intent intent = new Intent(SetupActivity.this, MainActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
    
}

