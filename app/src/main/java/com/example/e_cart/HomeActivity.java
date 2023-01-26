package com.example.e_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNV = findViewById(R.id.bottomNavigationView);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcons,new HomeFrag());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
        bottomNV.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.hme :
                    replaceFragment(new HomeFrag());
                    break;
                case R.id.create:

                    break;
                case R.id.offer:
                    replaceFragment(new OfferFrag());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFrag());
                    break;
            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcons,fragment);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }
}