import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
public class Report {
    private Map<String, StringBuilder> coachReviews;
    private BookSwimmingLesson swimmingLesson;

    public Report(BookSwimmingLesson swimmingLesson) {
        this.swimmingLesson = swimmingLesson;
        this.coachReviews = new HashMap<>();
        // Initialize coach reviews with predefined data
        initializeCoachReviews();
    }

    private void initializeCoachReviews() {
        // Predefined coach reviews
        coachReviews.put("Coach_Gohar", new StringBuilder("Lesson: Monday_Lesson1, Learner: Learner1, Rating: 4\n"));
        coachReviews.put("Coach_Sajid", new StringBuilder("Lesson: Tuesday_Lesson2, Learner: Learner2, Rating: 5\n"));
        coachReviews.put("Coach_Aashar", new StringBuilder("Lesson: Wednesday_Lesson3, Learner: Learner3, Rating: 3\n"));
        coachReviews.put("Coach_Furqan", new StringBuilder("Lesson: Thursday_Lesson4, Learner: Learner4, Rating: 2\n"));

        // Generate random coach reviews
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int coachIndex = random.nextInt(coachReviews.size());
            String coachName = coachReviews.keySet().toArray(new String[0])[coachIndex];
            double rating = 1 + random.nextInt(5); // Generate random rating between 1 and 5
            StringBuilder reviews = coachReviews.get(coachName);
            reviews.append(String.format("Lesson %d: Rating %.1f\n", i + 1, rating));
            coachReviews.put(coachName, reviews);
        }
    }
    public void generateLearnerReport(List<String[]> bookedLessons, List<String[]> attendedLessons, List<String[]> learners) {
        StringBuilder learnerReport = new StringBuilder();
        learnerReport.append("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n");
        learnerReport.append("║                                                               Learner Report                                                                ║\n");
        learnerReport.append("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n");

        // Extract unique lesson topics
        Set<String> uniqueLessons = new HashSet<>();
        for (String[] lesson : bookedLessons) {
            uniqueLessons.add(lesson[1]);
        }
        for (String[] lesson : attendedLessons) {
            uniqueLessons.add(lesson[1]);
        }

        // Generate report header for lessons
        learnerReport.append("║");
        //this thing
        for (String lesson : uniqueLessons) {
//            learnerReport.append(String.fomat(" %-10s", lesson));
            learnerReport.append(String.format(" %-10s", lesson));
            learnerReport.append(" ║");
        }
        learnerReport.append(" ║\n");
        learnerReport.append("╠═══════════════════════════════");
        for (int i = 0; i < uniqueLessons.size(); i++) {
            learnerReport.append("══════════");
        }
        learnerReport.append("╣\n");

        // Populate report body with learner information
        for (String[] learner : learners) {
            String learnerName = learner[0];
            learnerReport.append(String.format("║ %-16s ║", learnerName));//learner name

            Map<String, String> lessonStatuses = new HashMap<>();
            for (String[] lesson : bookedLessons) {
                if (lesson[3].equals(learnerName)) {
                    lessonStatuses.put(lesson[1], "Attended");
                }
            }
            for (String[] lesson : attendedLessons) {
                if (lesson[3].equals(learnerName)) {
                    lessonStatuses.put(lesson[1], "Booked");
                }
            }

            for (String lesson : uniqueLessons) {
                String status = lessonStatuses.getOrDefault(lesson, "Cancelled");
//                String status = lessonStatuses.getOrDefault(lesson, "-");// Use "-" for missing lessons
                learnerReport.append(String.format(" %-10s", status));
            }

            learnerReport.append(" ║\n");
        }

        learnerReport.append("╚══════════════════╝\n");

        System.out.println(learnerReport.toString());
    }


