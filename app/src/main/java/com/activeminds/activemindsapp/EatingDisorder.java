package com.activeminds.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EatingDisorder extends AppCompatActivity {

    private ImageButton backButton;
    private Button Anorexia;
    private Button Bulimia;
    private Button Binge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating_disorder);

        backButton = findViewById(R.id.backButton1);
        Anorexia = findViewById(R.id.anorexia);
        Bulimia = findViewById(R.id.Bulimia);
        Binge = findViewById(R.id.binge);

        goBack();
        goAnorexia();
        goBulimia();
        goBinge();
    }

    public void goBack(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void goBinge(){
        Binge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EatingDisorder.this,
                        Binge.class);
                startActivity(intent);
            }
        });
    }
    public void goBulimia(){
        Bulimia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EatingDisorder.this,
                        Bulimia.class);
                startActivity(intent);
            }
        });
    }

    public void goAnorexia(){
        Anorexia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EatingDisorder.this,
                        Anorexia.class);
                startActivity(intent);
            }
        });
    }
}
