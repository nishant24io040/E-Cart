package com.example.e_cart;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class VendorsPostSignup extends AppCompatActivity {
    EditText email,phone,create_password,confirmed_password,description;
    Button signup;
    ImageView cre_pwd_eye_img,con_pwd_eye_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors_post_signup);

        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        create_password=findViewById(R.id.pasword);
        confirmed_password=findViewById(R.id.pasword2);
        description=findViewById(R.id.editTextTextPersonName5);
        signup=findViewById(R.id.signup);
        cre_pwd_eye_img=findViewById(R.id.imageView12);
        con_pwd_eye_img=findViewById(R.id.imageView11);

        cre_pwd_eye_img.setImageResource(R.drawable.eyeoff);
        cre_pwd_eye_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(create_password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // if password visible then unvisible it..
                    create_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cre_pwd_eye_img.setImageResource(R.drawable.eyeoff);
                }
                else{
                    create_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cre_pwd_eye_img.setImageResource(R.drawable.baseline_remove_red_eye_24);
                }
            }
        });

        con_pwd_eye_img.setImageResource(R.drawable.eyeoff);
        con_pwd_eye_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmed_password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // if password visible then unvisible it..
                    confirmed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    con_pwd_eye_img.setImageResource(R.drawable.eyeoff);
                }
                else{
                    confirmed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    con_pwd_eye_img.setImageResource(R.drawable.baseline_remove_red_eye_24);
                }
            }
        });
        
    }
}