package com.example.kalori;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeBack extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_back);

        editTextEmail = findViewById(R.id.edt_txt_email);
        editTextPassword = findViewById(R.id.edt_txt_password);
    }

    public void postLogin(View view) {
        if(TextUtils.isEmpty(editTextEmail.getText().toString().trim()) && TextUtils.isEmpty(editTextPassword.getText().toString().trim())) {
            toastPostLogin(view, "Email dan Password tidak boleh kosong!");
        }
        else if(!isValidEmail(editTextEmail.getText().toString().trim())) {
            toastPostLogin(view, "Email tidak valid!");
        }
        else if(TextUtils.isEmpty(editTextPassword.getText().toString())) {
            toastPostLogin(view, "Password tidak boleh kosong!");
        }
        else {
            Intent i = new Intent(this, Success.class);
            startActivity(i);
        }
    }
    
    public void toastPostLogin(View view, String string){
        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();
    }

    public void clickForgot(View view) {
        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public void clickSignUp(View view) {
        Intent intent = new Intent(WelcomeBack.this, RegisterActivity.class);
        startActivity(intent);
    }
}