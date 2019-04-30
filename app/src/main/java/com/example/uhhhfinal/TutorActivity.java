package com.example.uhhhfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TutorActivity extends AppCompatActivity {
    public String geoffCounter = MainActivity.GlobalVars.globalChallen + "";
    private Long price = 11000L;
    private Long priceten = Math.round(price * 20.303718238);
    private Long pricehundred = Math.round(price * 7828749.671335256);
    private String priceString = price + "G per";

    protected void updatePrice() {
        price = Math.round(11000*(Math.pow(1.15, MainActivity.GlobalVars.numTutors)));
        priceten = Math.round(price * 20.303718238);
        pricehundred = Math.round(price * 7828749.671335256);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_layout);

        TextView currentGeoffs = findViewById(R.id.currency);
        currentGeoffs.setText(geoffCounter);

        FloatingActionButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TutorActivity.this, MenuActivity.class));
            }
        });

        Button pricetag = findViewById(R.id.pricetag);
        priceString = formatText(price) + " G per";
        pricetag.setText(priceString);

        final MediaPlayer anotherone = MediaPlayer.create(this, R.raw.afterupgrade2buy);
        final MediaPlayer catcall = MediaPlayer.create(this, R.raw.catcall);
        final MediaPlayer hax = MediaPlayer.create(this, R.raw.hax);
        Button buyone = findViewById(R.id.plus1);
        buyone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.GlobalVars.globalChallen >= price) {
                    MainActivity.GlobalVars.globalChallen -= price;
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putLong("challens", MainActivity.GlobalVars.globalChallen);
                    editor.putLong("tutors", MainActivity.GlobalVars.numTutors);
                    editor.apply();
                    MainActivity.GlobalVars.numTutors++;
                    if (MainActivity.GlobalVars.numTutors > 1 && !MainActivity.GlobalVars.soundMute) {
                        anotherone.start();
                        anotherone.setVolume(1.0f, 1.0f);
                    }
                }
                TextView currentGeoffs = findViewById(R.id.currency);
                geoffCounter = formatText(MainActivity.GlobalVars.globalChallen);
                currentGeoffs.setText(geoffCounter);
                updatePrice();
                Button pricetag = findViewById(R.id.pricetag);
                priceString = formatText(price) + " G per";
                pricetag.setText(priceString);
            }
        });

        Button buyten = findViewById(R.id.plus10);
        buyten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.GlobalVars.globalChallen >= priceten) {
                    MainActivity.GlobalVars.globalChallen -= priceten;
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putLong("challens", MainActivity.GlobalVars.globalChallen);
                    editor.putLong("tutors", MainActivity.GlobalVars.numTutors);
                    editor.apply();
                    MainActivity.GlobalVars.numTutors += 10;
                    if (!MainActivity.GlobalVars.soundMute) {
                        catcall.start();
                        catcall.setVolume(1.0f, 1.0f);
                    }
                }
                TextView currentGeoffs = findViewById(R.id.currency);
                geoffCounter = formatText(MainActivity.GlobalVars.globalChallen);
                currentGeoffs.setText(geoffCounter);
                updatePrice();
                Button pricetag = findViewById(R.id.pricetag);
                priceString = formatText(price) + " G per";
                pricetag.setText(priceString);
            }
        });

        Button buyhundred = findViewById(R.id.plus100);
        buyhundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.GlobalVars.globalChallen >= pricehundred) {
                    MainActivity.GlobalVars.globalChallen -= pricehundred;
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putLong("challens", MainActivity.GlobalVars.globalChallen);
                    editor.putLong("tutors", MainActivity.GlobalVars.numTutors);
                    editor.apply();
                    MainActivity.GlobalVars.numTutors += 100;
                    if (!MainActivity.GlobalVars.soundMute) {
                        hax.start();
                        hax.setVolume(1.0f, 1.0f);
                    }
                }
                TextView currentGeoffs = findViewById(R.id.currency);
                geoffCounter = formatText(MainActivity.GlobalVars.globalChallen);
                currentGeoffs.setText(geoffCounter);
                updatePrice();
                Button pricetag = findViewById(R.id.pricetag);
                priceString = formatText(price) + " G per";
                pricetag.setText(priceString);
            }
        });

        final MediaPlayer upgradeTutor1 = MediaPlayer.create(this, R.raw.tutorfirstupgrade);
        final FloatingActionButton upgrade1 = findViewById(R.id.csUpgrade);
        upgrade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.GlobalVars.globalChallen >= 93500000L && MainActivity.GlobalVars.genTutors < 800L) {
                    MainActivity.GlobalVars.globalChallen -= 93500000L;
                    MainActivity.GlobalVars.genTutors = 800L;
                    upgrade1.setBackgroundTintList(ColorStateList.valueOf(0xFF00FF00));
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putLong("challens", MainActivity.GlobalVars.globalChallen);
                    editor.putLong("genTutors", MainActivity.GlobalVars.genTutors);
                    editor.apply();
                    Button rate = findViewById(R.id.rate);
                    priceString = formatText(MainActivity.GlobalVars.genTutors) + " G/s";
                    rate.setText(priceString);
                    if (!MainActivity.GlobalVars.soundMute) {
                        upgradeTutor1.start();
                        upgradeTutor1.setVolume(1.0f, 1.0f);
                    }
                }
            }
        });

        final MediaPlayer upgradeTutor2 = MediaPlayer.create(this, R.raw.tutorsecondupgrade);
        final FloatingActionButton upgrade2 = findViewById(R.id.csUpgrade2);
        upgrade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.GlobalVars.globalChallen >= 181500000000L && MainActivity.GlobalVars.genTutors < 800000L) {
                    MainActivity.GlobalVars.globalChallen -= 181500000000L;
                    MainActivity.GlobalVars.genTutors = 800000L;
                    upgrade1.setBackgroundTintList(ColorStateList.valueOf(0xFF00FF00));
                    upgrade2.setBackgroundTintList(ColorStateList.valueOf(0xFF00FF00));
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putLong("challens", MainActivity.GlobalVars.globalChallen);
                    editor.putLong("genTutors", MainActivity.GlobalVars.genTutors);
                    editor.apply();
                    Button rate = findViewById(R.id.rate);
                    priceString = formatText(MainActivity.GlobalVars.genTutors) + " G/s";
                    rate.setText(priceString);
                    if (!MainActivity.GlobalVars.soundMute) {
                        upgradeTutor2.start();
                        upgradeTutor2.setVolume(1.0f, 1.0f);
                    }
                }
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
        geoffCounter = formatText(MainActivity.GlobalVars.globalChallen);
        counter.setText(geoffCounter);
    }

    public String formatText(Long toFormat) {
        int zeros = (int) Math.log10(toFormat);
        if (zeros < 3) {
            return toFormat + "";
        }
        if (zeros >= 3 && zeros < 6) {
            double toDisplay = Math.floor(toFormat / Math.pow(10, 2)) / 10;
            return toDisplay + "K";
        }
        if (zeros >= 6 && zeros < 9) {
            double toDisplay = Math.floor(toFormat / Math.pow(10, 5)) / 10;
            return toDisplay + "M";
        }
        if (zeros >= 9 && zeros < 12) {
            double toDisplay = Math.floor(toFormat / Math.pow(10, 8)) / 10;
            return toDisplay + "B";
        }
        if (zeros >= 12 && zeros < 15) {
            double toDisplay = Math.floor(toFormat / Math.pow(10, 11)) / 10;
            return toDisplay + "T";
        }
        if (zeros >= 15 && zeros < 18) {
            double toDisplay = Math.floor(toFormat / Math.pow(10, 14)) / 10;
            return toDisplay + "q";
        }
        if (zeros >= 18 && zeros < 21) {
            double toDisplay = Math.floor(toFormat / Math.pow(10, 17)) / 10;
            return toDisplay + "Q";
        }
        if (zeros >= 21 && zeros < 24) {
            double toDisplay = Math.floor(toFormat / Math.pow(10, 20)) / 10;
            return toDisplay + "s";
        }
        return "A lot of";
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.GlobalVars.pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        MainActivity.GlobalVars.globalChallen = MainActivity.GlobalVars.pref.getLong("challens", 0L);
        MainActivity.GlobalVars.numTutors = MainActivity.GlobalVars.pref.getLong("tutors", 0L);
        TextView currentGeoffs = findViewById(R.id.currency);
        geoffCounter = formatText(MainActivity.GlobalVars.globalChallen);
        currentGeoffs.setText(geoffCounter);
        updatePrice();

        if (MainActivity.GlobalVars.genTutors >= 800L) {
            FloatingActionButton upgrade1 = findViewById(R.id.csUpgrade);
            upgrade1.setBackgroundTintList(ColorStateList.valueOf(0xFF00FF00));
            if (MainActivity.GlobalVars.genTutors >= 800000L) {
                FloatingActionButton upgrade2 = findViewById(R.id.csUpgrade2);
                upgrade2.setBackgroundTintList(ColorStateList.valueOf(0xFF00FF00));
            }
        }

        Button pricetag = findViewById(R.id.pricetag);
        priceString = formatText(price) + " G per";
        pricetag.setText(priceString);
        Button rate = findViewById(R.id.rate);
        priceString = formatText(MainActivity.GlobalVars.genTutors) + " G/s";
        rate.setText(priceString);
        timer.schedule(timerTask, 1000, 1000); // 1000 = 1 second.
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
