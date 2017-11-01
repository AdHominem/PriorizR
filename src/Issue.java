import java.util.Date;
import java.util.List;

enum Priority {
    LOW, NORMAL, HIGH
}

public class Issue {
    private String name;
    private Date dateStart;
    private Date dateDone;
    private Priority priority;
    private List<Issue> subIssues;
    private boolean done;

    public Issue(final String name, final Priority priority, final List<Issue> subIssues) {
        this.name = name;
        this.dateStart = new Date();
        this.priority = priority;
        this.subIssues = subIssues;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateDone() {
        return dateDone;
    }

    public Priority getPriority() {
        return priority;
    }

    public List<Issue> getSubIssues() {
        return subIssues;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
        this.dateDone = new Date();
    }
}
