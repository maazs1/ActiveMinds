package com.example.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class newFollowing extends AppCompatActivity {
    private ListView listView;
    private ArrayList<follow> followingDataList;
    private ArrayAdapter<follow> Adapter;
    TextView textView;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_followers);
        textView = findViewById(R.id.titleNew);
        textView.setText("Following");
        Intent intent = getIntent();
        followingDataList = (ArrayList<follow>) intent.getSerializableExtra("list");
        if (followingDataList.size() == 0){
            Toast.makeText(newFollowing.this,"You are not following anyone!", Toast.LENGTH_LONG).show();
        }
        listView = findViewById(R.id.list_view);
        Adapter = new followAdapter(followingDataList, this);
        listView.setAdapter(Adapter);

        backButton();
    }

    private void backButton() {
        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

