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

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView failedSuccess = (TextView) findViewById(R.id.textView4);
        failedSuccess.setVisibility(View.INVISIBLE);

    }

    protected void onLoginPressed(View view) {
        Model model = Model.getInstance();
        TextView usernameField = (TextView) findViewById(R.id.usernameField);
        String username = usernameField.getText().toString();

        TextView passwordField = (TextView) findViewById(R.id.passwordField);
        String password = passwordField.getText().toString();
        if(model.attemptLogin(username, password)) {
            Log.d("SUCCESS:", "enter application");
        } else {
            TextView failedSuccess = (TextView) findViewById(R.id.textView4);
            failedSuccess.setVisibility(View.VISIBLE);
        }
    }

    protected void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
    }
}
