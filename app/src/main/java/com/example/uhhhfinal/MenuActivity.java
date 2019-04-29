package com.example.uhhhfinal;

import android.content.Intent;
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

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MenuActivity extends AppCompatActivity {
    public String geoffCounter = MainActivity.GlobalVars.globalChallen + "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        TextView currentGeoffs = findViewById(R.id.currency);
        currentGeoffs.setText(geoffCounter);

        FloatingActionButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });

        FloatingActionButton laptop = findViewById(R.id.laptopPage);
        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, LaptopActivity.class));
            }
        });

        FloatingActionButton student = findViewById(R.id.studentPage);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, StudentActivity.class));
            }
        });

        FloatingActionButton tutor = findViewById(R.id.tutorPage);
        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, TutorActivity.class));
            }
        });

        FloatingActionButton programmer = findViewById(R.id.programmerPage);
        programmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, ProgrammerActivity.class));
            }
        });

        FloatingActionButton ben = findViewById(R.id.benPage);
        ben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, BenActivity.class));
            }
        });

        FloatingActionButton settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
            }
        });

    }

    final Handler handler = new Handler();
    Timer timer = new Timer(false);
    TimerTask timerTask = new TimerTask() {
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
        timer.cancel();
    }

    public void updateText() {
        TextView counter = findViewById(R.id.currency);
        formatText();
        counter.setText(geoffCounter);
    }

    public void formatText() {
        int zeros = (int) Math.log10(MainActivity.GlobalVars.globalChallen);
        if (zeros < 3) {
            geoffCounter = MainActivity.GlobalVars.globalChallen + "";
        }
        if (zeros >= 3) {
            double toDisplay = Math.floor(MainActivity.GlobalVars.globalChallen / Math.pow(10, 2)) / 10;
            geoffCounter = toDisplay + "K";
        }
        if (zeros >= 6) {
            double toDisplay = Math.floor(MainActivity.GlobalVars.globalChallen / Math.pow(10, 5)) / 10;
            geoffCounter = toDisplay + "M";
        }
        if (zeros >= 9) {
            double toDisplay = Math.floor(MainActivity.GlobalVars.globalChallen / Math.pow(10, 8)) / 10;
            geoffCounter = toDisplay + "B";
        }
        if (zeros >= 12) {
            double toDisplay = Math.floor(MainActivity.GlobalVars.globalChallen / Math.pow(10, 11)) / 10;
            geoffCounter = toDisplay + "T";
        }
        if (zeros >= 15) {
            double toDisplay = Math.floor(MainActivity.GlobalVars.globalChallen / Math.pow(10, 14)) / 10;
            geoffCounter = toDisplay + "q";
        }
        if (zeros >= 18) {
            double toDisplay = Math.floor(MainActivity.GlobalVars.globalChallen / Math.pow(10, 17)) / 10;
            geoffCounter = toDisplay + "Q";
        }
        if (zeros >= 21) {
            double toDisplay = Math.floor(MainActivity.GlobalVars.globalChallen / Math.pow(10, 20)) / 10;
            geoffCounter = toDisplay + "s";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView currentGeoffs = findViewById(R.id.currency);
        formatText();
        currentGeoffs.setText(geoffCounter);
        timer.schedule(timerTask, 1000, 1000); // 1000 = 1 second.
    }

    public void changeIcon() {
        FloatingActionButton laptop = findViewById(R.id.laptopPage);
        laptop.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.question_mark));
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
