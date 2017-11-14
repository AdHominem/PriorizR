import java.time.LocalDateTime;
import java.util.List;

public class ScheduledIssue extends Issue {
    private LocalDateTime scheduledDate;

    public ScheduledIssue(final String name, final Priority priority, final List<Issue> subIssues,
                          final LocalDateTime scheduledDate) {
        super(name, priority, subIssues);
        this.scheduledDate = scheduledDate;
    }

    public ScheduledIssue(final String name, final Priority priority, final LocalDateTime scheduledDate) {
        super(name, priority);
        this.scheduledDate = scheduledDate;
    }

    public ScheduledIssue(final String name, final LocalDateTime scheduledDate) {
        super(name);
        this.scheduledDate = scheduledDate;
    }

    public ScheduledIssue(final String name, final Issue requiredIssue, final LocalDateTime scheduledDate) {
        super(name, requiredIssue);
        this.scheduledDate = scheduledDate;
    }

    public ScheduledIssue(final String name, final List<Issue> subIssues, final LocalDateTime scheduledDate) {
        super(name, subIssues);
        this.scheduledDate = scheduledDate;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(final LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @Override
    public String toString() {
        return String.format("Issue: %s%nID: %s%nTask: %s%nScheduled Date: %s%nPriority: %s%nRequires: %s%nDate started: %s%nDate done: %s",
                name,
                id.toString(),
                taskID,
                scheduledDate.toString(),
                priority,
                requiredIssue.isPresent() ? requiredIssue.get().getName() : "None",
                active ? dateStart.toString() : "Not active yet",
                done ? dateDone.toString() : "Not done yet");
    }
}
