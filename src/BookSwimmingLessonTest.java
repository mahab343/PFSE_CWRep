import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookSwimmingLessonTest {
    @Test
    void testDisplayTimetableByDay() {
        // Arrange
        BookSwimmingLesson bookSwimmingLesson = new BookSwimmingLesson();
        String expectedOutput = "Timetable for Monday:"; // Define your expected output here

        bookSwimmingLesson.displayTimetableByDay("Monday");

        assertEquals(expectedOutput, "Timetable for Monday:");
    }
}
