package com.example.alarmsystem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddAlarm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TimePicker timePicker;
    private Spinner spinner;
    private MediaPlayer alarmTone;
    private EditText alarmNameText;
    private static final String[] paths = {"Select Alarm Tone","Tone 1", "Tone 2", "Tone 3"};
    DataBaseHelper mDataBaseHelper;
    String alarmName;
    String alarmTime;
    int alarmToneSelected;
    int alarmTimeHours;
    int alarmTimeMinute;
    private PendingIntent alarmIntent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmNameText = (EditText) findViewById(R.id.alarm_name_txt);
         timePicker.setIs24HourView(true);

        spinner = (Spinner)findViewById(R.id.tone);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        mDataBaseHelper = new DataBaseHelper(this);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                alarmTime = Integer.toString(hourOfDay)+":"+Integer.toString(minute);
                alarmTimeHours = hourOfDay;
                alarmTimeMinute = minute;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Handler handler=new Handler();
        if(alarmTone!=null){
            alarmTone.stop();
        }
        alarmToneSelected = position;
        switch (position){
            case 1:
                alarmTone = MediaPlayer.create(this, R.raw.twin_alarm);
                alarmTone.start();
                break;
            case 2:
                alarmTone = MediaPlayer.create(this,R.raw.alarm_ringtones);
                alarmTone.start();
                break;
            case 3:
                alarmTone=MediaPlayer.create(this,R.raw.wrecker_alarm);
                alarmTone.start();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void saveAlarm(View view) {
        alarmName = alarmNameText.getText().toString();
        try {
            alarmTone.stop();
        }
        catch (Exception ex)
        {
            throw ex;
        }

        if (alarmName != null && alarmTime != null && alarmToneSelected > 0) {
            boolean insertData = mDataBaseHelper.addAlarm(alarmName, alarmTime, alarmToneSelected);
            if(insertData){
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(this,MyBroadCastReciever.class);
                alarmIntent = PendingIntent.getBroadcast(this,0,intent,0);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY,alarmTimeHours);
                calendar.set(Calendar.MINUTE,alarmTimeMinute);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1000*60*20,alarmIntent);




                toastMessage("Added Successfully");
                finish();
            }
            else {
                toastMessage("Error in Adding Alarm");
            }
        } else {
            toastMessage("Please fill all values");
        }
    }
    public void toastMessage(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
