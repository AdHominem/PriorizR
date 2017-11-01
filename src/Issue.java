import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

enum Priority {
    LOW, NORMAL, HIGH
}

public class Issue {
    private String name;
    private Date dateStart;
    private Date dateDone;
    private Priority priority = Priority.NORMAL;
    private List<Issue> subIssues = new ArrayList<>();
    private boolean done = false;
    private boolean active = false;
    private Optional<Issue> requiredIssue = Optional.empty();

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

    public Issue(final String name, final List<Issue> subIssues) {
        this.name = name;
        this.subIssues = subIssues;
    }

    public void requires(Issue issue) {
        requiredIssue = Optional.ofNullable(issue);
    }

    public void start() throws RequirementNotMetException {
        if (requiredIssue.isPresent()) {
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

    public void addSubIssue(Issue subIssue) {
        subIssues.add(subIssue);
    }

    public void removeSubIssue(Issue subIssue) {
        subIssues.remove(subIssue);
    }

    @Override
    public String toString() {
        return String.format("Issue: %s\nPriority: %s\nRequires: %s\nDate started: %s\nDate done: %s\nSub issues: \n%s",
                name,
                priority,
                requiredIssue.isPresent() ? requiredIssue.get().getName() : "None",
                active ? dateStart.toString() : "Not active yet",
                done ? dateDone.toString() : "Not done yet",
                subIssues.isEmpty() ? "None" : subIssues.stream()
                        .map(Issue::toString)
                        .collect(Collectors.joining("\n\n")));
    }
}
