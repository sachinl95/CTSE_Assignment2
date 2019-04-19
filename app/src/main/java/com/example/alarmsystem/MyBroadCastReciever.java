package com.example.alarmsystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyBroadCastReciever extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        mp= MediaPlayer.create(context,R.raw.alarm_ringtones);
        mp.start();

        Intent alarmRingingIntent = new Intent(context, AlarmRinging.class);
        alarmRingingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alarmRingingIntent);
//        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }
}
