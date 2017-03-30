package com.joelrorseth.ironinspiredfitness;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    // ==============================================
    // ==============================================
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Link up button objects for the Buttons created in layout file
        final Button generateWorkoutButton = (Button) findViewById(R.id.generate_workout_button);
        final Button myWorkoutsButton = (Button) findViewById(R.id.my_workouts_button);
        final Button browseExercisesButton = (Button) findViewById(R.id.browse_exercies_button);

        final Context context = this;

        // Loaded ArrayList<Exercise> holding all exercises parsed from exercises.json
        ArrayList<Exercise> exerciseList = Exercise.getExercisesFromFile("exercises.json", this);

        // Wrap exerciseList into Workout object to be able to pass as an Intent to other activities
        final Workout exerciseListWorkout = new Workout("All exercises", exerciseList, Workout.Difficulty.Easy, Workout.Type.Any, 0.0);

        // Create listener for Generate Workout button
        generateWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create Intent (transition)
                Intent detailIntent = new Intent(context, GenerateWorkoutActivity.class);
                detailIntent.putExtra("exerciseListWorkout", (Parcelable) exerciseListWorkout);
                startActivity(detailIntent);
            }
        });

        // Create listener for My Workouts button
        myWorkoutsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create Intent (transition)
                Intent detailIntent = new Intent(context, MyWorkoutsActivity.class);
                startActivity(detailIntent);
            }
        });

        // Create listener for Browse Exercises Button
        browseExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create Intent (transition)
                Intent detailIntent = new Intent(context, ExercisesActivity.class);
                detailIntent.putExtra("exerciseListWorkout", (Parcelable) exerciseListWorkout);
                startActivity(detailIntent);
            }
        });
    }
}
