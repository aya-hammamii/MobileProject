package com.example.mobileproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class AirplaneModeChangeReceiver extends BroadcastReceiver {

    TextView text2;
    AirplaneModeChangeReceiver(TextView text2){
        this.text2=text2;

    }
    @Override
    public void onReceive(Context context, Intent intent) {

        if (isAirplaneModeOn(context.getApplicationContext())) {
            text2.setText("ON");
            Toast.makeText(context, "AirPlane mode is on", Toast.LENGTH_SHORT).show();
        } else {
            text2.setText("OFF");
            Toast.makeText(context, "AirPlane mode is off", Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}
