package task;

public class Todo extends Task {
    public Todo(String desc, int ind){
        super(desc,ind);
    }

    @Override
    public String getTaskString(){
        String taskString;
        if(isDone){
            taskString = "   [T][X] ";
        }
        else{
            taskString = "   [T][ ] ";
        }
        return(taskString + description);
    }

    @Override
    public String getTaskStringWithIndex(){
        String taskString;
        if(isDone){
            taskString = " " + index + ". [T][X] ";
        }
        else{
            taskString = " " + index + ". [T][ ] ";
        }
       return(taskString + description);
    }
}
