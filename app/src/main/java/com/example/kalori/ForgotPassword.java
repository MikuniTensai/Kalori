package com.example.kalori;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextEmail = findViewById(R.id.edt_txt_email);
    }

    public void postSendRequest(View view) {
        if (TextUtils.isEmpty(editTextEmail.getText().toString().trim())){
            toastPostSendRequest(view, "Email tidak boleh kosong!");
        } else if(!isValidEmail(editTextEmail.getText().toString().trim())) {
            toastPostSendRequest(view, "Email tidak valid!");
        }
        else {
            Intent i = new Intent(ForgotPassword.this, ResetPassword.class);
            startActivity(i);
        }
    }

    public void toastPostSendRequest(View view, String string){
        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}