package com.github.miskatino.btterm;

import android.app.*;
import android.os.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.bluetooth.*;
import java.util.*;

public class SetupActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.setup);
        Button btn = (Button) findViewById(R.id.connect_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
				startActivity(new Intent(SetupActivity.this, MainActivity.class));
			}
        });
        List<String> names = new ArrayList<String>();
        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
        for (BluetoothDevice d : bta.getBondedDevices()) {
            names.add(d.getName());
        }
        Spinner devs = (Spinner) findViewById(R.id.dev_list);
        ArrayAdapter<String> devsAda = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, names);
        devsAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        devs.setAdapter(devsAda);
    }
    
}

