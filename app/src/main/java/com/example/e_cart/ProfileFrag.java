package com.example.e_cart;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFrag extends Fragment {


    TextView wallet;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profilefrag, container, false);
        wallet = view.findViewById(R.id.walt);

        wallet.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),MyWallet.class);
            startActivity(intent);
        });
        return view;
    }
}