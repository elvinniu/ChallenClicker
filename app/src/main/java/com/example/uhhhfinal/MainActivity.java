package com.example.uhhhfinal;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Boolean.FALSE;


public class MainActivity extends AppCompatActivity {
    public static class GlobalVars {
        public static Long globalChallen;
        public static boolean globalStarted;
        public static Long numStudents;
        public static Long numLaptops;
        public static Long numTutors;
        public static Long numProgrammers;
        public static Long numBens;
        public static SharedPreferences pref;
        public static Long genStudents;
        public static Long genLaptops;
        public static Long genTutors;
        public static Long genProgrammers;
        public static Long genBens;
        public static boolean musicMute;
        public static boolean soundMute;
    }
    public String geocounter = GlobalVars.globalChallen + " geoffs";
    public String startUpText = "What are you waiting for? Touch him!";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bimer.cancel();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!GlobalVars.globalStarted) {
            GlobalVars.globalChallen = 0L;
            GlobalVars.globalStarted = false;
            GlobalVars.genStudents = 1L;
            GlobalVars.genLaptops = 10L;
            GlobalVars.genTutors = 80L;
            GlobalVars.genProgrammers = 470L;
            GlobalVars.genBens = 100000000L;
            GlobalVars.musicMute = false;
            GlobalVars.soundMute = false;
        }

        if (!isMyServiceRunning(MusicService.class ) && !GlobalVars.musicMute) {
            startService(new Intent(this, MusicService.class));
        }

        final MediaPlayer geoffWelcome = MediaPlayer.create(this, R.raw.ultimatestart);
        ImageButton button = findViewById(R.id.geoff);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!GlobalVars.globalStarted) {
                    if (!GlobalVars.soundMute) {
                        geoffWelcome.start();
                        geoffWelcome.setVolume(1.0f,1.0f);
                    }
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
        final Timer timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences.Editor editor = GlobalVars.pref.edit();
                        GlobalVars.globalChallen += GlobalVars.genStudents * GlobalVars.numStudents;
                        GlobalVars.globalChallen += GlobalVars.genLaptops * GlobalVars.numLaptops;
                        GlobalVars.globalChallen += GlobalVars.genTutors * GlobalVars.numTutors;
                        GlobalVars.globalChallen += GlobalVars.genProgrammers * GlobalVars.numProgrammers;
                        GlobalVars.globalChallen += GlobalVars.genBens * GlobalVars.numBens;
                        editor.putLong("challens", GlobalVars.globalChallen);
                        editor.putLong("students", GlobalVars.numStudents);
                        editor.putLong("laptops", GlobalVars.numLaptops);
                        editor.putLong("tutors", GlobalVars.numTutors);
                        editor.putLong("programmers", GlobalVars.numProgrammers);
                        editor.putLong("bens", GlobalVars.numBens);
                        editor.putLong("genStudents", GlobalVars.genStudents);
                        editor.putLong("genLaptops", MainActivity.GlobalVars.genLaptops);
                        editor.putLong("genTutors", MainActivity.GlobalVars.genTutors);
                        editor.putLong("genProgrammers", MainActivity.GlobalVars.genProgrammers);
                        editor.putLong("genBens", MainActivity.GlobalVars.genBens);
                        editor.apply();
                        if (!isMyServiceRunning(MusicService.class) && !GlobalVars.musicMute) {
                            startService(new Intent(MainActivity.this, MusicService.class));
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 1000, 1000); // 1000 = 1 second.
    }

    final Handler handler = new Handler();
    Timer bimer = new Timer(false);
    TimerTask bimerTask = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    updateText();
                }
            });
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        bimer.cancel();
    }

    public void updateText() {
        TextView counter = findViewById(R.id.gcounter);
        formatText();
        counter.setText(geocounter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        GlobalVars.pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        GlobalVars.globalChallen = GlobalVars.pref.getLong("challens", 0L);
        GlobalVars.numStudents = GlobalVars.pref.getLong("students", 0L);
        GlobalVars.genStudents = GlobalVars.pref.getLong("genStudents", 1L);
        GlobalVars.numLaptops = GlobalVars.pref.getLong("laptops", 0L);
        GlobalVars.genLaptops = GlobalVars.pref.getLong("genLaptops", 10L);
        GlobalVars.numTutors = GlobalVars.pref.getLong("tutors", 0L);
        GlobalVars.genTutors = GlobalVars.pref.getLong("genTutors", 80L);
        GlobalVars.numProgrammers = GlobalVars.pref.getLong("progammers", 0L);
        GlobalVars.genProgrammers = GlobalVars.pref.getLong("genProgrammers", 470L);
        GlobalVars.numBens = GlobalVars.pref.getLong("bens", 0L);
        GlobalVars.genBens = GlobalVars.pref.getLong("genBens", 100000000L);
        GlobalVars.musicMute = GlobalVars.pref.getBoolean("musicMute", FALSE);
        GlobalVars.soundMute = GlobalVars.pref.getBoolean("soundMute", FALSE);
        TextView startUp = findViewById(R.id.startUp);
        TextView counter = findViewById(R.id.gcounter);
        startUp.setText(startUpText);
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
        bimer.schedule(bimerTask, 1000, 1000); // 1000 = 1 second.
    }

    public void formatText() {
        int zeros = (int) Math.log10(GlobalVars.globalChallen);
        if (zeros < 3) {
            geocounter = GlobalVars.globalChallen + " geoffs";
        }
        if (zeros >= 3) {
            double toDisplay = Math.floor(GlobalVars.globalChallen / Math.pow(10, 2)) / 10;
            geocounter = toDisplay + "K geoffs";
        }
        if (zeros >= 6) {
            double toDisplay = Math.floor(GlobalVars.globalChallen / Math.pow(10, 5)) / 10;
            geocounter = toDisplay + "M geoffs";
        }
        if (zeros >= 9) {
            double toDisplay = Math.floor(GlobalVars.globalChallen / Math.pow(10, 8)) / 10;
            geocounter = toDisplay + "B geoffs";
        }
        if (zeros >= 12) {
            double toDisplay = Math.floor(GlobalVars.globalChallen / Math.pow(10, 11)) / 10;
            geocounter = toDisplay + "T geoffs";
        }
        if (zeros >= 15) {
            double toDisplay = Math.floor(GlobalVars.globalChallen / Math.pow(10, 14)) / 10;
            geocounter = toDisplay + "q geoffs";
        }
        if (zeros >= 18) {
            double toDisplay = Math.floor(GlobalVars.globalChallen / Math.pow(10, 17)) / 10;
            geocounter = toDisplay + "Q geoffs";
        }
        if (zeros >= 21) {
            double toDisplay = Math.floor(GlobalVars.globalChallen / Math.pow(10, 20)) / 10;
            geocounter = toDisplay + "s geoffs";
        }
    }

    public void clickGeoff() {
        TextView counter = (TextView) findViewById(R.id.gcounter);
        GlobalVars.globalChallen++;
        formatText();
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
        TextView counter = (TextView) findViewById(R.id.gcounter);
        startUp.setText(startUpText);
        formatText();
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
