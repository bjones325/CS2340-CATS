package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.Model;

//A basic log in screen. Can log in or return to splash screen from here
public class LoginActivity extends AppCompatActivity{

    private TextView failedSuccessText;
    private TextView usernameField;
    private TextView passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameField = (TextView) findViewById(R.id.usernameField);
        passwordField = (TextView) findViewById(R.id.passwordField);
        //Log-in failed text

        failedSuccessText = (TextView) findViewById(R.id.textView4);
        //We want it invisible at the start
        failedSuccessText.setVisibility(View.INVISIBLE);

    }

    protected void onLoginPressed(View view) {
        Model model = Model.getInstance();
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        if(model.attemptLogin(username, password)) {
            startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
            finish();
        } else {
            failedSuccessText.setVisibility(View.VISIBLE);
        }
    }

    protected void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        finish();
    }
}
