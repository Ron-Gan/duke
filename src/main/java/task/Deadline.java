package task;

public class Deadline extends Task {
    public String deadline;
    public Deadline(String desc, int ind, String deadline){
        super(desc,ind);
        this.deadline = deadline;
    }

    @Override
    public String getTaskString(){
        String taskString;
        if(isDone){
            taskString = "   [D][X] ";
        }
        else{
            taskString = "   [D][ ] ";
        }
        return(taskString + description + " (by: " + deadline + ")");
    }

    @Override
    public String getTaskStringWithIndex(){
        String taskString;
        if(isDone){
            taskString = " " + index + ". [D][X] ";
        }
        else{
            taskString = " " + index + ". [D][ ] ";
        }
        return(taskString + description + " (by: " + deadline + ")");
    }
}
