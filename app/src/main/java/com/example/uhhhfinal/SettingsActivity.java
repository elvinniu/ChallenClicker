package com.example.uhhhfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.TimerTask;

import static java.lang.Boolean.FALSE;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        FloatingActionButton music = findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.GlobalVars.pref.getBoolean("musicMute", FALSE)) {
                    MainActivity.GlobalVars.musicMute = false;
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putBoolean("musicMute", MainActivity.GlobalVars.musicMute);
                    editor.apply();
                } else {
                    stopService(new Intent(SettingsActivity.this, MusicService.class));
                    MainActivity.GlobalVars.musicMute = true;
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putBoolean("musicMute", MainActivity.GlobalVars.musicMute);
                    editor.apply();
                }
            }
        });

        FloatingActionButton sound = findViewById(R.id.sound);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.GlobalVars.pref.getBoolean("soundMute", FALSE)) {
                    MainActivity.GlobalVars.soundMute = false;
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putBoolean("soundMute", MainActivity.GlobalVars.soundMute);
                    editor.apply();
                } else {
                    MainActivity.GlobalVars.soundMute = true;
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putBoolean("soundMute", MainActivity.GlobalVars.soundMute);
                    editor.apply();
                }
            }
        });

        FloatingActionButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MenuActivity.class));
            }
        });

        Button reset = findViewById(R.id.resetbutton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.GlobalVars.globalChallen = 100000000000000L;
                MainActivity.GlobalVars.numStudents = 0L;
                MainActivity.GlobalVars.numLaptops = 0L;
                MainActivity.GlobalVars.numTutors = 0L;
                MainActivity.GlobalVars.numProgrammers = 0L;
                MainActivity.GlobalVars.numBens = 0L;
                MainActivity.GlobalVars.genStudents = 1L;
                MainActivity.GlobalVars.genLaptops = 10L;
                MainActivity.GlobalVars.genTutors = 80L;
                MainActivity.GlobalVars.genProgrammers = 470L;
                MainActivity.GlobalVars.genBens = 100000000L;

                SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                editor.putLong("challens", MainActivity.GlobalVars.globalChallen);
                editor.putLong("students", MainActivity.GlobalVars.numStudents);
                editor.putLong("laptops", MainActivity.GlobalVars.numLaptops);
                editor.putLong("tutors", MainActivity.GlobalVars.numTutors);
                editor.putLong("programmers", MainActivity.GlobalVars.numProgrammers);
                editor.putLong("bens", MainActivity.GlobalVars.numBens);
                editor.putLong("genStudents", MainActivity.GlobalVars.genStudents);
                editor.putLong("genLaptops", MainActivity.GlobalVars.genLaptops);
                editor.putLong("genTutors", MainActivity.GlobalVars.genTutors);
                editor.putLong("genProgrammers", MainActivity.GlobalVars.genProgrammers);
                editor.putLong("genBens", MainActivity.GlobalVars.genBens);
                editor.apply();
            }
        });
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
