package com.joelrorseth.ironinspiredfitness;

public class Workout {

    public enum Difficulty { Easy, Moderate, Hard }
    public enum WorkoutType { Any, Core, Legs, Pull, Push }

    private String name;
    private Exercise[] exercises;
    private Difficulty difficulty;
    private WorkoutType type;
    private double length;

    // ==============================================
    // ==============================================
    public Workout(String name, Exercise[] exercises, Difficulty difficulty, WorkoutType type, double length ) {
        this.name = name;
        this.exercises = exercises;
        this.difficulty = difficulty;
        this.type = type;
        this.length = length;
    }
}
