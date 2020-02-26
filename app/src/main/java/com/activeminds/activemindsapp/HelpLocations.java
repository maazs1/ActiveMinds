package com.activeminds.activemindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HelpLocations extends AppCompatActivity {
    private ImageButton backButton;
    private Button AccessOpenMindsEdmonton;
    private Button CornerstoneCounsellingCentre;
    private Button MomentumWalkInCounselling;
    private Button CommunityCounsellingCentre;
    private Button DropInYEG;
    private Button THEFAMILYCENTRE;
    private Button UniversityofAlbertaFacultyofEducationClinicalServices;
    private Button PrideCentreofEdmonton;
    private Button SexualAssaultCentreofEdmonton;
    private Button YWCAEdmonton;
    private Button IslamicFamilyServices;
    private Button CounsellingClinicalServices;
    private Button EatingDisorderSupportNetworkofAlberta;
    private Button SchizophreniaofAlberta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_locations);
        MomentumWalkInCounselling = findViewById(R.id.MWIC);
        AccessOpenMindsEdmonton = findViewById(R.id.AOME);
        backButton = findViewById(R.id.backButton1);
        CornerstoneCounsellingCentre = findViewById(R.id.CCC);
        CommunityCounsellingCentre = findViewById(R.id.CCC2);
        DropInYEG = findViewById(R.id.DIY);
        THEFAMILYCENTRE = findViewById(R.id.TFC);
        UniversityofAlbertaFacultyofEducationClinicalServices = findViewById(R.id. UOAFOECS);
        PrideCentreofEdmonton = findViewById(R.id.PCOE);
        SexualAssaultCentreofEdmonton = findViewById(R.id.SACOE);
        YWCAEdmonton = findViewById(R.id.YE);
        IslamicFamilyServices = findViewById(R.id.IFS);
        CounsellingClinicalServices = findViewById(R.id.CACS);
        EatingDisorderSupportNetworkofAlberta = findViewById(R.id.EASNOA);
        SchizophreniaofAlberta = findViewById(R.id.SOA);


        goUOAFOECS();
        goTFC();
        goCCE();
        goBack();
        goAOME();
        goMWIC();
        goCCC();
        goDIY();
        goPCOE();
        goSACOE();
        goYE();
        goIFS();
        goCACS();
        goEASNOA();
        goSOA();

    }

    public void goSOA(){
        SchizophreniaofAlberta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, SchizophreniaofAlberta.class);
                startActivity(intent);
            }
        });
    }
    public void goEASNOA() {
        EatingDisorderSupportNetworkofAlberta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, EatingDisorderSupportNetworkofAlberta.class);
                startActivity(intent);
            }
        });
    }

    public void goCACS(){
        CounsellingClinicalServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, CounsellingClinicalServices.class);
                startActivity(intent);
            }
        });
    }
    public void goIFS(){
        IslamicFamilyServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, IslamicFamilyServices.class);
                startActivity(intent);
            }
        });
    }
    private void goYE(){
        YWCAEdmonton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, YWCAEdmonton.class);
                startActivity(intent);
            }
        });
    }

    private void goSACOE() {
        SexualAssaultCentreofEdmonton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, SexualAssaultCentreofEdmonton.class);
                startActivity(intent);
            }
        });
    }

    public void goPCOE(){
        PrideCentreofEdmonton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, PrideCentreofEdmonton.class);
                startActivity(intent);
            }
        });
    }

    public void goUOAFOECS(){
        UniversityofAlbertaFacultyofEducationClinicalServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, UniversityofAlbertaFaculty.class);
                startActivity(intent);
            }
        });
    }

    public void goTFC(){
        THEFAMILYCENTRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, THEFAMILYCENTRE.class);
                startActivity(intent);
            }
        });
    }
    public void goDIY(){
        DropInYEG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, DropInYEG.class);
                startActivity(intent);
            }
        });
    }

    public void goCCC(){
        CommunityCounsellingCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, CommunityCounsellingCentre.class);
                startActivity(intent);
            }
        });
    }

    public void goMWIC(){
        MomentumWalkInCounselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this, MomentumWalkInCounselling.class);
                startActivity(intent);
            }
        });
    }

    public void goBack(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void goAOME(){
        AccessOpenMindsEdmonton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this,
                        AccessOpenMindsEdmonton.class);
                startActivity(intent);
            }
        });
    }
    public void goCCE(){
        CornerstoneCounsellingCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpLocations.this,
                        CornerstoneCounsellingCentre.class);
                startActivity(intent);
            }
        });
    }
}
