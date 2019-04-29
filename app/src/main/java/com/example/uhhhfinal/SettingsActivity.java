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

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

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
                MainActivity.GlobalVars.globalChallen = 0L;
                MainActivity.GlobalVars.numStudents = 0L;
                MainActivity.GlobalVars.numLaptops = 0L;
                MainActivity.GlobalVars.numTutors = 0L;
                MainActivity.GlobalVars.numProgrammers = 0L;
                MainActivity.GlobalVars.numBens = 0L;

                SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                editor.putLong("challens", MainActivity.GlobalVars.globalChallen);
                editor.putLong("students", MainActivity.GlobalVars.numStudents);
                editor.putLong("laptops", MainActivity.GlobalVars.numLaptops);
                editor.putLong("tutors", MainActivity.GlobalVars.numTutors);
                editor.putLong("programmers", MainActivity.GlobalVars.numProgrammers);
                editor.putLong("bens", MainActivity.GlobalVars.numBens);
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
