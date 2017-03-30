package com.joelrorseth.ironinspiredfitness;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.*;

public class ExercisesActivity extends AppCompatActivity {

    private ListView mExercisesListView;

    // ==============================================
    // ==============================================
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        mExercisesListView = (ListView) findViewById(R.id.exercises_list_view);

        // If adapter has already been set / used, avoid possibility of inserting duplicate data
        if (mExercisesListView.getAdapter() != null) {
            Log.d("OPTIMIZE", "Exercise adapter has already been used for list view");
            return;
        }

        // Store Exercise objects in ArrayList from file
        final ArrayList<Exercise> exerciseList = Exercise.getExercisesFromFile("exercises.json", this);
        final Context context = this;

        // Setup an OnItemClickListener for the list view
        mExercisesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // ==============================================
            // ==============================================
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Create Intent (transition)
                Intent detailIntent = new Intent(context, ExerciseDetailActivity.class);

                // Extract exercise at selected row
                Exercise selectedExercise = exerciseList.get(position);

                // Important: Pass data from this activity to ExerciseDetailActivity
                detailIntent.putExtra("name", selectedExercise.name);
                detailIntent.putExtra("description", selectedExercise.description);
                detailIntent.putExtra("imageUrl", selectedExercise.imageUrl);
                detailIntent.putExtra("motion", selectedExercise.motion);
                detailIntent.putExtra("difficulty", selectedExercise.difficulty);

                startActivity(detailIntent);
            }
        });

        // Our custom adapter only requires a list of Exercise objects
        ExerciseAdapter adapter = new ExerciseAdapter(this, exerciseList);
        mExercisesListView.setAdapter(adapter);
    }
}