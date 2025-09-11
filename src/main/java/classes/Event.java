package classes;

public class Event extends Task {
    public String toDate, fromDate;
    public Event(String desc, int ind, String toDate, String fromDate){
        super(desc,ind);
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    @Override
    public void printTask(){
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
