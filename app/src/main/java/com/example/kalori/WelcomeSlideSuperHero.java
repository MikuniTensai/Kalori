package com.example.kalori;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeSlideSuperHero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_slide_super_hero);
    }

    public void clickGetStarted(View view) {
        Intent i = new Intent(this, WelcomeSlideAssign.class);
        startActivity(i);
    }

    public void clickLogin(View view) {
        Intent i = new Intent(this, WelcomeBack.class);
        startActivity(i);
    }
}