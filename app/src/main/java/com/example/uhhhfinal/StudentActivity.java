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

import java.util.Timer;
import java.util.TimerTask;

public class StudentActivity extends AppCompatActivity {
    public String geoffCounter = MainActivity.GlobalVars.globalChallen + "";
    private Long price = 6L;
    private Long priceten = Math.round(price * 20.303718238);
    private Long pricehundred = Math.round(price * 7828749.671335256);

    protected void updatePrice() {
        price = Math.round(6*(Math.pow(1.15, MainActivity.GlobalVars.numStudents)));
        priceten = Math.round(price * 20.303718238);
        pricehundred = Math.round(price * 7828749.671335256);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_layout);

        TextView currentGeoffs = findViewById(R.id.currency);
        currentGeoffs.setText(geoffCounter);

        FloatingActionButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentActivity.this, MenuActivity.class));
            }
        });

        Button buyone = findViewById(R.id.plus1);
        buyone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(price);
                if (MainActivity.GlobalVars.globalChallen >= price) {
                    MainActivity.GlobalVars.globalChallen -= price;
                    SharedPreferences.Editor editor = MainActivity.GlobalVars.pref.edit();
                    editor.putLong("challens", MainActivity.GlobalVars.globalChallen);
                    editor.putLong("students", MainActivity.GlobalVars.numStudents);
                    editor.apply();
                    MainActivity.GlobalVars.numStudents++;
                }
                TextView currentGeoffs = findViewById(R.id.currency);
                geoffCounter = MainActivity.GlobalVars.globalChallen + "";
                currentGeoffs.setText(geoffCounter);
                updatePrice();
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
                    editor.putLong("students", MainActivity.GlobalVars.numStudents);
                    editor.apply();
                    MainActivity.GlobalVars.numStudents += 10;
                }
                TextView currentGeoffs = findViewById(R.id.currency);
                currentGeoffs.setText(geoffCounter);
                updatePrice();
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
                    editor.putLong("students", MainActivity.GlobalVars.numStudents);
                    editor.apply();
                    MainActivity.GlobalVars.numStudents += 100;
                }
                TextView currentGeoffs = findViewById(R.id.currency);
                currentGeoffs.setText(geoffCounter);
                updatePrice();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.GlobalVars.pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        MainActivity.GlobalVars.globalChallen = MainActivity.GlobalVars.pref.getLong("challens", 0L);
        MainActivity.GlobalVars.numStudents = MainActivity.GlobalVars.pref.getLong("students", 0L);
        geoffCounter = MainActivity.GlobalVars.globalChallen + "";
        //updateSecond();
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
