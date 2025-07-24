package com.example.dailyfitnesstracker; // Package declaration for the application

import android.os.Bundle; // Importing Bundle class for passing data between activities
import android.view.View; // Importing View class for handling UI elements
import android.widget.Button; // Importing Button class for button UI elements
import android.widget.EditText; // Importing EditText class for input fields
import android.widget.TextView; // Importing TextView class for displaying text
import android.widget.Toast; // Importing Toast class for displaying brief messages
import androidx.appcompat.app.AppCompatActivity; // Importing AppCompatActivity for compatibility with older Android versions

public class CaloriesActivity extends AppCompatActivity { // CaloriesActivity class extending AppCompatActivity
    private EditText weightInput; // Declaring EditText variable for weight input
    private EditText ageInput; // Declaring EditText variable for age input
    private TextView caloriesBurned; // Declaring TextView variable to display calories burned

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate method for initializing the activity
        super.onCreate(savedInstanceState); // Calling the superclass method
        setContentView(R.layout.activity_calories); // Setting the content view to the layout defined in activity_calories.xml

        weightInput = findViewById(R.id.weight_input); // Initializing weightInput with the EditText view
        ageInput = findViewById(R.id.age_input); // Initializing ageInput with the EditText view
        caloriesBurned = findViewById(R.id.calories_burned); // Initializing caloriesBurned with the TextView view

        Button calculateButton = findViewById(R.id.calculate_button); // Initializing the Calculate button
        Button backButton = findViewById(R.id.back_button); // Initializing the Back button

        // Setting an OnClickListener for the Calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // onClick method when the button is clicked
                int steps = getIntent().getIntExtra("steps", 0); // Retrieving the steps passed from MainActivity
                if (steps == 0) { // Checking if steps are zero
                    Toast.makeText(CaloriesActivity.this, "Please enter steps in the main screen", Toast.LENGTH_SHORT).show(); // Displaying a toast message if steps are zero
                    finish(); // Finishing the activity to return to MainActivity
                    return; // Exiting the onClick method
                }
                if (weightInput.getText().toString().isEmpty() || ageInput.getText().toString().isEmpty()) {
                    Toast.makeText(CaloriesActivity.this, "Please enter weight and age", Toast.LENGTH_SHORT).show();

                }else {
                    int weight = Integer.parseInt(weightInput.getText().toString()); // Parsing the weight input to an integer
                    int age = Integer.parseInt(ageInput.getText().toString()); // Parsing the age input to an integer
                    double calories = calculateCalories(weight, age, steps); // Calling the method to calculate calories burned
                    caloriesBurned.setText("Calories Burned: " + Math.round(calories)); // Displaying the calories burned rounded to the nearest whole number
                }
            }
        });

        // Setting an OnClickListener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // onClick method when the button is clicked
                finish(); // Finishing the activity to return to MainActivity
            }
        });
    }

    // Method to calculate calories burned based on weight, age, and steps
    private double calculateCalories(int weight, int age, int steps) {
        // MET value for walking at moderate speed (e.g., 3.5 METs)
        double met = 3.5;
        // Average step length in miles (e.g., 2,000 steps per mile)
        double stepsPerMile = 2000;
        // Convert steps to miles
        double miles = steps / stepsPerMile;
        // Adjust the MET value based on age (example adjustment, you can refine this)
        double ageFactor = 1 - (age - 20) * 0.001;
        // Calories burned per mile = METs * weight (lbs) * miles * ageFactor
        double caloriesPerMile = met * weight * miles * ageFactor; // Calculating the calories burned
        return caloriesPerMile; // Returning the calculated calories burned
    }
}