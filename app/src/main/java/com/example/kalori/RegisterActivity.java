package com.example.kalori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.kalori.data.AppDbProvider;
import com.example.kalori.data.DatabaseTask;
import com.example.kalori.data.DatabaseTaskEventListener;
import com.example.kalori.data.KaloriDatabase;
import com.example.kalori.data.User;
import com.example.kalori.data.UserDao;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextNama, editTextEmail, editTextPassword, editTextPhoneNumber;

    ProgressDialog loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.initComponents();
    }

    private void initComponents(){
        editTextNama = findViewById(R.id.edt_txt_nama);
        editTextEmail = findViewById(R.id.edt_txt_email);
        editTextPassword = findViewById(R.id.edt_txt_password);
        editTextPhoneNumber = findViewById(R.id.edt_txt_telp);
    }

    public void postSignUp_Old(View view){
        UserDao daoUser = AppDbProvider.getInstance(this.getApplicationContext()).userDao();
        daoUser.insertAll(this.makeUser());
        Toast.makeText(this, "Register berhasil!", Toast.LENGTH_SHORT).show();
        this.finish();
        Intent intent = new Intent(this, WelcomeBack.class);
        startActivity(intent);
    }

    public void postSignUp(View view) {
        boolean nama = TextUtils.isEmpty(editTextNama.getText().toString().trim());
        boolean email = TextUtils.isEmpty(editTextEmail.getText().toString().trim());
        boolean password = TextUtils.isEmpty(editTextPassword.getText().toString().trim());
        boolean phone = TextUtils.isEmpty(editTextPhoneNumber.getText().toString().trim());

        if (nama && email && password && phone){
            toastPostLogin(view, "Data tidak boleh kosong");
        } else if (nama) {
            toastPostLogin(view, "Nama tidak boleh kosong");
        } else if (email) {
            toastPostLogin(view, "Email tidak boleh kosong");
        } else if (phone) {
            toastPostLogin(view, "Phone tidak boleh kosong");
        } else {
            this.showLoadingIndicator();

            new DatabaseTask(this, new DatabaseTaskEventListener() {

                @Override
                public Object runDatabaseOperation(RoomDatabase database, Object... params) {
                    User user = (User) params[0];

                    ((KaloriDatabase)database).userDao().insertAll(user);

                    return null;
                }

                @Override
                public void onDatabaseOperationFinished(Object... results) {
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            loadingIndicator.dismiss();
                            Toast.makeText(getApplicationContext(), "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                        }
                    }, 5000);
                }
            }).execute(this.makeUser());
            Intent intent = new Intent(this, WelcomeBack.class);
            startActivity(intent);
        }
    }

    private User makeUser() {
        User u = new User();
        u.username = this.editTextNama.getText().toString();
        u.password = this.editTextPassword.getText().toString();
        u.email = this.editTextEmail.getText().toString();
        u.phoneNumber = this.editTextPhoneNumber.getText().toString();

        return u;
    }

    private void showLoadingIndicator() {
        loadingIndicator = new ProgressDialog(this);
        loadingIndicator.setMessage("Sedang melakukan verifikasi data...");
        loadingIndicator.setIndeterminate(false);
        loadingIndicator.setCancelable(false);
        loadingIndicator.show();
    }

    public void toastPostLogin(View view, String string){
        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();
    }

    public void clickSignUp(View view) {
        Intent intent = new Intent(this, WelcomeBack.class);
        startActivity(intent);
    }
}