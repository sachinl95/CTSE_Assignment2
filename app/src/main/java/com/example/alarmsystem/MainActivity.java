package com.example.alarmsystem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mAddAlarmButton;
//    private Toolbar toolbar;
    ListView alarmListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Alarm System");

        alarmListView = (ListView)findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        alarmListView.setEmptyView(emptyView);

//    alarmListView.setOnItemClickListener();

        mAddAlarmButton = (FloatingActionButton)findViewById(R.id.fab);

        mAddAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddAlarm.class);
                startActivity(intent);
            }
        });

    }
}
