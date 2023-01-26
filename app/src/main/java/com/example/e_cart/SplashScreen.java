package com.example.e_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    Uri appLinkData;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Window window = SplashScreen.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(SplashScreen.this, R.color.Main));
        imageView = findViewById(R.id.iv);
        Glide.with(this).load(R.drawable.splash_drawable).fitCenter().into(imageView);


        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();

        if(appLinkAction!=null) {
            appLinkData = appLinkIntent.getData();
        }
        if(appLinkData!=null)
        {
            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(intent);
            finish();
            finish();
            return;
        }else
        {

            handler = new Handler();
            handler.postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                },
                    1500);

        }
    }
}