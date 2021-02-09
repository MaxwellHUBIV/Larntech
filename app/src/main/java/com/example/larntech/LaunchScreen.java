package com.example.larntech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        try {
            new Handler().postDelayed(new Runnable() {
                public void run(){
                    Intent intent = new Intent(LaunchScreen.this, MainActivity.class);
                    startActivity(intent);
                   }  }, 2000);
            } catch(Exception e){}

        }


}