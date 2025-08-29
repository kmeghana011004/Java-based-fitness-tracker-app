package fitness_tracker.main;

import fitness_tracker.exceptions.GoalNotSetException;
import fitness_tracker.exceptions.InvalidActivityDataException;
import fitness_tracker.models.User;
import fitness_tracker.services.ActivityService;
import fitness_tracker.services.GoalService;

import java.time.LocalDate;
import java.util.*;

public class FitnessApp {
    private static Map<String, User> users = new HashMap<>();
    private static ActivityService activityService = new ActivityService();
    private static GoalService goalService = new GoalService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Fitness Tracker ---");
            System.out.println("1. Add User");
            System.out.println("2. Log Activity");
            System.out.println("3. Set Daily Goal");
            System.out.println("4. View Daily Summary");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (option) {
                    case 1 -> addUser();
                    case 2 -> logActivity();
                    case 3 -> setGoal();
                    case 4 -> viewSummary();
                    case 5 -> {
                        System.out.println("Exiting..."); return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Weight (kg): ");
        double weight = scanner.nextDouble();
        scanner.nextLine();

        users.put(userId, new User(userId, name, age, weight));
        System.out.println("User added successfully.");
    }

    private static void logActivity() throws InvalidActivityDataException {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Steps Taken: ");
        int steps = scanner.nextInt();
        System.out.print("Calories Burned: ");
        int calories = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Workout Type: ");
        String workoutType = scanner.nextLine();

        activityService.logActivity(userId, date, steps, calories, workoutType);
        System.out.println("Activity logged.");
    }

    private static void setGoal() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Step Goal: ");
        int steps = scanner.nextInt();
        System.out.print("Calorie Goal: ");
        int calories = scanner.nextInt();
        scanner.nextLine();

        goalService.setDailyGoal(userId, steps, calories);
        System.out.println("Goal set successfully.");
    }

    private static void viewSummary() throws GoalNotSetException {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        var activities = activityService.getActivities(userId, date);
        if (activities.isEmpty()) {
            System.out.println("No activity for the given date.");
        } else {
            activities.forEach(a -> System.out.println(a.getWorkoutType() + ": " +
                a.getStepsTaken() + " steps, " + a.getCaloriesBurned() + " cal"));
            System.out.println(goalService.checkDailyGoal(userId, activities));
        }
    }
}
