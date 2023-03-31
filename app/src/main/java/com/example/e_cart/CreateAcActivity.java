package com.example.e_cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class CreateAcActivity extends AppCompatActivity {
    TextView tv1;
    Button btnsignup;
    GoogleSignInClient mGoogleSignInClient;
    ImageView googlelogin;
    EditText email,phone,password,confirmpassword;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ac);
        tv1 = findViewById(R.id.tv);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.pasword);
        confirmpassword = findViewById(R.id.pasword2);
        googlelogin = findViewById(R.id.google);
        btnsignup = findViewById(R.id.signup);
        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }

        tv1.setOnClickListener(view -> {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        googlelogin.setOnClickListener(v -> {

            signIn();
        });
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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
                    Toast.makeText(CreateAcActivity.this, "signup successfully", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference("Accounts").child(mAuth.getCurrentUser().getUid()).setValue("User");
                    startActivity(new Intent(CreateAcActivity.this,LoginActivity.class));
                }
                else {
                    Toast.makeText(CreateAcActivity.this, "signup error", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    int RC_SIGN_IN = 65;
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Error in catch"+task.getException(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();

                        Intent intent = new Intent(CreateAcActivity.this , HomeActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "error in else", Toast.LENGTH_SHORT).show();
                    }
                });
    }



}