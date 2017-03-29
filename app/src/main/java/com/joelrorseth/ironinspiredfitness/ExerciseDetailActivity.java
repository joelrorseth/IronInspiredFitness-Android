package com.joelrorseth.ironinspiredfitness;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ExerciseDetailActivity extends AppCompatActivity {

    Resources resources;

    // ==============================================
    // ==============================================
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        // Link up objects created in layout file
        final TextView exerciseNameTextView = (TextView) findViewById(R.id.exercise_detail_name);
        final TextView exerciseDescTextView = (TextView) findViewById(R.id.exercise_detail_description);
        final TextView exerciseMotionTextView = (TextView) findViewById(R.id.exercise_detail_motion);
        final TextView exerciseDifficultyTextView = (TextView) findViewById(R.id.exercise_detail_difficulty);
        final ImageView exerciseImageView = (ImageView) findViewById(R.id.exercise_detail_imageview);

        // Extract exercises properties passed from ExercisesActivity
        String name = this.getIntent().getExtras().getString("name");
        String image = this.getIntent().getExtras().getString("imageUrl");
        String description = this.getIntent().getExtras().getString("description");
        String motion = "Motion: " + this.getIntent().getExtras().getString("motion");
        String difficulty = "Difficulty: " + this.getIntent().getExtras().getString("difficulty");

        // Assign properties to each object on screen
        exerciseNameTextView.setText(name);
        exerciseDescTextView.setText(description);
        exerciseDifficultyTextView.setText(difficulty);
        exerciseMotionTextView.setText(motion);

        // Use image property to build a resource path to the corresponding image asset
        resources = getResources();
        int id = resources.getIdentifier(image, "drawable", getApplicationContext().getPackageName());
        exerciseImageView.setImageResource(id);
    }
}
