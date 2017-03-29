package com.joelrorseth.ironinspiredfitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Map;

public class MyWorkoutsActivity extends AppCompatActivity {

    private ArrayList<Workout> workouts;
    private ArrayAdapter<String> workoutAdapter;

    // ==============================================
    // ==============================================
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);

        ListView workoutsListView = (ListView) findViewById(R.id.workouts_list_view);
        loadWorkouts();

        // Our custom adapter only requires a list of Exercise objects
        WorkoutAdapter adapter = new WorkoutAdapter(this, workouts);
        workoutsListView.setAdapter(adapter);

        final Context context = this;

        // Setup an OnItemClickListener for the list view
        workoutsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // ==============================================
            // ==============================================
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Create Intent (transition)
                Intent workoutIntent = new Intent(context, WorkoutDetailActivity.class);

                // Important: Generate a workout, pass it to WorkoutDetailActivity
                Workout workout = workouts.get(position);
                workoutIntent.putExtra("workout", (Parcelable) workout);

                // Transition to WorkoutDetailActivity, pop this activity off the activity stack
                startActivity(workoutIntent);
            }
        });
    }

    // ==============================================
    // ==============================================
    public void loadWorkouts() {

        workouts = new ArrayList<>();

        // Obtain shared preferences for Workouts
        SharedPreferences sharedPreferences = getSharedPreferences("Workouts", Context.MODE_PRIVATE);

        // Create Gson object to parse JSON to Workout object
        Gson gson = new Gson();

        // Obtain all stored Workout entries in the Workouts shared preferences
        Map<String, ?> allPrefs = sharedPreferences.getAll();

        // For each Workout saved in JSON format
        for (Map.Entry<String, ?> entry: allPrefs.entrySet()) {

            // Read JSON Workout as String, convert and store in ArrayList<Workout>
            String json = entry.getValue().toString();
            workouts.add(gson.fromJson(json, Workout.class));
        }

        Log.d("LOAD", "Successfully loaded " + workouts.size() + " workouts from shared preferences");
    }
}
