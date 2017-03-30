package com.joelrorseth.ironinspiredfitness;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Parcelable, Serializable {

    public String name;
    public String description;
    public String imageUrl;
    public String motion;
    public String difficulty;
    public String category;

    // ==============================================
    // ==============================================
    @Override public String toString() {
        return name;
    }

    // ==============================================
    // ==============================================
    public Exercise() {}

    // ==============================================
    // ==============================================
    protected Exercise(Parcel in) {
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        motion = in.readString();
        difficulty = in.readString();
        category = in.readString();
    }

    // ==============================================
    // ==============================================
    @Override public int describeContents() {
        return 0;
    }

    // ==============================================
    // ==============================================
    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(motion);
        dest.writeString(difficulty);
        dest.writeString(category);
    }

    // ==============================================
    // ==============================================
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