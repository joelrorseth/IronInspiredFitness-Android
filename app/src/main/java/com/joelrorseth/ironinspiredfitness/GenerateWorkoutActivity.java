package com.joelrorseth.ironinspiredfitness;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

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


        final Button generateButton = (Button) findViewById(R.id.generate_button);
        final Context context = this;

        // Create listener for Generate Workout button
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create Intent (transition)
                Intent workoutIntent = new Intent(context, WorkoutDetailActivity.class);

                // Generate a workout, pass it to WorkoutDetailActivity
                Workout workout = generateWorkout();
                workoutIntent.putExtra("workout", workout);

                startActivity(workoutIntent);
            }
        });
    }

    // ==============================================
    // ==============================================
    private Workout generateWorkout() {

        ArrayList<Exercise> exercises = new ArrayList<Exercise>();
        String name = "";
        Workout.Difficulty difficulty = Workout.Difficulty.Easy;
        Workout.Type type = Workout.Type.Any;
        double length = 0.0;

        // TODO: Algorithm for generating workout

        return new Workout(name, exercises, difficulty, type, length);
    }
}
