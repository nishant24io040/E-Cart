package com.example.e_cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView eye;


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
        eye=findViewById(R.id.imageView3);
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

        eye.setImageResource(R.drawable.eyeoff);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // if password is visble then unvisibale it.
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.eyeoff);
                }
                else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.baseline_remove_red_eye_24);
                }
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