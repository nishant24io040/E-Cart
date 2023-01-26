package com.example.e_cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,CreateAcActivity.class);
            startActivity(intent);
        });

    }
}