import java.util.*;

public class Issue {
    private String name;
    private Date dateStart;
    private Date dateDone;
    private Priority priority = Priority.NORMAL;
    private List<Issue> subIssues = new ArrayList<>();
    private boolean done;
    private boolean active;
    private Optional<Issue> requiredIssue = Optional.empty();
    private final UUID id = UUID.randomUUID();
    private UUID taskID;

    public Issue(final String name, final Priority priority, final List<Issue> subIssues) {
        this.name = name;
        this.priority = priority;
        this.subIssues = subIssues;
    }

    public Issue(final String name, final Priority priority) {
        this.name = name;
        this.priority = priority;
    }

    public Issue(final String name) {
        this.name = name;
    }

    public Issue(final String name, final Issue requiredIssue) {
        this.name = name;
        this.requiredIssue = Optional.ofNullable(requiredIssue);
    }

    public Issue(final String name, final List<Issue> subIssues) {
        this.name = name;
        this.subIssues = subIssues;
    }

    public boolean requirementFulfilled() {
        return !requiredIssue.isPresent() || requiredIssue.get().isDone();
    }

    public void requires(final Issue issue) {
        requiredIssue = Optional.ofNullable(issue);
    }

    public void start() throws RequirementNotMetException {
        if (requirementFulfilled()) {
            active = true;
            dateStart = new Date();
        } else {
            throw new RequirementNotMetException();
        }
    }

    public Date getDateStart() {
        return dateStart;
    }

    public List<Issue> getSubIssues() {
        return subIssues;
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

    public void addSubIssue(final Issue subIssue) {
        subIssues.add(subIssue);
    }

    public void removeSubIssue(final Issue subIssue) {
        subIssues.remove(subIssue);
    }

    @Override
    public String toString() {
        return String.format("Issue: %s%nID: %s%nTask: %s%nPriority: %s%nRequires: %s%nDate started: %s%nDate done: %s",
                name,
                id.toString(),
                taskID,
                priority,
                requiredIssue.isPresent() ? requiredIssue.get().getName() : "None",
                active ? dateStart.toString() : "Not active yet",
                done ? dateDone.toString() : "Not done yet");
    }

    public UUID getTaskID() {
        return taskID;
    }

    public void setTaskID(final UUID taskID) {
        this.taskID = taskID;
    }
}
