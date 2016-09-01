package com.example.montoya.focalpoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {
    ImageView wholeLogo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_about);

        wholeLogo=(ImageView)findViewById(R.id.whole_logo);
        wholeLogo.setImageResource(R.drawable.whole_logo);



    }


}
