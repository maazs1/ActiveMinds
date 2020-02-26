package com.activeminds.activemindsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private SharedPreferences sp;
    private TextView profileName;
    private TextView followers;
    private TextView following;
    private TextView posts;
    private ArrayList<follow> followersList;
    private ArrayList<follow> followingList;
    private ArrayList<String> postList;
    private String name;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        name = intent.getStringExtra("acc");
        sp = getSharedPreferences("login",MODE_PRIVATE);
        profileName = findViewById(R.id.profileName);
        profileName.setText(name);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        followersList = new ArrayList<>();
        followingList = new ArrayList<>();
        collectionReference= db.collection("Users Account").document(name).collection("Following");
        posts= findViewById(R.id.postsNumber);
        postList = new ArrayList<>();

        followersList.clear();
        followingList.clear();
        postList.clear();

        countPosts();
        countFollowers();
        countFollowing();
        showFollowers();
        showFollowing();

    }

    private void countPosts() {
        db.collection("Users Account").document(name).collection("MoodActivities")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        postList.clear();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            String idDoc = queryDocumentSnapshot.getId();
                            postList.add(idDoc);
                        }
                        int sizeOf = postList.size();
                        posts.append(String.valueOf(sizeOf));
                    }
                });
    }

    private void showFollowing() {
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionReference
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                followingList.clear();
                                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                                    String name = queryDocumentSnapshot.getId();
                                    followingList.add(new follow(name));
                                }
                                Intent intent = new Intent(Profile.this, newFollowing.class);

                                intent.putExtra("list", (Serializable)followingList);
                                startActivity(intent);

                            }
                        });
            }
        });

    }

    public void countFollowers(){
        db.collection(name)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        followersList.clear();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            String name = queryDocumentSnapshot.getId();
                            followersList.add(new follow(name));
                        }
                        int sizeOf = followersList.size();
                        followers.append(String.valueOf(sizeOf));
                        followers.append(" ");
                    }
                });
    }

    public void countFollowing(){
        collectionReference
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        followingList.clear();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            String name = queryDocumentSnapshot.getId();
                            followingList.add(new follow(name));
                        }
                        int sizeOf = followingList.size();
                        following.append(String.valueOf(sizeOf));
                        following.append(" ");
                    }
                });

    }

    private void showFollowers(){

        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection(name)
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                followersList.clear();
                                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                                    String name = queryDocumentSnapshot.getId();
                                    followersList.add(new follow(name));
                                }
                                Intent intent = new Intent(Profile.this, newFollowPage.class);

                                intent.putExtra("list", (Serializable)followersList);
                                startActivity(intent);
                            }
                        });
            }
        });
    }


    /**
     * takes the user back to where they came from
     */
    public void backToFeed(View view) {
        finish();
    }


    /**
     * logs out the user and sends the user back to mainactivity
     */
    public void logout(View view) {

        finishAffinity();
        Intent intent = new Intent(getApplicationContext(), LoginPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        sp.edit().putBoolean("logged",false).apply();
        startActivity(intent);

    }
}
