package com.activeminds.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class HubPage extends AppCompatActivity {

    //Declare variables for later use
    private ViewPager moodRoster;
    private SwipeMoodsAdapter moodRosterAdapter;
    private List<Emoticon> moodImages;

    //for UX
    private ViewFlipper viewFlipper;
    private ImageView chosenEmoticon;
    private ImageView imageUpload;
    protected MoodEvent moodEvent;
    private TextView moodIndicator;
    private Button selectEmoticonBtn;
    private ImageButton backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_page);

        viewFlipper = findViewById(R.id.view_flipper);
        //chosenEmoticon = findViewById(R.id.chosen_emoticon);
        moodEvent = new MoodEvent();
        moodIndicator = findViewById(R.id.emotion_indicator);
        backButton = findViewById(R.id.backButton1);


        createMoodRoster();
        swipeMoodAdapterSetup();
        customSwipeMoodStyling();
        emoticonSelectBtnListener();
        moodSelection();
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

    public void previousView(View view) {
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.showPrevious();
    }

    public void nextView(View view) {
        viewFlipper.setInAnimation(this, R.anim.slide_in_right);
        viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
        viewFlipper.showNext();
    }

    private void chosenEmoticonListener(){
        chosenEmoticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousView(view);
            }
        });
    }

    private void createMoodRoster() {
        moodImages = new ArrayList<>();
        moodImages.add(new Emoticon("ANXIETY", 3));
        moodImages.add(new Emoticon("DEPRESSION", 3));
        moodImages.add(new Emoticon("STRESS", 3));
        moodImages.add(new Emoticon("SCHIZOPHRENIA", 3));
        moodImages.add(new Emoticon("EATING DISORDERS", 3));
        moodImages.add(new Emoticon("GENDER AND SEXUAL DIVERSITY (LGBTQ2+)", 3));
    }

    /**
     * This is the setup for the adapter that contains all the emoticons
     **/
    private void swipeMoodAdapterSetup() {
        moodRosterAdapter = new SwipeMoodsAdapter(moodImages, this);
        moodRoster = findViewById(R.id.mood_roster);
        moodRoster.setAdapter(moodRosterAdapter);
    }

    /**
     * This just customs the mood roster so the next and previous emoticon can be seen partially
     **/
    private void customSwipeMoodStyling() {
        moodRoster.setClipToPadding(false);
        moodRoster.setPadding(150, 0, 150, 0);
        //imageUpload.setAdjustViewBounds(true);
    }

    private void moodSelection() {
        moodEvent.setEmotionalState("ANXIETY");//default

        //click listener for Emoticon
        moodRoster.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //no need to use but must be here
            }

            @Override
            public void onPageSelected(int position) {
                String emotion = moodImages.get(position).getEmotionalState();
                moodEvent.setEmotionalState(emotion); //for the object to be created
                moodIndicator.setText(emotion); //for the indicator in xml

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //no need to use but must be here
            }
        });

    }
    private void emoticonSelectBtnListener() {
        selectEmoticonBtn = findViewById(R.id.select_emoticon_btn);
        selectEmoticonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("moodtag", moodEvent.getEmotionalState());

                if (moodEvent.getEmotionalState()=="ANXIETY"){
                    Intent intent = new Intent(HubPage.this, AnxietyPage.class);
                    startActivity(intent);
                }

                if (moodEvent.getEmotionalState()=="DEPRESSION"){
                    Intent intent = new Intent(HubPage.this, DepressionPage.class);
                    startActivity(intent);
                }

                if (moodEvent.getEmotionalState()=="STRESS"){
                    Intent intent = new Intent(HubPage.this, StressPage.class);
                    startActivity(intent);
                }
                if (moodEvent.getEmotionalState()=="SCHIZOPHRENIA"){
                    Intent intent = new Intent(HubPage.this, Schizophrenia.class);
                    startActivity(intent);
                }
                if (moodEvent.getEmotionalState()=="GENDER AND SEXUAL DIVERSITY (LGBTQ2+)"){
                    Intent intent = new Intent(HubPage.this, IssuesPage.class);
                    startActivity(intent);
                }
                if (moodEvent.getEmotionalState()=="EATING DISORDERS"){
                    Intent intent = new Intent(HubPage.this, EatingDisorder.class);
                    startActivity(intent);
                }

            }
        });
    }


}
