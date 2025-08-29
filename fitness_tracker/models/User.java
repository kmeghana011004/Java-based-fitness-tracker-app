package fitness_tracker.models;

public class User {
    private String userId;
    private String name;
    private int age;
    private double weight;

    public User(String userId, String name, int age, double weight) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }
}
