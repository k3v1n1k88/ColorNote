package com.example.lap10581_local.colornotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lap10581_local.colornotes.CustomView.CalendarCustomView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarCustomView mView = (CalendarCustomView)findViewById(R.id.custom_calendar);
    }
}
