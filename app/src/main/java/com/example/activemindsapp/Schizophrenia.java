package com.example.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Schizophrenia extends AppCompatActivity {

    private ImageButton backButton;
    private Button HelpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schizophrenia);
        HelpButton= findViewById(R.id.findHelp);
        backButton = findViewById(R.id.backButton1);
        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schizophrenia.this,
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
