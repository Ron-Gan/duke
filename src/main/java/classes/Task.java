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
    public void printTask(int index){
        String taskString;
        index +=1;
        if(isDone){
            taskString = " " + index + ". [X] ";
        }
        else{
            taskString = " " + index + ". [ ] ";
        }
        System.out.println(taskString + description);
    }
}
