package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;


public class SettingsActivity extends AppCompatActivity {

    AirplaneModeChangeReceiver airplaneModeChangeReceiver;

    BatteryReceiver batteryReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        TextView text=findViewById(R.id.textView);
        batteryReceiver= new BatteryReceiver(text);
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        TextView text2=findViewById(R.id.textView2);
        airplaneModeChangeReceiver= new AirplaneModeChangeReceiver(text2);
        registerReceiver(airplaneModeChangeReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        registerReceiver(airplaneModeChangeReceiver, filter);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unregisterReceiver(airplaneModeChangeReceiver);
//
//        unregisterReceiver(batteryReceiver);
//    }
}
