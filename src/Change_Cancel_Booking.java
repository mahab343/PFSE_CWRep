import java.util.List;
import java.util.Scanner;

public class Change_Cancel_Booking {
    private LessonBooking swimmingLesson;

    public Change_Cancel_Booking(LessonBooking swimmingLesson) {
        this.swimmingLesson = swimmingLesson;
    }

    public void cancelBooking(String learnerName) {
        List<String[]> bookedLessons = swimmingLesson.getBookedLessons();
        boolean found = false;
        for (String[] lesson : bookedLessons) {
            if (lesson[3].equalsIgnoreCase(learnerName)) {
                bookedLessons.remove(lesson);
                found = true;
                System.out.println("Booking canceled successfully for learner: " + learnerName);
                break;
            }
        }
        if (!found) {
            System.out.println("No booking found for learner: " + learnerName);
        }
    }

    public void changeBooking(String learnerName) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> bookedLessons = swimmingLesson.getBookedLessons();
        boolean found = false;
        for (String[] lesson : bookedLessons) {
            if (lesson[3].equalsIgnoreCase(learnerName)) {
                found = true;
                System.out.println("Current booking for learner: " + learnerName);
                System.out.println("Day: " + lesson[0] + ", Lesson: " + lesson[1] + ", Grade: " + lesson[2]);
                System.out.println("Enter the new booking details:");
                swimmingLesson.viewTimetableCategories(1); // Display options to view timetable
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        System.out.print("Enter the new day: ");
                        String newDay = scanner.nextLine();
                        swimmingLesson.displayTimetableByDay(newDay);
                        // Assuming the learner can only change the day for now
                        lesson[0] = newDay;
                        break;
                    // Implement cases for grade level and coach's name if needed
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
                break;
            }
        }
        if (!found) {
            System.out.println("No booking found for learner: " + learnerName);
        }
    }
}
