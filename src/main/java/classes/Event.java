package classes;

public class Event extends Task {
    public String toDate, fromDate;
    public Event(String desc, int ind, String fromDate, String toDate){
        super(desc,ind);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void printTask(){
        String taskString;
        if(isDone){
            taskString = "   [E][X] ";
        }
        else{
            taskString = "   [E][ ] ";
        }
        System.out.println(taskString + description + " (from: " + fromDate + " to: "+ toDate + ")");
    }

    @Override
    public void printTaskWithIndex(){
        String taskString;
        if(isDone){
            taskString = " " + index + ". [E][X] ";
        }
        else{
            taskString = " " + index + ". [E][ ] ";
        }
        System.out.println(taskString + description + " (from: " + fromDate + " to: "+ toDate + ")");
    }
}
