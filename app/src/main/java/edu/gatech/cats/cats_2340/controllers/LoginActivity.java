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

    /**
     * Overriden on-create sets the view to the activity_login view
     * @param savedInstanceState
     */
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

    /**
     * This is a button called by the Log-In button- it calls the Model's log in function. If
     * a user with a matching password is found, the user is logged in and sent to the application
     * otherwise they are stuck on the same screen
     * @param view
     */
    public void onLoginPressed(View view) {
        Model model = Model.getInstance();
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        //If they log in, put them in the application
        if(model.attemptLogin(username, password)) {
            startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
            finish();
        } else {
            failedSuccessText.setVisibility(View.VISIBLE);
        }
    }
    /**
     * User is on the login screen but pushes the cancel button. This takes the user back to the
     * preliminary screen
     * @param view
     */
    public void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        finish();
    }
}
