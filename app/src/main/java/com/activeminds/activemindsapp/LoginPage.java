package com.activeminds.activemindsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class LoginPage extends AppCompatActivity {
    private final static int REQUEST_CODE = 141;


    private EditText userName;
    private EditText password;
    private TextView noAccount;
    private TextView signUp;
    private Button prompt;
    private TextView errorMsg;
    private ConstraintLayout background;
    private ImageView greenLogo;
    private ImageView yellowLogo;
    FirebaseFirestore db;
    SharedPreferences sp;
    SharedPreferences sp1;
    private Handler handler;



    // 0 means logging in, 1 means sign up
    private Integer checkBtn = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        sp1 = getSharedPreferences("username",MODE_PRIVATE);
        handler = new Handler();
        db = FirebaseFirestore.getInstance();
        yellowLogo = findViewById(R.id.activity_main_iv_logo1);
        greenLogo= findViewById(R.id.activity_main_iv_logo0);
        userName = findViewById(R.id.activity_main_et__username);
        password = findViewById(R.id.activity_main_et__password);
        signUp = findViewById(R.id.activity_main_tv_signUp);
        prompt = findViewById(R.id.activity_main_btn_submit);
        noAccount = findViewById(R.id.activity_main_tv_noAccount);
        background = findViewById(R.id.activity_main_CL_background);
        errorMsg = findViewById(R.id.activity_main_tv_incorrect);

        //Requests location permission on startup, exits the app if permission denied.
        String[] PERMISSIONS = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (
                (ContextCompat.checkSelfPermission(LoginPage.this, PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(LoginPage.this, PERMISSIONS[1]) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(LoginPage.this, PERMISSIONS, REQUEST_CODE);
        }
    }
    //Handles user decision to grant or not grant permissions.
    @Override
    public void onRequestPermissionsResult ( int requestCode,
                                             String[] permissions, int[] grantResults){
        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    System.exit(0);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }

    }

    /**
     * takes the user to the sign up page or the login page depending on where they were
     */
    public void userSignUpORSignIn(View view) {
        Log.d("debugging","mainActivity- |" + signUp.getText().toString());
        // hiding error message textview
        errorMsg.setVisibility(View.INVISIBLE);
        // setting up the screen to look like the sign up screen
        if (signUp.getText().toString().equals(getString(R.string.sign_up))) {
            yellowLogo.setVisibility(View.VISIBLE);
            greenLogo.setVisibility(View.INVISIBLE);
            prompt.setText(R.string.signUpBtn);
            errorMsg.setTextColor(Color.parseColor("#1e7145"));
            prompt.setBackgroundColor(Color.parseColor("#ffc40d"));
            background.setBackgroundColor(Color.parseColor("#ffc40d"));
            noAccount.setText(R.string.alreadyExist);
            noAccount.setTextColor(0xFFFFFFFF);
            signUp.setText(R.string.sign_in);
            signUp.setTextColor(Color.parseColor("#1e7145"));
            // settingg tp sign up
            checkBtn = 1;
        }
        // setting up the screen to look like log in screen
        else {
            yellowLogo.setVisibility(View.INVISIBLE);
            greenLogo.setVisibility(View.VISIBLE);
            errorMsg.setTextColor(Color.parseColor("#ffc40d"));
            prompt.setText(R.string.submit);
            int myColor = Color.parseColor("#1e7145");
            prompt.setBackgroundColor(myColor);
            background.setBackgroundColor(myColor);
            checkBtn = 0;
            userName.setText("");
            password.setText("");
            noAccount.setText(R.string.no_account);
            signUp.setText(R.string.sign_up);
            signUp.setTextColor(Color.parseColor("#ffc40d"));

        }
    }

    /**
     * Checks to make sure the username or password is not empty when an user tries to login/sign up
     */
    public void CheckEmpty(View view) {
        Log.d("debugging","mainActivity- |"+ prompt.getText().toString()+"|");
        String userNameInput = userName.getText().toString().toLowerCase();
        String passwordInput = password.getText().toString();
        // if either username or password is empty it will show a message saying that they can not be empty
        if ((userName.getText().toString().isEmpty()) || password.getText().toString().isEmpty()){
            errorMsg.setText(R.string.empty);
            errorMsg.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    errorMsg.setVisibility(View.INVISIBLE);
                }
            }, 2000);
        }
        // since checkBtn is 0, authenticating log in by calling CheckLogin
        else if (checkBtn == 0){
            Log.d("debugging", "mainActivity- Logging in with " + userNameInput +" and " + passwordInput);
            CheckLogIn(userNameInput, passwordInput);
        }
        // letting user sign up
        else{
            Log.d("debugging", "mainActivity- Signing up with " + userNameInput + " and " + passwordInput);
            MakeAccount(userNameInput, passwordInput);
        }
    }

    /**
     * Checks if the account exists or not to validate log in
     * @param accountName
     *     This is the account name user enters
     * @param inputtedpassword
     *     This is the password user enters
     */
    public void CheckLogIn(final String accountName, final String inputtedpassword){
        Log.d("debugging", "mainActivity- Check account");
        DocumentReference docRef = db.collection("Users Account").document(accountName);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    // if the account account checks for correct password
                    if (document.exists()) {
                        Log.d("debugging", "mainActivity- Password: " + document.getData().get("password"));
                        // if inputted password is correct, let the user log in
                        if (document.getData().get("password").equals(inputtedpassword)){
                            Log.d("debugging", "mainActivity- Logged In");
                            errorMsg.setText(R.string.logging_in);
                            errorMsg.setVisibility(View.VISIBLE);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    errorMsg.setVisibility(View.INVISIBLE);
                                }
                            }, 2000);
                            Intent intent = new Intent(getApplicationContext(), UserPage.class);
                            intent.putExtra("accountKey", accountName);
                            intent.putExtra("FROM_ACTIVITY", "login");
                            sp.edit().putBoolean("logged",true).apply();
                            sp1.edit().putString("username", accountName).apply();
                            startActivity(intent);

                        }


                        // if inputted password is wrong, set the password field empty and
                        // show error message
                        else {
                            errorMsg.setText(R.string.wrongAccount);
                            errorMsg.setVisibility(View.VISIBLE);
                            password.setText("");
                        }

                        // the account does not exists, call ShowToast and set username and password field back to empty
                    } else {
                        Log.d("debugging", "mainActivity- Account does not exists");
                        errorMsg.setText(R.string.dneAccount);
                        errorMsg.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                errorMsg.setVisibility(View.INVISIBLE);
                            }
                        }, 2000);
                        userName.setText("");
                        password.setText("");
                    }
                } else {
                    Log.d("debugging", "mainActivity- get failed with ", task.getException());
                }
            }
        });
    }


    // checks if the inputted username already exists or not, if it does, it takes the user back to
    // log in page, if not, lets the user sign up


    /**
     * Checks if the account exists or not to let user make an account
     * If successful, logs in user automatically
     * @param accountName
     *     This is the account name user enters
     * @param inputtedPassword
     *     This is the password user enters
     */
    public void MakeAccount(final String accountName, final String inputtedPassword){
        Log.d("debugging", "mainActivity- Adding account to FB");
        // Checking if the account exists or not
        DocumentReference docRef = db.collection("Users Account").document(accountName);
        Log.d("debugging", "YAA");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    // if the account exists
                    if (document.exists()) {
                        Log.d("debugging", "mainActivity- Account already exists with password as:  " + document.getData());
                        errorMsg.setText(R.string.existAccount);
                        errorMsg.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                errorMsg.setVisibility(View.INVISIBLE);
                            }
                        }, 2000);
                        password.setText("");

                        // since the account does not exists, let the user sign up
                    } else {
                        Log.d("debugging", "mainActivity- Account does not exists");
                        HashMap<String, String> account = new HashMap<>();
                        account.put("password", inputtedPassword);
                        final CollectionReference collectionReference = db.collection("Users Account");
                        collectionReference
                                .document(accountName)
                                .set(account)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("debugging", "mainActivity- User added");
                                        errorMsg.setText(R.string.created);
                                        errorMsg.setVisibility(View.VISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                errorMsg.setVisibility(View.INVISIBLE);
                                            }
                                        }, 2000);

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("debugging", "mainActivity- Data addition failed" + e.toString());
                                    }
                                });
                    }
                } else {
                    Log.d("debugging", "mainActivity- get failed with ", task.getException());
                }
            }
        });

    }


}
