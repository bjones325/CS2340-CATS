package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.Model;

//Placeholder for rest of application
public class ApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
    }

    //Logout button
    protected void onLogoutPressed(View view) {
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        Model.getInstance().logout();
        finish();
    }
}