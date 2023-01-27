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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    TextView tv1;
    EditText email,password;
    Button btnlogin;
    ImageView img;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv1 = findViewById(R.id.tv1);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.pasword1);
        btnlogin = findViewById(R.id.btnlogin);
        img = findViewById(R.id.googlelogin);
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }
        tv1.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,CreateAcActivity.class);
            startActivity(intent);
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("280582128058-o0p2nlqjh4r1n81l5vk7cp156vo7u9bk.apps.googleusercontent.com")
                .requestEmail()
                .build();
        img.setOnClickListener(v -> {
            signIn();
        });
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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
                            Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "login error", Toast.LENGTH_SHORT).show();
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

                        Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "error in else", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}