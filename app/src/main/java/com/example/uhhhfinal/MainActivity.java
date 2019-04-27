package com.example.uhhhfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    public static class GlobalVars {
        public static Long globalChallen;
        public static boolean globalStarted;
        public static Long numStudents;
        public static Long numLaptops;
        public static Long numTutors;
        public static Long numProgrammers;
        public static SharedPreferences pref;
    }
    public String geocounter = GlobalVars.globalChallen + " geoffs";
    public String startUpText = "What are you waiting for? Touch him!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!GlobalVars.globalStarted) {
            GlobalVars.globalChallen = 0L;
            GlobalVars.globalStarted = false;
        }

        final MediaPlayer geoffWelcome = MediaPlayer.create(this, R.raw.geoffsoundwelcome);

        ImageButton button = (ImageButton) findViewById(R.id.geoff);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!GlobalVars.globalStarted) {
                    geoffWelcome.start();
                    GlobalVars.globalStarted = true;
                }
                clickGeoff();
            }
        });

        FloatingActionButton menu = findViewById(R.id.menuButton);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });

    }

    public void updateSecond() {
        final Handler handler = new Handler();
        Timer timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences.Editor editor = GlobalVars.pref.edit();
                        GlobalVars.globalChallen += GlobalVars.numStudents;
                        editor.putLong("challens", GlobalVars.globalChallen);
                        editor.putLong("students", GlobalVars.numStudents);
                        editor.apply();
                        updateText();
                        System.out.println("challelesln: " + GlobalVars.globalChallen);
                        System.out.println("main: " + GlobalVars.numStudents);
                        updateText();
                    }
                });
            }
        };
        timer.schedule(timerTask, 1000, 1000); // 1000 = 1 second.
    }

    public void updateText() {
        TextView counter = (TextView) findViewById(R.id.counnt);
        geocounter = GlobalVars.globalChallen + " geoffs";
        counter.setText(geocounter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        GlobalVars.pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        GlobalVars.globalChallen = GlobalVars.pref.getLong("challens", 0L);
        GlobalVars.numStudents = GlobalVars.pref.getLong("students", 0L);

        //geocounter = GlobalVars.globalChallen + " geoffs";

        TextView startUp = (TextView) findViewById(R.id.startUp);
        TextView counter = (TextView) findViewById(R.id.counnt);
        startUp.setText(startUpText);
        //counter.setText(geocounter);
        updateText();
        if (GlobalVars.globalStarted) {
            startUp.setVisibility(View.GONE);
            counter.setVisibility(View.VISIBLE);
        } else {
            counter.setVisibility(View.GONE);
        }
        if (!GlobalVars.globalStarted) {
            updateSecond();
        }
    }

    public void clickGeoff() {
        TextView counter = (TextView) findViewById(R.id.counnt);
        GlobalVars.globalChallen++;
        geocounter = GlobalVars.globalChallen + " geoffs";

        counter.setText(geocounter);
        TextView startUp = (TextView) findViewById(R.id.startUp);
        startUp.setText(startUpText);
        counter.setVisibility(View.VISIBLE);
        if (GlobalVars.globalStarted) {
            startUp.setVisibility(View.GONE);
        }
        SharedPreferences.Editor editor = GlobalVars.pref.edit();
        editor.putLong("challens", GlobalVars.globalChallen);
        editor.apply();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong("geoffsOnExit", GlobalVars.globalChallen);
        outState.putBoolean("firstTimer", GlobalVars.globalStarted);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        GlobalVars.globalChallen = savedInstanceState.getLong("geoffsOnExit");
        geocounter = GlobalVars.globalChallen + " geoffs";

        GlobalVars.globalStarted = savedInstanceState.getBoolean("firstTimer");
        TextView startUp = (TextView) findViewById(R.id.startUp);
        TextView counter = (TextView) findViewById(R.id.counnt);
        startUp.setText(startUpText);
        counter.setText(geocounter);
        if (GlobalVars.globalStarted) {
            startUp.setVisibility(View.GONE);
            counter.setVisibility(View.VISIBLE);
        } else {
            counter.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
