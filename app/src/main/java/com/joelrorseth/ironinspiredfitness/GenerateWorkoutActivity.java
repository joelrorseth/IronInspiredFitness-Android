package com.joelrorseth.ironinspiredfitness;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GenerateWorkoutActivity extends AppCompatActivity {

    private Spinner workoutTypeSpinner;
    private Spinner workoutDifficultySpinner;

    private SeekBar lengthSeekBar;
    private int seekBarLength = 60;

    private static ArrayList<Exercise> exerciseList;
    private static ArrayList<CheckBox> checkboxes;

    // ==============================================
    // ==============================================
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_workout);

        // Create an ArrayList of all exercises, loaded from exercises.json
        exerciseList = Exercise.getExercisesFromFile("exercises.json", this);

        // Link up Spinners
        workoutTypeSpinner = (Spinner) findViewById(R.id.workout_type_spinner);
        workoutDifficultySpinner = (Spinner) findViewById(R.id.workout_difficulty_spinner);

        // Link up CheckBoxes, store into ArrayList
        checkboxes = new ArrayList<>();
        checkboxes.add((CheckBox) findViewById(R.id.cbBack));
        checkboxes.add((CheckBox) findViewById(R.id.cbBiceps));
        checkboxes.add((CheckBox) findViewById(R.id.cbCalves));
        checkboxes.add((CheckBox) findViewById(R.id.cbChest));
        checkboxes.add((CheckBox) findViewById(R.id.cbCore));
        checkboxes.add((CheckBox) findViewById(R.id.cbForearms));
        checkboxes.add((CheckBox) findViewById(R.id.cbHamstrings));
        checkboxes.add((CheckBox) findViewById(R.id.cbShoulders));
        checkboxes.add((CheckBox) findViewById(R.id.cbTraps));
        checkboxes.add((CheckBox) findViewById(R.id.cbTriceps));
        checkboxes.add((CheckBox) findViewById(R.id.cbQuads));

        // Link up SeekBar (length)
        lengthSeekBar = (SeekBar) findViewById(R.id.workout_length_seekbar);

        // Link up TextView displaying the prompt for (and currently selected) workout length
        final TextView workoutLengthTextView = (TextView) findViewById(R.id.workout_length_prompt_textview);


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


        // Establish a listener for the workout length SeekBar
        lengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekBarLength = progress;

                // TODO: Show text indicating current value
                workoutLengthTextView.setText("I want to exercise for " + String.valueOf(progress) + " minutes");
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        final Button generateButton = (Button) findViewById(R.id.generate_button);
        final Context context = this;

        // Create listener for Generate Workout button
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                // Create Intent (transition)
                Intent workoutIntent = new Intent(context, WorkoutDetailActivity.class);

                // Important: Generate a workout, pass it to WorkoutDetailActivity
                Workout workout = generateWorkout();
                workoutIntent.putExtra("workout", (Parcelable) workout);
                workoutIntent.putExtra("showSaveButton", true);

                // Transition to WorkoutDetailActivity, pop this activity off the activity stack
                startActivity(workoutIntent);
                finish();
            }
        });
    }


    // ==============================================
    // ==============================================
    private Workout generateWorkout() {

        ArrayList<String> selectedMuscleGroups = new ArrayList<>();

        // Set type and difficulty by extracting values of Spinners
        Workout.Difficulty difficulty = Workout.Difficulty.valueOf(workoutDifficultySpinner.getSelectedItem().toString());
        Workout.Type type = Workout.Type.valueOf(workoutTypeSpinner.getSelectedItem().toString());

        // Set workout name:  eg. 2017/01/16 12:08
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.CANADA);
        String name = type.name() + " Workout: " + dateFormat.format(new Date());


        // Discern which muscle groups were selected
        for (CheckBox checkbox: checkboxes) {
            if (checkbox.isChecked()) {

                selectedMuscleGroups.add((String) checkbox.getText());
            }
        }


        // Create placeholder to potential exercise picks
        ArrayList<Exercise> potentialExercises = new ArrayList<>();


        // Important: Check each exercise, and add to potentialExercises if it matches criteria
        // This selection process accounts for type, difficulty, and target muscle groups
        for (Exercise exercise: exerciseList)
            if (    selectedMuscleGroups.contains(exercise.category) &&
                    (difficulty.toString().equals(exercise.difficulty) || type.toString().equals(exercise.motion)) &&
                    (!potentialExercises.contains(exercise)) )
                potentialExercises.add(exercise);



        // Use our devised formula to calculate an 'ideal' number of exercises
        // Important: If there are less exercises to choose from than 'ideal', return as many as we can
        int numberOfExercisesToGenerate = Math.min( (seekBarLength / 7), potentialExercises.size());
        Log.d("NUM", "Out of potential " + potentialExercises.size() + " picks, a " + seekBarLength +
                " min workout requires " + numberOfExercisesToGenerate + " exercises");

        // Use Collections shuffle() to rearrange, then take first 'numberOfExercisesToGenerate' elements
        Collections.shuffle(potentialExercises, new Random(System.nanoTime()));
        potentialExercises = new ArrayList<> (potentialExercises.subList(0, numberOfExercisesToGenerate));


        // TESTING: Print algorithm output
        for (Exercise e: potentialExercises) {
            Log.d("ALGORITHM", e.name);
        }

        return new Workout(name, potentialExercises, difficulty, type, seekBarLength);
    }
}
