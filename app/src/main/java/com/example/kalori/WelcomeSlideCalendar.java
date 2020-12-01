package com.example.kalori;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeSlideCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_slide_calendar);
    }

    public void clickGetStarted(View view) {
        Intent i = new Intent(this, WelcomeSlideSuperHero.class);
        startActivity(i);
    }

    public void clickLogin(View view) {
        Intent i = new Intent(this, WelcomeBack.class);
        startActivity(i);
    }
}