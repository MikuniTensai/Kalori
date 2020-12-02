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

    public void clickContactUs(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mikunitensai@gmail.com", "1818046@scholar.itn.ac.id"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Email");
        i.putExtra(Intent.EXTRA_TEXT, "Welcome to Kalori");
        startActivity(Intent.createChooser(i, "Pilih email client"));
    }
}