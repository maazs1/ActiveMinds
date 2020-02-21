package com.example.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AnxietyPage extends AppCompatActivity {
    private ImageButton backButton;
    private Button panicAttack;
    private Button generalizedAnxiety;
    private Button socialAnxiety;
    private Button OCD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_page);
        generalizedAnxiety = findViewById(R.id.generalized);
        panicAttack = findViewById(R.id.panic);
        socialAnxiety= findViewById(R.id.social_anxiety);
        OCD = findViewById(R.id.OCD);
        backButton= findViewById(R.id.backButton1);

        panicAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnxietyPage.this,
                        PanicAttack.class);
                startActivity(intent);
            }
        });

        generalizedAnxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnxietyPage.this,
                        GeneralAnxiety.class);
                startActivity(intent);
            }
        });

        socialAnxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnxietyPage.this,
                        SocialAnxiety.class);
                startActivity(intent);
            }
        });

        OCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnxietyPage.this,
                        ObsessiveCompulsive.class);
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
