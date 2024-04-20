import java.util.ArrayList;
import java.util.List;

public class Learner {
    private List<String[]> learners;

    public Learner() {
        learners = new ArrayList<>();
    }

    public void registerNewLearner(String name, int age, String gender, String emergencyContact, int grade) {
        String[] learnerData = {name, Integer.toString(age), gender, emergencyContact, Integer.toString(grade)};
        learners.add(learnerData);
        System.out.println("Learner registered successfully.");
    }

    public String[] getLearnerDataByName(String name) {
        for (String[] learner : learners) {
            if (learner[0].equalsIgnoreCase(name)) {
                return learner;
            }
        }
        return null;
    }

    public void incrementGrade(String[] learnerData) {
        int grade = Integer.parseInt(learnerData[4]);
        if (grade < 5) {
            grade++;
            learnerData[4] = Integer.toString(grade);
        }
    }

    public void addAttendedLesson(String learnerName) {
        for (String[] learner : learners) {
            if (learner[0].equalsIgnoreCase(learnerName)) {
                learner[5] = "true"; // Marking lesson as attended
                System.out.println("Lesson attendance recorded for learner: " + learnerName);
                break;
            }
        }
    }
}
