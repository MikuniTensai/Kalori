package com.example.kalori;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kalori.data.AppDbProvider;
import com.example.kalori.data.User;
import com.example.kalori.data.UserDao;

import java.util.Objects;

public class WelcomeBack extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private CheckBox chkRememberUsername, chkKeepLogin;

    private SharedPreferences sharedPrefs;

    private static final String USERNAME_KEY = "key_username";
    private static final String KEEP_LOGIN_KEY = "key_keep_login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_back);
        // Inisialisasi SharedPreferences
        this.sharedPrefs = this.getSharedPreferences("kaloriapp", Context.MODE_PRIVATE);
        this.initComponents();
        this.autoLogin();
        this.loadSavedUsername();
    }

    private void initComponents() {
        // Init components
        this.edtUsername = this.findViewById(R.id.edt_txt_email);
        this.edtPassword = this.findViewById(R.id.edt_txt_password);
        this.chkRememberUsername = this.findViewById(R.id.chk_remember_username);
        this.chkKeepLogin = this.findViewById(R.id.chk_keep_login);
    }

    public void clickForgot(View view) {
        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
    }

    public void postLogin(View view) {
        boolean valid = this.validateCredential();
        if (valid) {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);

            this.saveUsername();
            this.makeAutoLogin();
        } else if (TextUtils.isEmpty(edtUsername.getText().toString().trim()) && TextUtils.isEmpty(edtPassword.getText().toString().trim())) {
            toastPostLogin(view, "Email dan Password tidak boleh kosong!");
        } else if (TextUtils.isEmpty(edtUsername.getText().toString().trim())) {
            toastPostLogin(view, "Email tidak valid!");
        } else if (TextUtils.isEmpty(edtPassword.getText().toString().trim())) {
            toastPostLogin(view, "Password tidak valid!");
        } else {
            Toast.makeText(this, "Username atau password salah", Toast.LENGTH_LONG).show();
        }
    }

    public void clickSignUp(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private boolean validateCredential() {
        String currentUsername = this.edtUsername.getText().toString();
        String currentPassword = this.edtPassword.getText().toString();
        /*
        return (Objects.equals(currentUsername, DUMMY_USERNAME)
                && Objects.equals(currentPassword, DUMMY_PASSWORD));
        */
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();
        User existingUser = daoUser.findByUsernameAndPassword(currentUsername, currentPassword);

        return existingUser != null;
    }

    private void saveUsername() {
        SharedPreferences.Editor editor = this.sharedPrefs.edit();
        if(this.chkRememberUsername.isChecked())
            editor.putString(USERNAME_KEY, this.edtUsername.getText().toString());
        else
            editor.remove(USERNAME_KEY);

        editor.apply();
    }

    private void loadSavedUsername() {
        String savedUsername = this.sharedPrefs.getString(USERNAME_KEY, null);
        if(savedUsername != null)
        {
            this.edtUsername.setText(savedUsername);

            this.chkRememberUsername.setChecked(true);
        }
    }

    private void makeAutoLogin() {
        SharedPreferences.Editor editor = this.sharedPrefs.edit();
        if(this.chkKeepLogin.isChecked())
            editor.putBoolean(KEEP_LOGIN_KEY, true);
        else
            editor.remove(KEEP_LOGIN_KEY);
        editor.apply();
    }

    private void autoLogin() {
        boolean keepLogin = this.sharedPrefs.getBoolean(KEEP_LOGIN_KEY, false);
        if(keepLogin) {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);

            this.chkKeepLogin.setChecked(true);
        }
    }

    public void toastPostLogin(View view, String string){
        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}