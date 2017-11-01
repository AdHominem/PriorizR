import java.util.List;

public class DailyTask extends Task {

    public DailyTask(final String name, final Priority priority, final List<Issue> issues) {
        super(name, priority, issues);
    }

    public DailyTask(final String name) {
        super(name);
    }

    public DailyTask(final String name, final List<Issue> issues) {
        super(name, issues);
    }
}
