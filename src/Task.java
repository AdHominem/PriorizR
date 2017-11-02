import java.util.*;
import java.util.stream.Collectors;

public class Task {
    private String name;
    private Date dateStart;
    private Date dateDone;
    private Priority priority = Priority.NORMAL;
    private List<Issue> issues = new ArrayList<>();
    private boolean done;
    private boolean active;
    private final UUID id = UUID.randomUUID();

    public Task(final String name, final Priority priority, final List<Issue> issues) {
        this.name = name;
        this.priority = priority;
        this.issues = issues;
        this.issues.forEach(issue -> issue.setTaskID(id));
    }

    public Task(final String name, final Priority priority) {
        this.name = name;
        this.priority = priority;
    }

    public Task(final String name) {
        this.name = name;
    }

    public Task(final String name, final List<Issue> issues) {
        this.name = name;
        this.issues = issues;
        this.issues.forEach(issue -> issue.setTaskID(id));
    }

    public Task(final String name, final Issue issue) {
        this.name = name;
        this.issues.add(issue);
    }

    public void start() {
        active = true;
        dateStart = new Date();
    }

    public Date getDateStart() {
        return dateStart;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public boolean isDone() {
        return done;
    }

    public void done() {
        this.done = true;
        this.dateDone = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(final Priority priority) {
        this.priority = priority;
    }

    public void addIssue(final Issue issue) {
        issues.add(issue);
    }

    public void removeSubIssue(final Issue subIssue) {
        issues.remove(subIssue);
    }

    @Override
    public String toString() {
        return String.format("Task: %s%nPriority: %s%nDate started: %s%nDate done: %s%nIssues: %n%s",
                name,
                priority,
                active ? dateStart.toString() : "Not active yet",
                done ? dateDone.toString() : "Not done yet",
                issues.isEmpty() ? "None" : issues.stream()
                        .map(Issue::toString)
                        .collect(Collectors.joining("%n%n")));
    }

    public UUID getId() {
        return id;
    }
}
