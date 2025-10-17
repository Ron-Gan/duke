package task;

/**
 * Represents a Task in a TaskList.
 */
public abstract class Task {
    public boolean isDone = false;
    public String description;
    public int index;
    static int count = 0;
    /**
     * @param description of the task.
     * @param index of task in a TaskList.
     */
    public Task(String description, int index){
        this.description = description;
        this.index = index;
    }

    public void setStatus(boolean set){
        isDone = set;
    }

    public boolean getStatus(){
        return isDone;
    }

    public void setIndex(int newIndex){
        this.index = newIndex;
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
