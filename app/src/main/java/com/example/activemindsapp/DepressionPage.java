package com.example.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DepressionPage extends AppCompatActivity {
    private ImageButton backButton;
    Button majorDepression;
    Button SAD;
    Button BipolarDepression;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_page);
        backButton = findViewById(R.id.backButton1);
        majorDepression= findViewById(R.id.majorDepression);
        SAD = findViewById(R.id.SAD);
        BipolarDepression = findViewById(R.id.mania);

        majorDepression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepressionPage.this,
                        MajorDepression.class);
                startActivity(intent);
            }
        });

        SAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepressionPage.this,
                        SeasonalAD.class);
                startActivity(intent);
            }
        });

        BipolarDepression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepressionPage.this,
                        BipolarDepressionMania.class);
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
