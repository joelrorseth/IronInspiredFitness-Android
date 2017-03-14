package com.joelrorseth.ironinspiredfitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ExercisesActivity extends AppCompatActivity {

    private ListView exercisesListView;

    @Override
    // ==============================================
    // ==============================================
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        exercisesListView = (ListView) findViewById(R.id.exercises_list_view);
    }
}
