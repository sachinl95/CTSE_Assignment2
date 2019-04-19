package com.example.alarmsystem;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mAddAlarmButton;
//    private Toolbar toolbar;
    ListView alarmListView;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);
        Cursor cursor =dataBaseHelper.fetch();
        List<String> alarms = new ArrayList<>();
        if(cursor.getCount()==0){
            System.out.println("No Data found");
        }
        else {
            while (cursor.moveToNext()){
                alarms.add(cursor.getString(1)+"\t \t \t "+cursor.getString(2));
            }

        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alarms);
        alarmListView = (ListView) findViewById(R.id.list);
        alarmListView.setAdapter(adapter);


//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Alarm System");

//        alarmListView = (ListView)findViewById(R.id.list);
//        View emptyView = findViewById(R.id.empty_view);
//        alarmListView.setEmptyView(emptyView);

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
