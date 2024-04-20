import java.util.Scanner;

public class AttendLesson {
    private BookSwimmingLesson swimmingLesson;

    public AttendLesson(BookSwimmingLesson swimmingLesson) {
        this.swimmingLesson = swimmingLesson;
    }

    public void attendLesson() {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the name of the learner
        System.out.print("Enter the name of the learner: ");
        String learnerName = scanner.nextLine();

        // Display booked lessons for the specified learner
        boolean foundBookedLesson = false;
        for (String[] lesson : swimmingLesson.getBookedLessons()) {
            if (lesson[3].equalsIgnoreCase(learnerName)) { // Check if the learner's name matches
                System.out.println("Lesson attended by learner: " + learnerName);
                foundBookedLesson = true;

                // Ask for review and rating
                System.out.print("Write a review of the lesson: ");
                String review = scanner.nextLine();
                System.out.print("Rate the lesson from 1 to 5 (1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied): ");
                int rating = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Thanks for giving Review :)");

                // Get lesson details
                String day = lesson[0];
                String grade = lesson[2];
                String lessonNumber = lesson[1].substring(lesson[1].indexOf("_Lesson") + 7);

                // Store the review and rating
                swimmingLesson.addReview(day, grade, lessonNumber, review, rating);

                // Store coach review and rating
                String coachName = lesson[4];
                swimmingLesson.addCoachReview(coachName, review, rating);

                // Upgrade learner's grade
                int currentGrade = Integer.parseInt(grade);
                int newGrade = currentGrade + 1;
                System.out.println("Congratulations! Your grade has been upgraded from " + currentGrade + " to " + newGrade);
                swimmingLesson.upgradeLearnerGrade(learnerName, Integer.toString(newGrade));
            }
        }

        if (!foundBookedLesson) {
            System.out.println("No booked lessons found for learner: " + learnerName);
        }
    }
}
