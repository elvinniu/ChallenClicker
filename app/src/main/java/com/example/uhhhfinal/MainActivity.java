package com.example.uhhhfinal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity {
    public static class GlobalVars {
        public static Long globalChallen = 0L;
        public static boolean globalStarted = false;
    }
    public String geocounter = GlobalVars.globalChallen + " geoffs";
    public String startUpText = "What are you waiting for? Touch him!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

        ImageButton button = (ImageButton) findViewById(R.id.geoff);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GlobalVars.globalStarted = true;
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

    @Override
    protected void onResume() {
        super.onResume();

        geocounter = GlobalVars.globalChallen + " geoffs";

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
        } else {
            final MediaPlayer geoffWelcome = MediaPlayer.create(this, R.raw.geoffsoundwelcome);
            geoffWelcome.start();
        }
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
