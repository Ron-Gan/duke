package task;

import common.Priority;

/**
 * Represents a Task in a TaskList.
 */
public abstract class Task {

    public boolean isDone = false;
    public String description;
    public int index;
    public Priority priority;

    /**
     * @param description of the task.
     * @param index of task in a TaskList.
     */
    public Task(String description, int index) {
        this.description = description;
        this.index = index;
    }

    public void setStatus(boolean set) {
        isDone = set;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setIndex(int newIndex) {
        this.index = newIndex;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * @return formatted string of Task.
     */
    public abstract String getTaskString();

    /**
     * @return formatted string of Task with the index numbering.
     */
    public abstract String getTaskStringWithIndex();
}
