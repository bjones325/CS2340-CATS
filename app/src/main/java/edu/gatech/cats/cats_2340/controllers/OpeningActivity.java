package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.StrictMode;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.Model;

//Splash screen
public class OpeningActivity extends AppCompatActivity{
    public EditText userText;
    public EditText passText;
    private TextView passEdit;
    private TextView failedSuccessText;
    boolean userEdit;
    public Animation shake;
    /**
     * Creates first screen the user/admin sees when the app is initially opened
     * @param savedInstanceState passes data between various android activities
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        // Sets current state of user edit to false
        userText = (EditText) findViewById(R.id.usernameField);
        passText = (EditText) findViewById(R.id.passwordField);
        userEdit = false;
        passEdit = (TextView) findViewById(R.id.passEdit);
        failedSuccessText = (TextView) findViewById(R.id.loginFail);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);

        // We want the Password text visible and the fail message invisible at the start
        passEdit.setVisibility(View.VISIBLE);
        failedSuccessText.setVisibility(View.INVISIBLE);


        // If username has yet to be changed, clears text
        // Otherwise, moves on to password (For hitting enter key counts as a click)
        userText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!userEdit) {
                    userText.setText("");
                    userEdit = true;
                } else if (!userText.getText().toString().equals("")){
                    passText.requestFocus();
                    passEdit.setVisibility(View.INVISIBLE);
                    userEdit = false;
                }
            }
        });
        // Changes visibility of password text
        passText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                passEdit.setVisibility(View.INVISIBLE);
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

    /**
     * This is a button called by the Log-In button- it calls the Model's log in function. If
     * a user with a matching password is found, the user is logged in and sent to the application
     * otherwise they are stuck on the same screen
     * @param view
     */
    public void onLoginPressed(View view) {
        Model model = Model.getInstance();
        String username = userText.getText().toString();
        String password = passText.getText().toString();

        //If they log in, put them in the application
        if(model.attemptLogin(username, password)) {
            startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
            finish();
        } else {
            failedSuccessText.setVisibility(View.VISIBLE);
            passEdit.setVisibility(View.INVISIBLE);
            userText.startAnimation(shake);
            passText.startAnimation(shake);
            passText.setText("");
        }
    }


}
