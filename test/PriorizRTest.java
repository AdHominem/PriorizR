import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PriorizRTest {

    @BeforeAll
    static void init() {
        Issue SB1Read = new Issue("Read SB1");
        Issue SB1Exercises = new Issue("Do SB1 exercises", SB1Read);
        Issue SB2Read = new Issue("Read SB2", SB1Read);
        Issue SB2Exercises = new Issue("Do SB2 exercises", SB2Read);
        Issue SB3Read = new Issue("Read SB3", SB2Read);
        Issue SB3Exercises = new Issue("Do SB3 exercises", SB3Read);
        Issue prepareExam = new Issue("Prepare for exam", SB3Exercises);
        Issue writeExam = new Issue("Write Exam", prepareExam);

        Task anonymity = new Task("Anonymity in the net", Arrays.asList(SB1Read, SB1Exercises, SB2Read, SB2Exercises,
                SB3Read, SB3Exercises, prepareExam, writeExam));

        Issue workoutIssue = new Issue("Workout");
        Task workout = new Task("Workout", workoutIssue);

        Issue scrum = new Issue("Scrum Course Pluralsight");
        Issue cissp = new Issue("CISSP Course Pluralsight");
        Issue csslp = new Issue("CSSLP Course Pluralsight");
        Task prepare = new Task("Prepare", Priority.HIGH, Arrays.asList(scrum, cissp, csslp));

        PriorizR.loadTasksAndIssues(new ArrayList<>(Arrays.asList(anonymity, workout, prepare)));
    }

    @Test
    void getTaskByIDTestNotExist() {
        assertThrows(TaskIDNotFoundException.class, () -> PriorizR.getTaskByID(UUID.randomUUID()));
    }

    @Test
    void getTaskByIDTest() {
        Task task = new Task("Test task");
        PriorizR.loadTask(task);
        assertEquals(task, PriorizR.getTaskByID(task.getId()));
    }

    @Test
    void createScheduledIssueTest() {
        ScheduledIssue scheduledIssue = new ScheduledIssue("Nikolaus", LocalDateTime.of(2017, 12,
                6, 7, 0));
        System.out.println(scheduledIssue.toString());
    }

}