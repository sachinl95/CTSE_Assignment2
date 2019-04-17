package com.example.alarmsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

public class AddAlarm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TimePicker timePicker;
    private Spinner spinner;
    private static final String[] paths = {"Tone 1", "Tone 2", "Tone 3"};

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
        System.out.println("asdn");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
