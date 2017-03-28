package com.joelrorseth.ironinspiredfitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WorkoutDetailActivity extends AppCompatActivity {

    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

       // workout = getIntent().getExtras().getParcelable("workout");
    }
}
