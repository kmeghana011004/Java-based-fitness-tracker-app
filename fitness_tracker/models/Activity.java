package fitness_tracker.models;

import java.time.LocalDate;

public class Activity {
    private LocalDate date;
    private int stepsTaken;
    private int caloriesBurned;
    private String workoutType;

    public Activity(LocalDate date, int stepsTaken, int caloriesBurned, String workoutType) {
        this.date = date;
        this.stepsTaken = stepsTaken;
        this.caloriesBurned = caloriesBurned;
        this.workoutType = workoutType;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getStepsTaken() {
        return stepsTaken;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getWorkoutType() {
        return workoutType;
    }
}
