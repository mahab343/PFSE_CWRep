import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Learner {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int currentGradeLevel;
    Map<Lesson, Integer> lessonReviews;

    public Learner(String name, String gender, int age, String emergencyContact, int currentGradeLevel) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.currentGradeLevel = currentGradeLevel;
        this.lessonReviews = new HashMap<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getCurrentGradeLevel() {
        return currentGradeLevel;
    }

    public void setCurrentGradeLevel(int currentGradeLevel) {
        this.currentGradeLevel = currentGradeLevel;
    }

    // Method to add review for a lesson
    public void addReview(Lesson lesson, int rating) {
        lessonReviews.put(lesson, rating);
    }

    // Method to get average rating for all lessons
    public double getAverageRating() {
        if (lessonReviews.isEmpty())
            return 0.0;
        double sum = 0.0;
        for (int rating : lessonReviews.values()) {
            sum += rating;
        }
        return sum / lessonReviews.size();
    }

    // toString method
    @Override
    public String toString() {
        return name + " (Grade " + currentGradeLevel + ")";
    }
}

class Coach {
    private String name;

    public Coach(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }
}

class Lesson {
    private String day;
    private String time;
    private int gradeLevel;
    private Coach coach;
    private int capacity;
    private ArrayList<Learner> learners;

    public Lesson(String day, String time, int gradeLevel, Coach coach, int capacity) {
        this.day = day;
        this.time = time;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.capacity = capacity;
        this.learners = new ArrayList<>();
    }

    // Getters and setters
    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Learner> getLearners() {
        return learners;
    }

    // Adding learner to the lesson
    public boolean addLearner(Learner learner) {
        if (learners.size() < capacity) {
            learners.add(learner);
            return true;
        }
        return false;
    }

    // Removing learner from the lesson
    public boolean removeLearner(Learner learner) {
        return learners.remove(learner);
    }
}

public class SwimmingSchoolManagementSystem {
    private ArrayList<Learner> learners;
    private ArrayList<Coach> coaches;
    private ArrayList<Lesson> lessons;
    private Scanner scanner;

    public SwimmingSchoolManagementSystem() {
        this.learners = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        preloadData(); // Preload data
    }

    // Data loading method
    private void preloadData() {
        // Learners
        learners.add(new Learner("Emma Johnson", "Male", 6, "1234567890", 1));
        learners.add(new Learner("Liam Smith", "Female", 8, "0987654321", 3));
        learners.add(new Learner("Olivia Williams", "Male", 5, "1357924680", 2));
        learners.add(new Learner("Noah Brown", "Male", 7, "1234543210", 1));
        learners.add(new Learner("Ava Jones", "Female", 9, "0987612345", 2));
        learners.add(new Learner("William Davis", "Male", 6, "1357987654", 3));
        learners.add(new Learner("Sophia Taylor", "Female", 10, "1234569870", 4));
        learners.add(new Learner("James Wilson", "Male", 8, "0987654321", 2));
        learners.add(new Learner("Isabella Martinez", "Male", 11, "1357924680", 5));
        learners.add(new Learner("Oliver Anderson", "Female", 5, "1234509876", 1));
        learners.add(new Learner("Mia Rodriguez", "Female", 7, "0987612345", 2));

        // Coaches
        coaches.add(new Coach("John"));
        coaches.add(new Coach("Sarah"));
        coaches.add(new Coach("Jacksparrow"));

        // Lessons
        // Monday
        lessons.add(new Lesson("Monday", "4-5pm", 1, coaches.get(0), 4));
        lessons.add(new Lesson("Monday", "5-6pm", 2, coaches.get(1), 4));
        lessons.add(new Lesson("Monday", "6-7pm", 3, coaches.get(2), 4));
        // Wednesday
        lessons.add(new Lesson("Wednesday", "4-5pm", 2, coaches.get(0), 4));
        lessons.add(new Lesson("Wednesday", "5-6pm", 3, coaches.get(1), 4));
        lessons.add(new Lesson("Wednesday", "6-7pm", 4, coaches.get(2), 4));
        // Friday
        lessons.add(new Lesson("Friday", "4-5pm", 3, coaches.get(0), 4));
        lessons.add(new Lesson("Friday", "5-6pm", 4, coaches.get(1), 4));
        lessons.add(new Lesson("Friday", "6-7pm", 5, coaches.get(2), 4));
        // Saturday
        lessons.add(new Lesson("Saturday", "2-3pm", 1, coaches.get(0), 4));
        lessons.add(new Lesson("Saturday", "3-4pm", 5, coaches.get(1), 4));
    }

    // Method to book a lesson for a learner
    public boolean bookLesson(Learner learner, Lesson lesson) {
        // Checking if learner's current grade level matches or is one level below the lesson grade level
        if (lesson.getGradeLevel() - learner.getCurrentGradeLevel() <= 1) {
            // Checking if there is space available in the lesson
            if (lesson.getLearners().size() < lesson.getCapacity()) {
                // Adding learner to the lesson
                lesson.addLearner(learner);
                // Updating learner's current grade level if necessary
                if (learner.getCurrentGradeLevel() < lesson.getGradeLevel()) {
                    learner.setCurrentGradeLevel(lesson.getGradeLevel());
                }
                return true;
            } else {
                System.out.println("Sorry, the lesson is already full.");
            }
        } else {
            System.out.println("You can only book a lesson of your current grade level or one level higher.");
        }
        return false;
    }

    // Method to cancel a lesson booking for a learner
    public boolean cancelLesson(Learner learner, Lesson lesson) {
        return lesson.removeLearner(learner);
    }

