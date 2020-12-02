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
        boolean code = TextUtils.isEmpty(editTextCode.toString().trim());
        boolean password = TextUtils.isEmpty(editTextPassword.toString().trim());
        boolean confirmations = TextUtils.isEmpty(editTextConfirmPassword.toString().trim());

        if ( code && password && confirmations ) {
            toastPostChangePassword(view, "Kode dan Password tidak boleh kosong!");
        } else if (code) {
            toastPostChangePassword(view, "Kode kosong atau tidak valid!");
        } else if (password && confirmations) {
            toastPostChangePassword(view, "Password tidak boleh kosong");
        } else if (password == confirmations) {
            toastPostChangePassword(view, "Password tidak cocok, coba cek kembali");
        }
        else {
            Intent i = new Intent(ResetPassword.this, Success.class);
            startActivity(i);
        }
    }

    public void toastPostChangePassword(View view, String string) {
        Toast.makeText(view.getContext(), string, Toast.LENGTH_SHORT).show();
    }
}