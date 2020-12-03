package com.example.kalori.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kalori.R;

public class NewBmiActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText edtberat, edtTinggi;
    private TextView tvHasil;
    private Button buttonCalculate, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bmi);

        edtberat = findViewById(R.id.edt_berat);
        edtTinggi = findViewById(R.id.edt_tinggi);
        tvHasil = findViewById(R.id.tv_hasil);
        buttonCalculate = findViewById(R.id.button_calculate);
        buttonBack = findViewById(R.id.button_back);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float hitungBmi = Float.valueOf(edtberat.getText().toString())/(Float.valueOf(edtTinggi.getText().toString())*Float.valueOf(edtTinggi.getText().toString()));
                tvHasil.setText(String.valueOf(hitungBmi));
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(tvHasil.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = tvHasil.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent =  new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(mIntent);
            }
        });
    }

}