package classes;

public class Task {
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
    public void printTask(){
        String taskString;
        if(isDone){
            taskString = "   [X] ";
        }
        else{
            taskString = "   [ ] ";
        }
        System.out.println(taskString + description);
    }
    public void printTaskWithIndex(){
        String taskString;
        if(isDone){
            taskString = " " + index + ". [X] ";
        }
        else{
            taskString = " " + index + ". [ ] ";
        }
        System.out.println(taskString + description);
    }
    public void setIndex(int newIndex){
        this.index = newIndex;
    }
}
