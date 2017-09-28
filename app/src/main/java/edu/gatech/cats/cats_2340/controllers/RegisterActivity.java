package edu.gatech.cats.cats_2340.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.gatech.cats.cats_2340.R;
import android.widget.TextView;

import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.User;

public class RegisterActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView userText;
    private TextView passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    protected void onRegisterPressed() {
        Model model = Model.getInstance();

        String name = nameText.getText().toString();
        String user = userText.getText().toString();
        String pass = userText.getText().toString();

        User newUser = new User(name, user, pass, true);

        model.register(newUser);
    }
}
