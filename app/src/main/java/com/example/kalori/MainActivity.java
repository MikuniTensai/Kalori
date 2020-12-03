package com.example.kalori;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, WelcomeSlideAssign.class);
                startActivity(i);
                finish();
            }
        }, 1000);

    }
}
