package com.example.sismola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DaftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
    }

    public void LOGIN (View view) {
        Intent LOGIN = new Intent(DaftarActivity.this, LoginActivity.class);
        startActivity(LOGIN);
    }
}
