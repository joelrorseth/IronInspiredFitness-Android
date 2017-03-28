package com.joelrorseth.ironinspiredfitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;


public class WorkoutDetailActivity extends AppCompatActivity {

    private static Workout workout;

    // ==============================================
    // ==============================================
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        // Extract Workout object passed from GenerateWorkoutActivity
        workout = getIntent().getExtras().getParcelable("workout");

        // Link up the ListView and Save Workout Button
        ListView mExercisesListView = (ListView) findViewById(R.id.workout_detail_list_view);
        Button saveWorkoutButton = (Button) findViewById(R.id.save_workout_button);


        // Employ custom adapter that specializes in displaying ArrayList<Exercise>
        ExerciseAdapter adapter = new ExerciseAdapter(this, workout.getExercises());
        mExercisesListView.setAdapter(adapter);


        final Context context = this;

        // Setup an OnItemClickListener for the list view
        mExercisesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // ==============================================
            // ==============================================
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Create Intent (transition)
                Intent detailIntent = new Intent(context, ExerciseDetailActivity.class);

                // Extract exercise at selected row
                Exercise selectedExercise = workout.getExercises().get(position);

                // Important: Pass data from this activity to ExerciseDetailActivity
                detailIntent.putExtra("name", selectedExercise.name);
                detailIntent.putExtra("description", selectedExercise.description);
                detailIntent.putExtra("imageUrl", selectedExercise.imageUrl);
                detailIntent.putExtra("motion", selectedExercise.motion);
                detailIntent.putExtra("difficulty", selectedExercise.difficulty);

                startActivity(detailIntent);
            }
        });

        // Persist Workout object upon selection, dismiss activity
        saveWorkoutButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {

                saveWorkout();
                finish();
            }
        });
    }

    // ==============================================
    // ==============================================
    public void saveWorkout() {

        // Obtain shared preferences for Workouts saved
        SharedPreferences mSettings = getSharedPreferences("Workouts", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSettings.edit();

        // Create Gson instance, convert Workout object to JSON String
        Gson gson = new Gson();
        String json = gson.toJson(workout);

        // Save JSON String to shared preferences
        mEditor.putString(workout.getName(), json);
        mEditor.apply();

        Log.d("SAVE", "Successfully saved " + workout.getName());
    }
}
