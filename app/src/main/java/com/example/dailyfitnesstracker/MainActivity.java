package com.example.dailyfitnesstracker;

import android.content.Intent; // Importing Intent class to navigate between activities
import android.os.Bundle; // Importing Bundle class for passing data between activities
import android.view.View; // Importing View class to handle user interface elements
import android.widget.Button; // Importing Button class for button elements
import android.widget.EditText; // Importing EditText class for text input fields
import android.widget.Toast; // Importing Toast class for displaying short messages
import androidx.appcompat.app.AppCompatActivity; // Importing AppCompatActivity for activity lifecycle management

public class MainActivity extends AppCompatActivity {

    private EditText stepsInput; // Declare an EditText variable to capture user input for steps

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call the superclass method to create the activity
        setContentView(R.layout.activity_main); // Set the content view to the activity_main layout

        stepsInput = findViewById(R.id.steps_input); // Initialize the EditText for steps input
        Button calculateCaloriesButton = findViewById(R.id.calculate_calories_button); // Initialize the button for calculation

        // Set an OnClickListener for the calculate button
        calculateCaloriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stepsText = String.valueOf(stepsInput.getText()); // Get the text input from the user
                if (stepsText.isEmpty()) { // Check if the input is empty
                    Toast.makeText(MainActivity.this, "Please enter your steps", Toast.LENGTH_SHORT).show(); // Show a toast message
                } else {
                    // Convert the steps text to an integer
                    int steps = Integer.parseInt(stepsText);
                    // Create an Intent to start the CaloriesActivity
                    Intent intent = new Intent(MainActivity.this, CaloriesActivity.class);
                    // Add the steps value to the Intent as an extra
                    intent.putExtra("steps", steps); // "steps" is the key, and steps is the value
                    stepsInput.setText("");
                    // Start the CaloriesActivity
                    startActivity(intent);
                }
            }
        });
    }
}