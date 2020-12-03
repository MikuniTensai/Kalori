package com.example.kalori.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kalori.ProfileActivity;
import com.example.kalori.R;
import com.example.kalori.WelcomeBack;
import com.example.kalori.ui.HomeActivity;
import com.example.kalori.ui.NewBmiActivity;

import me.ibrahimsn.lib.SmoothBottomBar;

public class AccountActivity extends AppCompatActivity {

    private static final String KEEP_LOGIN_KEY = "key_keep_login";

    private SharedPreferences sharedPrefs;

    private User currentUser;
    private TextView Account;
    SmoothBottomBar BottomBar;
    public static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        account();

        BottomBar = findViewById(R.id.BottomBar);
        BottomBar.setOnItemSelectedListener(i -> {
            switch (i){
                case 0:
                    Intent home = new Intent(this, HomeActivity.class);
                    startActivity(home);
                    break;
                case 1:
                    Intent add = new Intent(this, NewBmiActivity.class);
                    startActivityForResult(add, REQUEST_CODE);
                    break;
                case 2:
                    break;
            }
            return false;
        });
    }

    public void account(){
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();
        this.currentUser = daoUser.selectOne();
        this.Account = findViewById(R.id.txv_account);
        this.Account.setText(this.currentUser.username);
    }

    public void onBtnShowProfile_Click(View view) {
        Intent intent = new Intent(AccountActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onBtnLogout_Click(View view) {
        Intent intent = new Intent(AccountActivity.this, WelcomeBack.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}