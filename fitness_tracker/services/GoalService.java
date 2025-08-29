package fitness_tracker.services;

import fitness_tracker.exceptions.GoalNotSetException;
import fitness_tracker.models.Activity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalService {
    private static class Goal {
        int stepGoal;
        int calorieGoal;

        Goal(int stepGoal, int calorieGoal) {
            this.stepGoal = stepGoal;
            this.calorieGoal = calorieGoal;
        }
    }

    private Map<String, Goal> userGoals = new HashMap<>();

    public void setDailyGoal(String userId, int steps, int calories) {
        userGoals.put(userId, new Goal(steps, calories));
    }

    public String checkDailyGoal(String userId, List<Activity> activities) throws GoalNotSetException {
        Goal goal = userGoals.get(userId);
        if (goal == null) {
            throw new GoalNotSetException("No goal set for user.");
        }

        int totalSteps = activities.stream().mapToInt(Activity::getStepsTaken).sum();
        int totalCalories = activities.stream().mapToInt(Activity::getCaloriesBurned).sum();

        return "Steps: " + totalSteps + "/" + goal.stepGoal +
               ", Calories: " + totalCalories + "/" + goal.calorieGoal +
               (totalSteps >= goal.stepGoal && totalCalories >= goal.calorieGoal ? " ✅ Goal met!" : " ❌ Goal not met.");
    }
}
