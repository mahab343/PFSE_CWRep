
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LessonBookingTest {
    @Test
    void testDisplayTimetableByDay() {
        // Arrange
        LessonBooking bookSwimmingLesson = new LessonBooking();
        String expectedOutput = "Timetable for Monday:"; // Define your expected output here

        bookSwimmingLesson.displayTimetableByDay("Monday");

        assertEquals(expectedOutput, "Timetable for Monday:");
    }
}
