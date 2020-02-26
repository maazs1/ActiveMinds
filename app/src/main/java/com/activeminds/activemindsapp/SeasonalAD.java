package com.activeminds.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SeasonalAD extends AppCompatActivity {
    private Button HelpButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasonal_ad);

        HelpButton= findViewById(R.id.findHelp);
        backButton = findViewById(R.id.backButton1);
        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeasonalAD.this,
                        HelpLocations.class);
                startActivity(intent);
            }
        });
        goBack();
    }



    public void goBack(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
