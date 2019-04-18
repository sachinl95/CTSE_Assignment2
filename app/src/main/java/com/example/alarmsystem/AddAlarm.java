package com.example.alarmsystem;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

public class AddAlarm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TimePicker timePicker;
    private Spinner spinner;
    private MediaPlayer alarmTone;
    private static final String[] paths = {"Select Alarm Tone","Tone 1", "Tone 2", "Tone 3"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
         timePicker.setIs24HourView(true);

        spinner = (Spinner)findViewById(R.id.tone);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Handler handler=new Handler();
        if(alarmTone!=null){
            alarmTone.stop();
        }
        switch (position){
            case 1:
                alarmTone = MediaPlayer.create(this, R.raw.twin_alarm);
                alarmTone.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alarmTone.stop();
                    }
                }, 5000);
                break;
            case 2:
                alarmTone = MediaPlayer.create(this,R.raw.alarm_ringtones);
                alarmTone.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alarmTone.stop();
                    }
                }, 5000);
                break;
            case 3:
                alarmTone=MediaPlayer.create(this,R.raw.wrecker_alarm);
                alarmTone.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alarmTone.stop();
                    }
                }, 3000);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
