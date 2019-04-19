package com.example.alarmsystem;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AlarmRinging extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner spinner;

    private static final String[] answers = {"Select Answer","Phosphorous", "Chlorine", "Bromine","Helium"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_ringing);
        spinner = (Spinner)findViewById(R.id.answers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,answers);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 1:
                toastMessage("Incorrect Answer");
                break;
            case 2:
                toastMessage("Incorrect Answer");
                break;
            case 3:
                MediaPlayer mediaPlayer = MediaPlayer.create(this,1);
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                break;
            case 4:
                toastMessage("Incorrect Answer");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void toastMessage(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
