package com.joelrorseth.ironinspiredfitness;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Exercise implements Parcelable {

    public String name;
    public String description;
    public String imageUrl;
    public String motion;
    public String difficulty;
    public String category;

    private static final ArrayList<String> exerciseCategories = new ArrayList<>();

    // ==============================================
    // ==============================================
    public static ArrayList<Exercise> getExercisesFromFile(String filename, Context context) {

        exerciseCategories.add("Back");
        exerciseCategories.add("Biceps");
        exerciseCategories.add("Calves");
        exerciseCategories.add("Chest");
        exerciseCategories.add("Core");
        exerciseCategories.add("Forearms");
        exerciseCategories.add("Hamstrings");
        exerciseCategories.add("Shoulders");
        exerciseCategories.add("Traps");
        exerciseCategories.add("Triceps");
        exerciseCategories.add("Quads");


        final ArrayList<Exercise> exercisesList = new ArrayList<>();

        try {
            // Load exercises from the JSON file in assets folder
            String jsonString = loadJsonFromFile("exercises.json", context);
            JSONObject json = new JSONObject(jsonString);

            // Use exerciseCategories to go through each separated JSONArray of exercises
            for (String muscleGroup: exerciseCategories) {
                JSONArray categoryExercises = json.getJSONArray(muscleGroup);

                // Go through each exercise record in this category
                for (int i = 0; i < categoryExercises.length(); ++i) {
                    Exercise exercise = new Exercise();

                    // Set properties of the current Exercise record
                    exercise.name = categoryExercises.getJSONObject(i).getString("name");
                    exercise.description = categoryExercises.getJSONObject(i).getString("desc");
                    exercise.imageUrl = categoryExercises.getJSONObject(i).getString("image");
                    exercise.motion = categoryExercises.getJSONObject(i).getString("motion");
                    exercise.difficulty = categoryExercises.getJSONObject(i).getString("difficulty");
                    exercise.category = muscleGroup;

                    // Add exercise to the ArrayList of Exercises
                    exercisesList.add(exercise);
                }

            }

        } catch (JSONException e) {
            Log.d("LOAD_JSON_ERR_1", ("Could not create JSONObject or JSONArray for " + filename));
            e.printStackTrace();
        }

        return exercisesList;
    }

    // ==============================================
    // ==============================================
    private static String loadJsonFromFile(String filename, Context context) {
        String jsonString = null;
        try {

            // Open the JSON file (in assets folder) as a stream
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];

            // Read JSON file into byte buffer
            is.read(buffer);
            is.close();

            // Put the entire JSON file into a String
            jsonString = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            Log.d("LOAD_JSON_ERR_2", ("Could not open input stream for " + filename));
            ex.printStackTrace();
            return null;
        }

        return jsonString;
    }

    public Exercise() {}

    protected Exercise(Parcel in) {
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        motion = in.readString();
        difficulty = in.readString();
        category = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(motion);
        dest.writeString(difficulty);
        dest.writeString(category);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Exercise> CREATOR = new Parcelable.Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };
}