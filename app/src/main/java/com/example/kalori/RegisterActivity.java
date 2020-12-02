package com.example.kalori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = RegisterActivity.class.getName();
    public static final String Key_RegisterActivity = "Key_RegisterActivity";

    EditText editTextNama, editTextTanggalLahir, editTextUsername, editTextPassword;
    RadioButton radioButtonLaki, radioButtonPerempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextNama = findViewById(R.id.edt_txt_nama);
        editTextTanggalLahir = findViewById(R.id.edt_tanggal_lahir);
        editTextUsername = findViewById(R.id.edt_username);
        editTextPassword = findViewById(R.id.edt_password);
        radioButtonLaki = findViewById(R.id.rb_laki);
        radioButtonPerempuan = findViewById(R.id.rb_perempuan);
    }

    public void postSignUp(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String nama = editTextNama.getText().toString();
        String jk = radioButtonPerempuan.isChecked() ? "Perempuan" : "Laki-Laki";
        String tanggal_lahir = editTextTanggalLahir.getText().toString();
        Log.d(TAG, "PostSignUp " + username);
        Log.d(TAG, "PostSignUp " + password);
        Log.d(TAG, "PostSignUp " + nama);
        Log.d(TAG, "PostSignUp " + jk);
        Log.d(TAG, "PostSignUp " + tanggal_lahir);
        Intent intent = new Intent(this, RegisterResultActivity.class);
        intent.putExtra(Key_RegisterActivity, new String[]{nama, tanggal_lahir, jk, username});
        startActivity(intent);
    }
}