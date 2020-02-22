package com.example.lab6;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class TimeActivity extends AppCompatActivity {
    private TextView localTime, Time2, Time3, Time4, Time5;
    private String newtime, time;
    private LocalTime time2, mytime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);


        localTime = findViewById(R.id.localTimeView);
        Time2 = findViewById(R.id.time2);
        Time3 = findViewById(R.id.time3);
        Time4 = findViewById(R.id.time4);
        Time5 = findViewById(R.id.time5);

        final Intent intent = getIntent();
        time = intent.getStringExtra("TIME");
        time2 = LocalTime.parse(time);
        newtime = formatter.format(time2);
        mytime = LocalTime.parse(newtime);

        updateTime();
    }

    public void updateTime(){
        new Thread() {
            public void run() {
                while(true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            mytime = LocalTime.now(ZoneId.of("GMT+2"));
                            newtime = formatter.format(mytime);

                            localTime.setText(newtime);
                            Time2.setText(formatter.format(mytime.minus(5, ChronoUnit.HOURS)));
                            Time3.setText(formatter.format(mytime.minus(6, ChronoUnit.HOURS)));
                            Time4.setText(formatter.format(mytime.plus(4, ChronoUnit.HOURS)));
                            Time5.setText(formatter.format(mytime.plus(3, ChronoUnit.HOURS)));

                        }
                    });
                    SystemClock.sleep(1000);
                }
            }
        }.start();
    }

}
