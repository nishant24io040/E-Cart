package com.example.e_cart;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HomeFrag extends Fragment {
    ImageView iv1,iv2,iv3,iv4,iv5,iv6;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        iv1 = view.findViewById(R.id.iv1);
        iv2 = view.findViewById(R.id.iv2);
        iv3 = view.findViewById(R.id.iv3);
        iv4 = view.findViewById(R.id.iv4);
        iv5 = view.findViewById(R.id.iv5);
        iv6 = view.findViewById(R.id.iv6);

        iv1.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(),ShopDetails.class);
            intent.putExtra("1st",1);
            startActivity(intent);
        });
        iv2.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(),ShopDetails.class);
            intent.putExtra("1st",2);
            startActivity(intent);
        });
        iv3.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(),ShopDetails.class);
            intent.putExtra("1st",3);
            startActivity(intent);
        });
        iv4.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(),ShopDetails.class);
            intent.putExtra("1st",4);
            startActivity(intent);
        });
        iv5.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(),ShopDetails.class);
            intent.putExtra("1st",5);
            startActivity(intent);
        });
        iv6.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(),ShopDetails.class);
            intent.putExtra("1st",6);
            startActivity(intent);
        });


        return view;
    }
}