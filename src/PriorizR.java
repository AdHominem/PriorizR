import java.util.*;
import java.util.stream.Collectors;

enum Priority {
    LOW, NORMAL, HIGH
}

public class PriorizR {

    // Mocks persistence layer
    private static ArrayList<Issue> issues = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static List<Issue> getIssues() {
        return issues;
    }

    public static Task getTaskByID(final UUID taskID) throws TaskIDNotFoundException {
        return tasks.stream()
                .filter(task -> task.getId().compareTo(taskID) == 0)
                .findFirst()
                .orElseThrow(TaskIDNotFoundException::new);
    }

    public static List<Issue> suggestIssues() {
        List<Issue> possibleIssues = issues.stream()
                .filter(Issue::requirementFulfilled)
                .filter(issue -> !issue.isDone())
                .collect(Collectors.toList());
        List<Issue> highPriority = possibleIssues.stream()
                .filter(issue -> getTaskByID(issue.getTaskID()).getPriority() == Priority.HIGH)
                .collect(Collectors.toList());
        List<Issue> normalPriority = possibleIssues.stream()
                .filter(issue -> getTaskByID(issue.getTaskID()).getPriority() == Priority.NORMAL)
                .collect(Collectors.toList());

        return highPriority.isEmpty() ? normalPriority.isEmpty() ? possibleIssues : normalPriority : highPriority;
    }

    public static void loadTasksAndIssues(final ArrayList<Task> tasks) {
        PriorizR.tasks = tasks;
        PriorizR.issues = tasks.stream().flatMap(task -> task.getIssues().stream())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void loadIssue(final Issue issue) {
        PriorizR.issues.add(issue);
    }

    public static void loadTask(final Task task) {
        PriorizR.tasks.add(task);
    }

    public static void main(final String[] args) {
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

        issues.addAll(anonymity.getIssues());
        issues.addAll(workout.getIssues());
        issues.addAll(prepare.getIssues());

        tasks.add(anonymity);
        tasks.add(workout);
        tasks.add(prepare);

        suggestIssues().forEach(System.out::println);
    }
}
