package com.activeminds.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DepressionPage extends AppCompatActivity {
    private ImageView image;
    private ImageButton backButton;
    Button majorDepression;
    Button SAD;
    Button BipolarDepression;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_page);

        image = findViewById(R.id.emotionEmoticon);
        backButton = findViewById(R.id.backButton1);
        majorDepression= findViewById(R.id.majorDepression);
        SAD = findViewById(R.id.SAD);
        BipolarDepression = findViewById(R.id.mania);

        image.setImageResource(new Emoticon("DEPRESSION", 3).getImageLink());

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
