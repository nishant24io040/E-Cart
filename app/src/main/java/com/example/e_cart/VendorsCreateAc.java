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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class VendorsCreateAc extends AppCompatActivity {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText email,phone,password,confirmpassword;
    Button btnsignup;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors_create);

        tv1 = findViewById(R.id.tv);
        email = findViewById(R.id.email);

        phone = findViewById(R.id.phone);
        password = findViewById(R.id.pasword);
        confirmpassword = findViewById(R.id.pasword2);
        btnsignup = findViewById(R.id.signup);

        btnsignup.setOnClickListener(v -> {
            if (TextUtils.isEmpty(email.getText())){
                email.setError("Email cannot be empty");
                email.requestFocus();
            }else if (TextUtils.isEmpty(password.getText())){
                password.setError("Password cannot be empty");
                password.requestFocus();
            }
            else if (password.getText()== confirmpassword.getText()) {
                confirmpassword.setError("Password is not matching");
                confirmpassword.requestFocus();
            } else {
                signup();
            }
        });


    }
    private void signup() {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            Toast.makeText(VendorsCreateAc.this, "signup successfully", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference("Accounts").child(mAuth.getCurrentUser().getUid()).setValue("Vendor")
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(VendorsCreateAc.this, "done", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            startActivity(new Intent(VendorsCreateAc.this,VendorsPostSignup.class));
                        }
                        else {
                            Toast.makeText(VendorsCreateAc.this, "signup error", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}