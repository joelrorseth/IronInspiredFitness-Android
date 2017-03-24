package com.joelrorseth.ironinspiredfitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GenerateWorkoutActivity extends AppCompatActivity {

    // ==============================================
    // ==============================================
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_workout);

        // Link up Spinners
        Spinner workoutTypeSpinner = (Spinner) findViewById(R.id.workout_type_spinner);
        Spinner workoutDifficultySpinner = (Spinner) findViewById(R.id.workout_difficulty_spinner);

        // Create basic adapters for string-arrays defined in strings.xml
        ArrayAdapter<CharSequence> typeAdapter =
                ArrayAdapter.createFromResource(this, R.array.workout_types_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> difficultyAdapter =
                ArrayAdapter.createFromResource(this, R.array.exercise_difficulties_array, android.R.layout.simple_spinner_item);

        // Set drop down view for both adapters
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapters created to each Spinner
        workoutTypeSpinner.setAdapter(typeAdapter);
        workoutDifficultySpinner.setAdapter(difficultyAdapter);
    }
}
