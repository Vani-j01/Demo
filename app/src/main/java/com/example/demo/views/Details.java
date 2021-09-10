package com.example.demo.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    String t;
    TextView title, price, quantity;
    private ImageView imageView;
    private ImageButton back, share;
    private Button addtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title= findViewById(R.id.details_title);
        quantity= findViewById(R.id.details_quantity);
        price= findViewById(R.id.details_price);
        imageView= findViewById(R.id.details_image);
        back= findViewById(R.id.back);
        share= findViewById(R.id.share);
        addtocart= findViewById(R.id.addtocart);

        Intent intent = getIntent();
        t= intent.getStringExtra("title");
        title.setText(intent.getStringExtra("title"));
        quantity.setText(intent.getStringExtra("quantity"));
        price.setText("$"+intent.getStringExtra("price"));
        Picasso.get().load(intent.getStringExtra("image")).into(imageView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Share();
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
            }
        });

    }

    private void Add() {
        Toast.makeText(getApplicationContext(), "Item Added to cart!", Toast.LENGTH_LONG).show();
    }

    private void Share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "I am sharing this"+ t+"With you");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private void Back() {
        onBackPressed();
    }
}