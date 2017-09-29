package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.gatech.cats.cats_2340.R;
import android.widget.TextView;

import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.User;

import android.view.*;

public class RegisterActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView userText;
    private TextView passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameText = (TextView) findViewById(R.id.nameField);
        userText = (TextView) findViewById(R.id.userID);
        passText = (TextView) findViewById(R.id.password);
    }

    protected void onRegisterPressed(View view) {
        Model model = Model.getInstance();

        String name = nameText.getText().toString();
        String user = userText.getText().toString();
        String pass = passText.getText().toString();

        User newUser = new User(name, user, pass, true);

        model.register(newUser);

        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        finish();
    }

    protected void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        finish();
    }
}
