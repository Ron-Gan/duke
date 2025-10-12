package task;

public abstract class Task {
    public boolean isDone = false;
    public String description;
    public int index;
    static int count = 0;
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
    public abstract String getTaskString();
    public abstract String getTaskStringWithIndex();
}
