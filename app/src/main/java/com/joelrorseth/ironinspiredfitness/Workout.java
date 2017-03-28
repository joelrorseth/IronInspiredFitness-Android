package com.joelrorseth.ironinspiredfitness;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Workout {

    public enum Difficulty {Easy, Moderate, Hard}

    public enum Type {Any, Core, Legs, Pull, Push}

    private String name;
    private ArrayList<Exercise> exercises;
    private Difficulty difficulty;
    private Type type;
    private double length;

    // ==============================================
    // ==============================================
    public Workout(String name, ArrayList<Exercise> exercises, Difficulty difficulty, Type type, double length) {
        this.name = name;
        this.exercises = exercises;
        this.difficulty = difficulty;
        this.type = type;
        this.length = length;
    }
}
//
//    // ==============================================
//    // ==============================================
//    protected Workout(Parcel in) {
//        name = in.readString();
//
//        if (in.readByte() == 0x01) {
//            exercises = new ArrayList<Exercise>();
//            in.readList(exercises, Exercise.class.getClassLoader());
//        } else {
//            exercises = null;
//        }
//
//        difficulty = (Difficulty) in.readValue(Difficulty.class.getClassLoader());
//        type = (Type) in.readValue(Type.class.getClassLoader());
//        length = in.readDouble();
//    }
//
//    // ==============================================
//    // ==============================================
//    @Override public int describeContents() {
//        return 0;
//    }
//
//    // ==============================================
//    // ==============================================
//    @Override public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        if (exercises == null) {
//            dest.writeByte((byte) (0x00));
//        } else {
//            dest.writeByte((byte) (0x01));
//            dest.writeList(exercises);
//        }
//        dest.writeValue(difficulty);
//        dest.writeValue(type);
//        dest.writeDouble(length);
//    }
//
//    @SuppressWarnings("unused")
//    // ==============================================
//    // ==============================================
//    public static final Parcelable.Creator<Workout> CREATOR = new Parcelable.Creator<Workout>() {
//        @Override
//        public Workout createFromParcel(Parcel in) {
//            return new Workout(in);
//        }
//
//        @Override
//        public Workout[] newArray(int size) {
//            return new Workout[size];
//        }
//    };
//}