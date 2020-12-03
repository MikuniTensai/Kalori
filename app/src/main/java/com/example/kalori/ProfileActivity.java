package com.example.kalori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kalori.data.AppDbProvider;
import com.example.kalori.data.User;
import com.example.kalori.data.UserDao;

public class ProfileActivity extends AppCompatActivity {

    private User currentUser;

    private EditText edtUsername, edtPassword, edtEmail, edtPhoneNumber;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.loadData();
        this.initComponents();
    }

    private void loadData() {
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();

        this.currentUser = daoUser.selectOne();
    }

    private void initComponents() {
        this.edtUsername = this.findViewById(R.id.edt_username);
        this.edtPassword = this.findViewById(R.id.edt_password);
        this.edtEmail = this.findViewById(R.id.edt_email);
        this.edtPhoneNumber = this.findViewById(R.id.edt_phone_number);
        this.btnSave = this.findViewById(R.id.btn_save);

        if(this.currentUser == null) {
            this.btnSave.setEnabled(false);
            return;
        }

        this.edtUsername.setText(this.currentUser.username);
        this.edtPassword.setText(this.currentUser.password);
        this.edtEmail.setText(this.currentUser.email);
        this.edtPhoneNumber.setText(this.currentUser.phoneNumber);
    }

    private void syncData() {
        this.currentUser.password = this.edtPassword.getText().toString();
        this.currentUser.email = this.edtEmail.getText().toString();
        this.currentUser.phoneNumber = this.edtPhoneNumber.getText().toString();
    }

    public void onBtnSave_Click(View view) {
        this.syncData();
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();
        daoUser.update(this.currentUser);
        Toast.makeText(this, "Your data has been updated!", Toast.LENGTH_SHORT).show();
    }

    public void onTxvDeleteAccount_Click(View view) {
        this.syncData();
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();
        daoUser.delete(this.currentUser);

        Toast.makeText(this, "Your data has been deleted..", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), WelcomeBack.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        this.finish();
    }

}