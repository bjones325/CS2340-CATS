package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.os.StrictMode;
import android.widget.TextView;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.Model;

/**
 * The splash screen of the app, not current in this
 */
public class OpeningActivity extends AppCompatActivity{
    private EditText userText;
    private EditText passText;
    private TextView passEdit;
    private TextView failedSuccessText;
    private boolean userEdit;
    private Animation shake;

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
            @Override
            public void onClick(View v) {
                if (!userEdit) {
                    userText.setText("");
                    userEdit = true;
                } else if (!"".equals(userText.getText().toString())){
                    passText.requestFocus();
                    passEdit.setVisibility(View.INVISIBLE);
                    userEdit = false;
                }
            }
        });
        // Changes visibility of password text
        passText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passEdit.setVisibility(View.INVISIBLE);
            }
        });



        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SQLController.getSQLController().initializeConnection();
    }

    /*private static AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

        @Override
        protected Void doInBackground(Void ... params) {
            SQLController.getSQLController().initializeConnection();
            return null;
        }
    };*/

    /**
     * This is a button called by the Log-In button- it calls the Model's log in function. If
     * a user with a matching password is found, the user is logged in and sent to the application
     * otherwise they are stuck on the same screen
     * @param view The view
     */
    public void onLoginPressed(View view) {
        Model model = Model.getInstance();
        String username = userText.getText().toString();
        String password = passText.getText().toString();

        Log.d("Login", "Attempting login");

        //If they log in, put them in the application
        if(model.attemptLogin(username, password)) {
            Log.d("Login", "Success");
            startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
            finish();
        } else {
            Log.d("Login", "Failure");
            failedSuccessText.setVisibility(View.VISIBLE);
            passEdit.setVisibility(View.INVISIBLE);
            userText.startAnimation(shake);
            passText.startAnimation(shake);
            passText.setText("");
        }
    }
}
