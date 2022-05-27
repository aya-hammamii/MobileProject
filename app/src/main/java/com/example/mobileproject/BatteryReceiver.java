package com.example.mobileproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

class BatteryReceiver extends BroadcastReceiver {

    TextView text;
    BatteryReceiver(TextView text){
        this.text=text;

    }
     @Override
     public void onReceive(Context context, Intent intent){

        int percentage=intent.getIntExtra("level", 0);
        if(percentage!=0){
            text.setText(percentage+"%");
        }
     }
 }
