import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
public class BookSwimmingLesson {
    private static final String[] GRADE_LEVELS = {"1", "2", "3", "4", "5"};
    private static final String[] DAYS = {"Monday", "Wednesday", "Friday", "Saturday"};
    private static final String[] COACHES = {"John", "Sarah", "Jacksparrow","Alina"};
    private static final String[] TIME = {"4-5pm", "5-6pm", "6-7pm"};
//    private static final String[] COACHES = {"Coach_Gohar", "Coach_Sajid", "Coach_Aashar", "Coach_Furqan"};
    private static final int LESSON_VACANCY_LIMIT = 4;
    private static final int LESSONS_PER_WEEKDAY = 3;
    private static final int LESSONS_ON_SATURDAY = 2;

    private List<String[]> bookedLessons;
    private List<String[]> attendedLessons;
    private List<String[]> learners;
    private List<String[]> lessonReviews;
    private Map<String, List<String>> coachReviews;
    private String[][][][] timetable;
    private int[][][] vacancies;

    public List<String[]> getBookedLessons() {
        return bookedLessons;
    }

    public List<String[]> getAttendedLessons() {
        return attendedLessons;
    }

    public List<String[]> getLessonReviews() {
        return lessonReviews;
    }
    public List<String[]> getLearners() {
        return learners;
    }

    public void addCoachReview(String coachName, String review, double rating) {
        if (!coachReviews.containsKey(coachName)) {
            coachReviews.put(coachName, new ArrayList<>());
        }
        List<String> reviews = coachReviews.get(coachName);
        reviews.add(review);
        // You can optionally store the rating as well
    }
    public BookSwimmingLesson() {
        bookedLessons = new ArrayList<>();
        attendedLessons = new ArrayList<>();
        learners = new ArrayList<>();
        lessonReviews = new ArrayList<>();
        coachReviews = new HashMap<>();
        initializeTimetable();
        initializeVacancies();
        initializeLearners();
    }

