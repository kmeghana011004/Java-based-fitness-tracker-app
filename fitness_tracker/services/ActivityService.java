package fitness_tracker.services;

import fitness_tracker.models.Activity;
import fitness_tracker.exceptions.InvalidActivityDataException;

import java.time.LocalDate;
import java.util.*;

public class ActivityService {
    private Map<String, List<Activity>> userActivities = new HashMap<>();

    public void logActivity(String userId, LocalDate date, int steps, int calories, String workoutType)
            throws InvalidActivityDataException {
        if (steps < 0 || calories < 0) {
            throw new InvalidActivityDataException("Steps and calories must be non-negative.");
        }

        Activity activity = new Activity(date, steps, calories, workoutType);
        userActivities.computeIfAbsent(userId, k -> new ArrayList<>()).add(activity);
    }

    public List<Activity> getActivities(String userId, LocalDate date) {
        return userActivities.getOrDefault(userId, new ArrayList<>()).stream()
                .filter(a -> a.getDate().equals(date))
                .toList();
    }
}
