package com.example.sismola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LOGIN (View view){
        Intent LOGIN = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(LOGIN);
    }

    public void DAFTAR (View view){
        Intent DAFTAR = new Intent(LoginActivity.this, DaftarActivity.class);
        startActivity(DAFTAR);
    }
}
