package com.example.e_cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class VendorLogin extends AppCompatActivity {
    TextView createacount,user;
    FirebaseAuth mAuth;
    EditText email,password;
    Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);
        mAuth = FirebaseAuth.getInstance();
        createacount = findViewById(R.id.tv1);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.pasword1);
        btnlogin = findViewById(R.id.btnlogin);
        user = findViewById(R.id.user);
        user.setOnClickListener(v -> {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        });
        createacount.setOnClickListener(v -> {
            Intent intent = new Intent(this,VendorsCreateAc.class);
            startActivity(intent);
        });
        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(this,VendorsPostSignup.class);
            startActivity(intent);
        }
        btnlogin.setOnClickListener(v -> {
            if (TextUtils.isEmpty(email.getText())){
                email.setError("Email cannot be empty");
                email.requestFocus();
            }else if (TextUtils.isEmpty(password.getText())) {
                password.setError("Password cannot be empty");
                password.requestFocus();
            }else {
                login();
            }

        });

    }
    private void login() {
        mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(VendorLogin.this, "login successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(VendorLogin.this , VendorsPostSignup.class));
                        }
                        else {
                            Toast.makeText(VendorLogin.this, "login error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}