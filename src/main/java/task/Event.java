package task;

public class Event extends Task {
    public String toDate, fromDate;
    public Event(String desc, int ind, String fromDate, String toDate){
        super(desc,ind);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String getTaskString(){
        String taskString;
        if(isDone){
            taskString = "   [E][X] ";
        }
        else{
            taskString = "   [E][ ] ";
        }
        return(taskString + description + " (from: " + fromDate + " to: "+ toDate + ")");
    }

    @Override
    public String getTaskStringWithIndex(){
        String taskString;
        if(isDone){
            taskString = " " + index + ". [E][X] ";
        }
        else{
            taskString = " " + index + ". [E][ ] ";
        }
        return(taskString + description + " (from: " + fromDate + " to: "+ toDate + ")");
    }
}