//    public void generateLearnerReport(List<String[]> bookedLessons, List<String[]> attendedLessons, List<String[]> learners) {
//        StringBuilder learnerReport = new StringBuilder();
//        learnerReport.append("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n");
//        learnerReport.append("║                                                                                Print Learner Report                                                                                                                        ║\n");
//        learnerReport.append("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n");
//
//        // Initialize a map to track lesson status for each learner
//        Map<String, Map<String, String>> learnerLessons = new HashMap<>();
//
//        // Populate the map with lesson statuses for each learner
//        for (String[] learner : learners) {
//            String learnerName = learner[0];
//            Map<String, String> lessonStatuses = new HashMap<>();
//            for (String[] lesson : bookedLessons) {
//                if (lesson[3].equals(learnerName)) {
//                    lessonStatuses.put(lesson[1], "Attended");
//                }
//            }
//            for (String[] lesson : attendedLessons) {
//                if (lesson[3].equals(learnerName)) {
//                    lessonStatuses.put(lesson[1], "Booked");
//                }
//            }
//            learnerLessons.put(learnerName, lessonStatuses);
//        }
//
//        // Append learner information to the report
//        for (String[] learner : learners) {
//            String learnerName = learner[0];
//            learnerReport.append(String.format("║ Learner: %-20s", learnerName));
//            for (Map.Entry<String, String> entry : learnerLessons.get(learnerName).entrySet()) {
//                String lesson = entry.getKey();
//                String status = entry.getValue();
//                learnerReport.append(String.format(" %s: %-10s", lesson, status));
//            }
//            learnerReport.append(" ║\n");
//        }
//
//        // Append closing lines of the report
//        learnerReport.append("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n");
//
//        System.out.println(learnerReport.toString());
//    }

    public void generateRandomCoachInfo() {
        Random random = new Random();
        for (Map.Entry<String, StringBuilder> entry : coachReviews.entrySet()) {
            StringBuilder reviews = entry.getValue();
            // Add predefined coach reviews
            reviews.append(" Coach Reviews:\n");
            reviews.append("Lesson 1: Excellent teaching skills\n");
            reviews.append("Lesson 2: Very patient and helpful\n");
            reviews.append("Lesson 3: Clear instructions and feedback\n");

            // Add random statements for coach reviews
            reviews.append("Coach Reviews:\n");
            for (int i = 0; i < 3; i++) {
                String randomReview = generateRandomReview();
                reviews.append(randomReview).append("\n");
            }
        }
    }
    private String generateRandomReview() {
        String[] randomStatements = {
                "Great coach, very knowledgeable",
                "Enjoyed the lessons, learned a lot",
                "Helped me improve my technique",
                "Highly recommend this coach",
                "Patient and supportive instructor",
                "Fantastic experience, will definitely continue"
        };
        Random random = new Random();
        return "Lesson: " + (random.nextInt(10) + 1) + ": " + randomStatements[random.nextInt(randomStatements.length)];
    }

    // Updated method to generate coach report with random information
    public void generateCoachReport() {
        System.out.println("Coach Report\n");
        System.out.println("Coach\t\tAverage Rating\t\tReviews\n");

        // Append coach information and reviews to the report
        for (Map.Entry<String, StringBuilder> entry : coachReviews.entrySet()) {
            String coachName = entry.getKey();
            StringBuilder reviews = entry.getValue();
            double averageRating = calculateAverageRating(reviews.toString());

            // Format reviews to align with the table structure
            String formattedReviews = reviews.toString().replaceAll("(?m)^", "\t\t\t\t\t");

            // Display coach information and reviews
            System.out.printf("%s\t\t%.1f\t\t%s\n", coachName, averageRating, formattedReviews);
        }
    }

    private double calculateAverageRating(String reviews) {
        String[] lines = reviews.split("\n");
        double sum = 0;
        int count = 0;
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length >= 2) {
                try {
                    double rating = Double.parseDouble(parts[1].trim());
                    sum += rating;
                    count++;
                } catch (NumberFormatException e) {
                    // Ignore lines that don't contain a valid rating
                }
            }
        }
        if (count == 0) {
            return 0; // Return 0 if no valid ratings found
        }
        return sum / count;
    }
}



