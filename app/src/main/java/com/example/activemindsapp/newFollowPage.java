package com.example.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class newFollowPage extends AppCompatActivity {
    private ListView listView;
    private ArrayList<follow> followerDataList;
    private ArrayAdapter<follow> Adapter;
    TextView textView;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_followers);
        textView = findViewById(R.id.titleNew);
        textView.setText("Followers");
        Intent intent = getIntent();
       /* Bundle bundle = getIntent().getExtras();
        followerDataList = bundle.getParcelable("list");*/
        followerDataList = (ArrayList<follow>) intent.getSerializableExtra("list");
        if (followerDataList.size() == 0){
            Toast.makeText(newFollowPage.this,"You have no followers!", Toast.LENGTH_LONG).show();
        }
        listView = findViewById(R.id.list_view);
        Adapter = new followAdapter(followerDataList, this);
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
