package com.example.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp;
                sp = getSharedPreferences("login",MODE_PRIVATE);
                if(sp.getBoolean("logged",false)){
                    Intent intent = new Intent(getApplicationContext(), UserPage.class);
                    intent.putExtra("FROM_ACTIVITY", "main");
                    startActivity(intent);
                }
                else{
                    Intent mainIntent = new Intent(MainActivity.this, LoginPage.class);
                    MainActivity.this.startActivity(mainIntent);
                    MainActivity.this.finish();
                }


            }
        }, SPLASH_DISPLAY_LENGTH);
    }


}

