import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hatfield Junior Swimming School System!");
        LessonBooking bookSwimmingLesson = new LessonBooking();
        Add_New_Learner learner = new Add_New_Learner();
        Print_Learner_Coach_Report report = new Print_Learner_Coach_Report(bookSwimmingLesson);
        while (true) {
        	System.out.println("\n Please Select the Below Option");
            System.out.println("\033[1m \n Please Select the Below Option  \033[0m");
            System.out.println("Press 1. Book a swimming lesson");
            System.out.println("Press 2. Cancel a booking / Change a booking");
            System.out.println("Press 3. Attend And Rate swimming lesson");
            System.out.println("Press 4. Print Monthly learner report");
            System.out.println("Press 5. Print Monthly coach report");
            System.out.println("Press 6. Register for a new learner");
            System.out.println("Press 7. Exit");

            System.out.print("\033[1m \n Please Enter your choice:  \033[0m ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookSwimmingLesson.viewTimetableCategories(1);
                    System.out.print("Please Enter your choice: ");
                    int timetableChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (timetableChoice) {
                        case 1:
                        	System.out.print("Enter the day: (e.g : Monday, Wednesday, Friday, Saturday ) : ");
                            String day = scanner.nextLine();
                            bookSwimmingLesson.displayTimetableByDay(day);
                            break;
                        case 2:
                            System.out.print("Please Enter the grade(1-5): ");
                            String grade = scanner.nextLine();
                            bookSwimmingLesson.displayTimetableByGrade(grade);
                            break;
                        case 3:
                            System.out.print("Please Enter the coach's name: (e.g : John, Sarah, Jacksparrow):");                     
                            String coach = scanner.nextLine();
                            bookSwimmingLesson.displayTimetableByCoach(coach);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                case 2:
                    // Change/Cancel a booking
                    Change_Cancel_Booking changeCancelBooking = new Change_Cancel_Booking(bookSwimmingLesson);
                    System.out.println("    Change/Cancel Lesson bookings:");
                    System.out.print("Please Enter learner's name: ");
                    String learnerName = scanner.nextLine();
                    System.out.println("Press 1. Change booking");
                    System.out.println("Press 2. Cancel booking");
                    System.out.print("Please Enter your choice: ");
                    int actionChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    switch (actionChoice) {
                        case 1:
                            changeCancelBooking.changeBooking(learnerName);
                            break;
                        case 2:
                            changeCancelBooking.cancelBooking(learnerName);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                case 3:
                    // Attend a swimming lesson
                    Attend_Review_Rating_Lesson attendLesson = new Attend_Review_Rating_Lesson(bookSwimmingLesson);
                    attendLesson.attendLesson();
                    break;
                case 4:
                    // Generate monthly learner report
                    List<String[]> bookedLessons = bookSwimmingLesson.getBookedLessons();
                    List<String[]> attendedLessons = bookSwimmingLesson.getAttendedLessons();
                    List<String[]> learners = bookSwimmingLesson.getLearners();
                    report.generateLearnerReport(bookedLessons, attendedLessons, learners);
                    break;
                case 5:
                    report.generateRandomCoachInfo();
                    report.generateCoachReport();
                    break;
                case 6:
                    // Register a new learner
                    System.out.print("Please Enter learner's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Please Enter learner's age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Please Enter learner's gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Please Enter learner's emergency contact phone number: ");
                    String emergencyContact = scanner.nextLine();
                    System.out.print("Please Enter learner's current grade level: ");
                    int gradeLevel = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    learner.registerNewLearner(name, age, gender, emergencyContact, gradeLevel);
                    break;
                case 7:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