    // Method to allow learners to provide ratings and reviews for lessons
    public void provideRatingAndReview(Learner learner) {
        System.out.println("Lessons attended by " + learner.getName() + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getLearners().contains(learner)) {
                System.out.println("- " + lesson.getDay() + ", " + lesson.getTime() +
                        " with Coach " + lesson.getCoach().getName());
                System.out.print("Enter rating (1-5) for this lesson: ");
                int rating = scanner.nextInt();
                learner.addReview(lesson, rating);
            }
        }
    }

    // Method to print detailed information of each learner
    public void printLearnerInfo() {
        System.out.println("Detailed information of each learner:");
        for (Learner learner : learners) {
            System.out.println("Learner: " + learner.getName());
            System.out.println("Grade Level: " + learner.getCurrentGradeLevel());
            System.out.println("Lessons booked:");
            for (Lesson lesson : lessons) {
                if (lesson.getLearners().contains(learner)) {
                    System.out.println("- " + lesson.getDay() + ", " + lesson.getTime() +
                            " with Coach " + lesson.getCoach().getName());
                }
            }
            System.out.println("Average Rating: " + learner.getAverageRating());
            System.out.println();
        }
    }

    // Method to print coach information and average ratings received
    public void printCoachInfo() {
        System.out.println("Coach Information:");
        for (Coach coach : coaches) {
            double totalRating = 0;
            int lessonCount = 0;
            for (Lesson lesson : lessons) {
                if (lesson.getCoach().equals(coach)) {
                    lessonCount++;
                    for (Learner learner : lesson.getLearners()) {
                        if (learner.lessonReviews.containsKey(lesson)) {
                            totalRating += learner.lessonReviews.get(lesson);
                        }
                    }
                }
            }
            double averageRating = lessonCount > 0 ? totalRating / lessonCount : 0;
            System.out.println("Coach: " + coach.getName());
            System.out.println("Average Rating Received: " + averageRating);
            System.out.println();
        }
    }

    // Method to display menu
    public void displayMenu() {
        System.out.println("Welcome to Hatfield Junior Swimming School Management System");
        System.out.println("1. Book Lesson");
        System.out.println("2. Cancel Lesson");
        System.out.println("3. Provide Ratings and Reviews");
        System.out.println("4. Print Learner Information");
        System.out.println("5. Print Coach Information");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    // Main method for menu-driven system
    public static void main(String[] args) {
        SwimmingSchoolManagementSystem system = new SwimmingSchoolManagementSystem();

        while (true) {
            system.displayMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Book Lesson
                    System.out.print("Enter learner's name: ");
                    String learnerName = scanner.nextLine();
                    Learner learner = null;
                    for (Learner l : system.learners) {
                        if (l.getName().equalsIgnoreCase(learnerName)) {
                            learner = l;
                            break;
                        }
                    }
                    if (learner == null) {
                        System.out.println("Learner not found.");
                        break;
                    }

                    System.out.print("Enter day of the lesson: ");
                    String day = scanner.nextLine();
                    System.out.print("Enter time of the lesson: ");
                    String time = scanner.nextLine();

                    Lesson selectedLesson = null;
                    for (Lesson lesson : system.lessons) {
                        if (lesson.getDay().equalsIgnoreCase(day) && lesson.getTime().equalsIgnoreCase(time)) {
                            selectedLesson = lesson;
                            break;
                        }
                    }
                    if (selectedLesson == null) {
                        System.out.println("Lesson not found.");
                        break;
                    }

                    boolean booked = system.bookLesson(learner, selectedLesson);
                    if (booked) {
                        System.out.println("Lesson booked successfully!");
                    } else {
                        System.out.println("Failed to book the lesson.");
                    }
                    break;

                case 2:
                    // Cancel Lesson
                    System.out.print("Enter learner's name: ");
                    learnerName = scanner.nextLine();
                    learner = null;
                    for (Learner l : system.learners) {
                        if (l.getName().equalsIgnoreCase(learnerName)) {
                            learner = l;
                            break;
                        }
                    }
                    if (learner == null) {
                        System.out.println("Learner not found.");
                        break;
                    }

                    System.out.print("Enter day of the lesson: ");
                    day = scanner.nextLine();
                    System.out.print("Enter time of the lesson: ");
                    time = scanner.nextLine();

                    selectedLesson = null;
                    for (Lesson lesson : system.lessons) {
                        if (lesson.getDay().equalsIgnoreCase(day) && lesson.getTime().equalsIgnoreCase(time)) {
                            selectedLesson = lesson;
                            break;
                        }
                    }
                    if (selectedLesson == null) {
                        System.out.println("Lesson not found.");
                        break;
                    }

                    boolean cancelled = system.cancelLesson(learner, selectedLesson);
                    if (cancelled) {
                        System.out.println("Lesson cancelled successfully!");
                    } else {
                        System.out.println("Failed to cancel the lesson.");
                    }
                    break;

                case 3:
                    // Provide Ratings and Reviews
                    System.out.print("Enter learner's name: ");
                    learnerName = scanner.nextLine();
                    learner = null;
                    for (Learner l : system.learners) {
                        if (l.getName().equalsIgnoreCase(learnerName)) {
                            learner = l;
                            break;
                        }
                    }
                    if (learner == null) {
                        System.out.println("Learner not found.");
                        break;
                    }
                    system.provideRatingAndReview(learner);
                    break;

                case 4:
                    // Learner Information
                    system.printLearnerInfo();
                    break;

                case 5:
                    // Coach Information
                    system.printCoachInfo();
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

