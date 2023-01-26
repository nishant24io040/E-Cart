package com.example.e_cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CreateAcActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ac);
        tv1 = findViewById(R.id.tv);
        tv1.setOnClickListener(view -> {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        });
    }
}