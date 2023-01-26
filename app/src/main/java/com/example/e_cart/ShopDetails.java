package com.example.e_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ShopDetails extends AppCompatActivity {
    Bundle bundle;
    ImageView ivd;
    TextView tvd;
    Button btn1;
    ConstraintLayout cnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        ivd = findViewById(R.id.ivd);
        tvd = findViewById(R.id.tvd);
        cnd = findViewById(R.id.cons);
        btn1 = findViewById(R.id.btn4);
        bundle = getIntent().getExtras();
        int i =bundle.getInt("1st");
        String s = Integer.toString(i);
        switch(s){
            case "1" :
                tvd.setText("Sai Kirana");
                break;
            case "2":
                cnd.setBackgroundColor(getResources().getColor(R.color.M1));
                Glide.with(this).load(R.drawable.shop2).fitCenter().into(ivd);
                btn1.setBackground(getDrawable(R.drawable.dbtnbg2));
                tvd.setText("VIP Bags");
                break;
            case "3" :
                cnd.setBackgroundColor(getResources().getColor(R.color.M2));
                Glide.with(this).load(R.drawable.shop3).fitCenter().into(ivd);
                btn1.setBackground(getDrawable(R.drawable.dbtnbg3));
                tvd.setText("Shiva Electronics");
                break;
            case "4" :
                cnd.setBackgroundColor(getResources().getColor(R.color.M3));
                Glide.with(this).load(R.drawable.shop4).fitCenter().into(ivd);
                btn1.setBackground(getDrawable(R.drawable.dbtnbg4));
                tvd.setText("Mangalam");
                break;
            case "5" :
                cnd.setBackgroundColor(getResources().getColor(R.color.M4));
                Glide.with(this).load(R.drawable.shop5).fitCenter().into(ivd);
                btn1.setBackground(getDrawable(R.drawable.dbtnbg5));
                tvd.setText("Mr. Bean Cafe");
                break;
            case "6" :
                cnd.setBackgroundColor(getResources().getColor(R.color.M5));
                Glide.with(this).load(R.drawable.shop6).fitCenter().into(ivd);
                btn1.setBackground(getDrawable(R.drawable.dbtnbg6));
                tvd.setText("Lovely Gifts");
                break;

        }

    }
}