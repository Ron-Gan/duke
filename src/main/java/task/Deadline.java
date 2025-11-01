package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with an end date.
 */
public class Deadline extends Task {

    private String deadlineDisplayString;
    private LocalDateTime deadline;

    /**
     * @param description of the task.
     * @param index of task in a TaskList.
     * @param deadline of the task.
     */
    public Deadline(String desc, int ind, LocalDateTime deadline) {
        super(desc, ind);
        this.deadline = deadline;
        this.deadlineDisplayString = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
    }

    @Override
    public String getTaskString() {
        String taskString;
        if (priority != null) {
            if (isDone) {
                taskString = String.format("   [D][X][%s] ", priority.toString());
            } else {
                taskString = String.format("   [D][ ][%s] ", priority.toString());
            }
        } else {
            if (isDone) {
                taskString = "   [D][X][ ] ";
            } else {
                taskString = "   [D][ ][ ] ";
            }
        }

        return (taskString + description + "\n   (by: " + deadlineDisplayString + " hrs)");
    }

    @Override
    public String getTaskStringWithIndex() {
        String taskString;
        if (priority != null) {
            if (isDone) {
                taskString = " " + index + String.format(". [D][X][%s] ", priority.toString());
            } else {
                taskString = " " + index + String.format(". [D][ ][%s] ", priority.toString());
            }
        } else {
            if (isDone) {
                taskString = " " + index + ". [D][X][ ] ";
            } else {
                taskString = " " + index + ". [D][ ][ ] ";
            }
        }
        return (taskString + description + "\n    (by: " + deadlineDisplayString + " hrs)");
    }

    public String getDeadlineString() {
        return deadlineDisplayString;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}
