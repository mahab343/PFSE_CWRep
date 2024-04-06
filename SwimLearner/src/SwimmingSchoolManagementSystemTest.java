import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SwimmingSchoolManagementSystemTest {

    private SwimmingSchoolManagementSystem system;
    private Learner learner;
    private Lesson lesson;

    @Before
    public void setUp() {
        system = new SwimmingSchoolManagementSystem();
        learner = new Learner("John Doe", "Male", 10, "1234567890", 2);
        lesson = new Lesson("Monday", "4-5pm", 2, new Coach("Coach A"), 4);
    }

    @Test
    public void testBookLesson() {
        boolean result = system.bookLesson(learner, lesson);
        assertTrue(result);
        assertEquals(1, lesson.getLearners().size());
    }

    @Test
    public void testCancelLesson() {
        system.bookLesson(learner, lesson);
        boolean result = system.cancelLesson(learner, lesson);
        assertTrue(result);
        assertEquals(0, lesson.getLearners().size());
    }

    @Test
    public void testProvideRatingAndReview() {
        system.bookLesson(learner, lesson);
        learner.addReview(lesson, 5);
        assertEquals(5, learner.getAverageRating(), 0.01);
    }

    @Test
    public void testPrintLearnerInfo() {
        system.bookLesson(learner, lesson);
        assertEquals("John Doe (Grade 2)", learner.toString());
    }

    @Test
    public void testPrintCoachInfo() {
        Coach coach = new Coach("Coach A");
        Lesson lesson2 = new Lesson("Monday", "4-5pm", 2, coach, 4);
        system.bookLesson(learner, lesson);
        system.bookLesson(learner, lesson2);
        assertEquals("Coach A", coach.getName());
    }
}