    private void initializeTimetable() {
        timetable = new String[4][DAYS.length][][];
        for (int week = 0; week < 4; week++) {
            for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                int lessonsPerDay = (dayIndex == DAYS.length - 1) ? LESSONS_ON_SATURDAY : LESSONS_PER_WEEKDAY;
                timetable[week][dayIndex] = new String[lessonsPerDay][];
                for (int lessonIndex = 0; lessonIndex < lessonsPerDay; lessonIndex++) {
                    String coach = COACHES[lessonIndex % COACHES.length];
                    String time = TIME[lessonIndex];
                    String date = (week * 7 + dayIndex + 1) +"-04-2024" ;
             
                    String lessonName = "Grade" + (dayIndex + 1) + "_&&_Lesson" + (lessonIndex + 1);
                    timetable[week][dayIndex][lessonIndex] = new String[]{date, lessonName, coach, time};
                    //index here
                }
            }
        }
    }

    private void initializeVacancies() {
        vacancies = new int[4][DAYS.length][];
        for (int week = 0; week < 4; week++) {
            for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                int lessonsPerDay = (dayIndex == DAYS.length - 1) ? LESSONS_ON_SATURDAY : LESSONS_PER_WEEKDAY;
                vacancies[week][dayIndex] = new int[lessonsPerDay];
                for (int lessonIndex = 0; lessonIndex < lessonsPerDay; lessonIndex++) {
                    vacancies[week][dayIndex][lessonIndex] = LESSON_VACANCY_LIMIT;
                }
            }
        }
    }

    public void registerNewLearner(String name, int age, String gender, String emergencyContact, int gradeLevel) {
        learners.add(new String[]{name, Integer.toString(age), gender, emergencyContact, Integer.toString(gradeLevel)});
    }


    private void initializeLearners() {
        Random rand = new Random();
        String[] maleNames = {"James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Charles", "Thomas"};
        String[] femaleNames = {"Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy"};

        for (int i = 0; i < 10; i++) {
            String name;
            String gender;
            if (rand.nextBoolean()) {
                name = maleNames[rand.nextInt(maleNames.length)];
                gender = "Male";
            } else {
                name = femaleNames[rand.nextInt(femaleNames.length)];
                gender = "Female";
            }
            int age = 6 + (rand.nextInt(4));
            String emergencyContact = "123456789" + i;
            String grade = GRADE_LEVELS[rand.nextInt(GRADE_LEVELS.length)];
            String[] learnerData = {name, gender, Integer.toString(age), emergencyContact, grade};
            learners.add(learnerData);
            for (int week = 0; week < 4; week++) {
                for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                    for (int lessonIndex = 0; lessonIndex < timetable[week][dayIndex].length; lessonIndex++) {
                        if (Math.random() < 0.3) {
                            String[] lessonData = timetable[week][dayIndex][lessonIndex];
                            bookLesson(name, lessonData[0], lessonData[1], lessonData[2], lessonData[3], week, dayIndex, lessonIndex);
                        }
                    }
                }
            }
        }
    }

    public void viewTimetableCategories(int choice) {
        switch (choice) {
            case 1:
            	System.out.println("Choose how you want to book lesson");
                System.out.println("1. Timetable view by date");
                System.out.println("2. Timetable view by Grade level(1-5)");
                System.out.println("3. Timetable view by Coach Name");
                break;
        }
    }

    public void displayTimetableByDay(String day) {
        int dayIndex = getDayIndex(day);
        if (dayIndex != -1) {
            System.out.println("Timetable for " + day + ":");
            for (int week = 0; week < 4; week++) {
                System.out.println("Week " + (week + 1) + ":");
                for (String[] lesson : timetable[week][dayIndex]) {
                    int vacancies = getVacancies(week, dayIndex, lesson[1]);
                    System.out.println("       Date: " + lesson[0] + ",Lesson " + lesson[1] + ",Coach: " + lesson[2] + ",Time: " + lesson[3] + ",Vacancies: " + vacancies);
                }
            }
            bookLessonByDay(day);
        } else {
            System.out.println("Invalid day.");
        }
    }

    public void displayTimetableByGrade(String grade) {
        
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Timetable for Grade " + grade + ":");
        System.out.println("----------------------------------------------------------------------------------------------------------");
       
        int currentGrade = Integer.parseInt(grade);
        for (int week = 0; week < 4; week++) {
            for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                for (String[] lesson : timetable[week][dayIndex]) {
                    int lessonGrade = Integer.parseInt(lesson[1].substring(5, 6));
                    if (lessonGrade == currentGrade || lessonGrade == currentGrade + 1) {
                        int vacancies = getVacancies(week, dayIndex, lesson[1]);
                        System.out.println("       Date: " + lesson[0] + ",Lesson " + lesson[1] + ",Coach: " + lesson[2] + ",Time: " + lesson[3] + ",Vacancies: " + vacancies);
                        
                    } 
                }
                
            }
            System.out.println(" ");
        }
        bookLessonByGrade(grade);
    }

    public void upgradeLearnerGrade(String learnerName, String newGrade) {
        for (String[] learner : learners) {
            if (learner[0].equalsIgnoreCase(learnerName)) {
                learner[4] = newGrade;
                return;
            }
        }
    }

    public void displayTimetableByCoach(String coach) {
    	 System.out.println("----------------------------------------------------------------------------------------------------------");
    	 System.out.println("Timetable for Coach " + coach + ":");
         System.out.println("----------------------------------------------------------------------------------------------------------");

        for (int week = 0; week < 4; week++) {
            for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                for (String[] lesson : timetable[week][dayIndex]) {
                    if (lesson[2].equals(coach)) {
                        int vacancies = getVacancies(week, dayIndex, lesson[1]);
                        System.out.println("       Date: " + lesson[0] + ",Lesson " + lesson[1] + ",Grade: " + getGrade(lesson[1]) + ",Time: " + lesson[3] + ",Vacancies: " + vacancies);
                    
                    }
                }
            }
            System.out.println(" ");
        }
        bookLessonByCoach(coach);
    }

    public void addReview(String day, String grade, String lessonNumber, String review, int rating) {
        String[] reviewData = {day, grade, lessonNumber, review, String.valueOf(rating)};
        lessonReviews.add(reviewData);
    }

    private void bookLesson(String name, String date, String lesson, String coach, String time, int week, int dayIndex, int lessonIndex) {
        String grade = lesson.substring(5, 6);
        String[] lessonData = {date, lesson, grade, name, coach, time}; // Create array of strings
        bookedLessons.add(lessonData); // Add array of strings to bookedLessons
        vacancies[week][dayIndex][lessonIndex]--;
    }

    private void bookLessonByDay(String day) {
        Scanner scanner = new Scanner(System.in);
        int dayIndex = getDayIndex(day);
        if (dayIndex == -1) {
            System.out.println("Invalid day.");
            return;
        }

        System.out.print("Please Enter the week number: ");
        int week = scanner.nextInt();
        week--; //  week no array index adjusting

        if (week < 0 || week >= 4) {
            System.out.println("Invalid week number.");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Timetable for " + day + " (Week " + (week + 1) + "):");
        System.out.println("----------------------------------------------------------------------------------------------------------");
       
        for (String[] lesson : timetable[week][dayIndex]) {
            int vacancies = getVacancies(week, dayIndex, lesson[1]);
            System.out.println("       Date: " + lesson[0] + ",Lesson: " + lesson[1] + ",Coach: " + lesson[2] + ",Time: " + lesson[3] + ",Vacancies: " + vacancies);
        }

        System.out.print("Please Enter the lesson number to book: ");
        int lessonNumber = scanner.nextInt();
        if (lessonNumber < 1 || lessonNumber > LESSONS_PER_WEEKDAY) {
            System.out.println("Invalid lesson number.");
            return;
        }

        System.out.print("Please Enter the grade: ");
        String grade = scanner.next();
        if (!isValidGrade(grade)) {
            System.out.println("Invalid grade.");
            return;
        }

        System.out.print("Please Enter learner's name: ");
        String name = scanner.next();

        System.out.print("Please Enter learner's gender: ");
        String gender = scanner.next();

        System.out.print("Please Enter learner's age: ");
        int age = scanner.nextInt();
        if (!isValidAge(age)) {
            System.out.println("Invalid age.");
            return;
        }

        System.out.print("Please Enter learner's emergency contact phone number: ");
        String emergencyContact = scanner.next();

        if (vacancies[week][dayIndex][lessonNumber - 1] <= 0) {
            System.out.println("No vacancies available for this lesson.");
            return;
        }

        String[] lessonData = timetable[week][dayIndex][lessonNumber - 1];
        bookLesson(name, lessonData[0], lessonData[1], lessonData[2], lessonData[3], week, dayIndex, lessonNumber - 1);

        String[] learnerData = {name, gender, Integer.toString(age), emergencyContact, grade};
        learners.add(learnerData);

        System.out.println("You have Successfully Booked the Lesson.");
    }

    private void bookLessonByGrade(String grade) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter the day(e.g: Monday,...) to book a lesson for Grade: " + grade + ":");
        String day = scanner.nextLine();
        displayTimetableByDay(day);
    }

    private void bookLessonByCoach(String coach) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter the day(e.g: Monday,...) to book a lesson with Coach: " + coach + ":");
        String day = scanner.nextLine();
        displayTimetableByDay(day);
    }

    private boolean isValidGrade(String grade) {
        for (String level : GRADE_LEVELS) {
            if (level.equals(grade)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidAge(int age) {
        return age >= 4 && age <= 11;
    }

    private int getDayIndex(String day) {
        for (int i = 0; i < DAYS.length; i++) {
            if (DAYS[i].equalsIgnoreCase(day)) {
                return i;
            }
        }
        return -1;
    }

    private int getWeekFromDate(String day) {
        // Mapping days to week numbers
        switch (day.toLowerCase()) {
            case "monday":
                return 1;
            case "wednesday":
                return 2;
            case "friday":
                return 3;
            case "saturday":
                return 4;
            default:
                System.out.println("Invalid day.");
                return -1;
        }
    }
    public void bookLesson(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Booking a swimming lesson for learner: " + name);

        System.out.print("Enter the day: ");
        String day = scanner.nextLine();
        displayTimetableByDay(day);

        System.out.print("Please Enter the lesson number to book: ");
        int lessonNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (lessonNumber < 1 || lessonNumber > 3) {
            System.out.println("Invalid lesson number.");
            return;
        }

        String[] lessonData = timetable[0][getDayIndex(day)][lessonNumber - 1];
        bookLesson(name, lessonData[0], lessonData[1], lessonData[2], lessonData[3], 0, getDayIndex(day), lessonNumber - 1);
        System.out.println("You have Successfully Booked the Lesson.");
    }

    private String getGrade(String lesson) {
        return lesson.substring(5, 6);
    }

    private int getVacancies(int week, int dayIndex, String lesson) {
        String lessonNumber = lesson.substring(lesson.indexOf("_Lesson") + 7);
        int vacancies = this.vacancies[week][dayIndex][Integer.parseInt(lessonNumber) - 1]; // Adjust lesson index here
        return Math.max(0, vacancies);
    }
}
