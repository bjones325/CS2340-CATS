package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.gatech.cats.cats_2340.R;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.User;

import android.view.*;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView nameText;
    private TextView userText;
    private TextView passText;
    private Spinner userTypeLabel;
    // Keeps track of spinner changes
    private String _type = "Unselected";
    boolean isAdmin = false;

    /**
     * Overriden on-create sets the view to the application view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameText = (TextView) findViewById(R.id.nameField);
        userText = (TextView) findViewById(R.id.userID);
        passText = (TextView) findViewById(R.id.password);
        userTypeLabel = (Spinner) findViewById(R.id.userTypeLabel);

        TextView failedRegisterText = (TextView) findViewById(R.id.invalidSubmit);
        failedRegisterText.setVisibility(View.INVISIBLE);

        // Set up adapter to display the allowable types inside the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeLabel.setAdapter(adapter);
    }

    /**
     * Adds a new user with submitted data to the list of users. Will most likely update with
     * database functionality soon
     * @param view
     */
    public void onRegisterPressed(View view) {
        Model model = Model.getInstance();

        String name = nameText.getText().toString();
        String user = userText.getText().toString();
        String pass = passText.getText().toString();

        boolean isAdmin = false;
        if (userTypeLabel.getSelectedItem().equals("Admin")) {
            isAdmin = true;
        }

        if (name.equals("") || user.equals("") || pass.equals("")) {
            TextView failedRegisterText = (TextView) findViewById(R.id.invalidSubmit);
            failedRegisterText.setVisibility(View.VISIBLE);

            return;
        }

        User newUser = new User(name, user, pass, isAdmin);

        model.register(newUser);
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        finish();
    }
    /**
     * Goes back to splash screen when the user presses cancel
     * @param view
     */
    public void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        finish();
    }

    /**
     * Informs the application that the item has been selected or not
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _type = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
