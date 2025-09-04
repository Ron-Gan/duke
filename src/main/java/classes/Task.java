package classes;

public class Task {
    public boolean isDone = false;
    public String description;
    public Task(String desc){
        description = desc;
    }
    public void setStatus(boolean set){
        isDone = set;
    }
    public boolean getStatus(){
        return isDone;
    }
    public String printTask(){
        String taskString;
        if(isDone){
            taskString = "[X] ";
        }
        else{
            taskString = "[ ] ";
        }
        return (taskString + description);
    }
}
