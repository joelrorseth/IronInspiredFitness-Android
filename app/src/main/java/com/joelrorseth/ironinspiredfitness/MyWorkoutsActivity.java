package com.joelrorseth.ironinspiredfitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MyWorkoutsActivity extends AppCompatActivity {

    private ListView workoutsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);

        workoutsListView = (ListView) findViewById(R.id.workouts_list_view);
    }
}
