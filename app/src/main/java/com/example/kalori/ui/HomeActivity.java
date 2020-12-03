package com.example.kalori.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kalori.ProfileActivity;
import com.example.kalori.R;
import com.example.kalori.data.AccountActivity;
import com.example.kalori.db.entity.BmiEntity;
import com.example.kalori.viewModel.BmiViewModel;

import java.util.List;

import me.ibrahimsn.lib.SmoothBottomBar;

public class HomeActivity extends AppCompatActivity {

    // Key-key untuk data yang disimpan di SharedPrefernces
    private static final String KEEP_LOGIN_KEY = "key_keep_login";

    // SharedPreferences yang akan digunakan untuk menulis dan membaca data
    private SharedPreferences sharedPrefs;

    RecyclerView recyclerView;
    BmiAdapter adapter;
    private BmiViewModel bmiViewModel;
    public static final int REQUEST_CODE = 100;

    SmoothBottomBar BottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inisialisasi SharedPrefe rences
        this.sharedPrefs = this.getSharedPreferences("kaloriapp", Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recycler_bmi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BmiAdapter(this);

        bmiViewModel = ViewModelProviders.of(this).get(BmiViewModel.class);
        recyclerView.setAdapter(adapter);
        bmiViewModel.getAllBmi().observe(this, new Observer<List<BmiEntity>>() {

            @Override
            public void onChanged(List<BmiEntity> bmiEntities) {
                adapter.setBMI(bmiEntities);
            }
        });

        BottomBar = findViewById(R.id.BottomBar);
        BottomBar.setOnItemSelectedListener(i -> {
            switch (i){
                case 0:
                    break;
                case 1:
                    Intent add = new Intent(this, NewBmiActivity.class);
                    startActivityForResult(add, REQUEST_CODE);
                    break;
                case 2:
                    Intent account = new Intent(this, AccountActivity.class);
                    startActivity(account);
                    break;
            }
            return false;
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            BmiEntity bmiEntity = new BmiEntity();
            bmiEntity.setBmiResult(Float.valueOf(data.getStringExtra(NewBmiActivity.EXTRA_REPLY)));
            bmiViewModel.insertBmi(bmiEntity);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Data tidak disimpan",
                    Toast.LENGTH_LONG).show();
        }
    }

}