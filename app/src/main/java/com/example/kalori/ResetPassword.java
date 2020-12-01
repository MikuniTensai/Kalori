package com.example.kalori;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPassword extends AppCompatActivity {

    EditText editTextCode, editTextPassword, editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        editTextCode = findViewById(R.id.edt_reset_code);
        editTextPassword = findViewById(R.id.edt_new_password);
        editTextConfirmPassword = findViewById(R.id.edt_confirm_password);
    }

    public void postChangePassword(View view) {
        if (TextUtils.isEmpty(editTextCode.toString().trim()) && TextUtils.isEmpty(editTextPassword.toString().trim()) && TextUtils.isEmpty(editTextConfirmPassword.toString().trim())) {
            toastPostChangePassword(view, "Code dan Password tidak boleh kosong!");
        } else if (TextUtils.isEmpty(editTextCode.getText().toString().trim())) {
            toastPostChangePassword(view, "Code tidak valid!");
        } else if (TextUtils.isEmpty(editTextPassword.getText().toString().trim()) && TextUtils.isEmpty(editTextConfirmPassword.getText().toString().trim())) {
            toastPostChangePassword(view, "Password tidak cocok");
        }
        else {
            Intent i = new Intent(ResetPassword.this, Success.class);
            startActivity(i);
        }
    }

    public void toastPostChangePassword(View view, String string) {
        Toast.makeText(view.getContext(), string, Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}