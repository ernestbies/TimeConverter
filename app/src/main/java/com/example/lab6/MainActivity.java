package com.example.lab6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.time.LocalTime;
import java.time.ZoneId;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void time(View view) {
        LocalTime lt = LocalTime.now(ZoneId.of("GMT+2"));
        String stime = lt.toString();
        Intent intent = new Intent(this,TimeActivity.class);
        intent.putExtra("TIME",stime);
        startActivity(intent);
    }
}
