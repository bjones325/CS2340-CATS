package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.StrictMode;

import edu.gatech.cats.cats_2340.R;

//Splash screen
public class OpeningActivity extends AppCompatActivity {

    /**
     * Creates first screen the user/admin sees when the app is initially opened
     * @param savedInstanceState passes data between various android activities
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        // Loads Titlebar Image
        ImageView titleImg = (ImageView) findViewById(R.id.titlebar);
        titleImg.setImageResource(R.drawable.titlebar);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void ... params) {
                SQLController.getSQLController().initializeConnection();
                return null;
            }
        }.execute();
    }
}
