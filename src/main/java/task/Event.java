package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String toDateString,fromDateString;
    private LocalDateTime toDate,fromDate;

    public Event(String desc, int ind, LocalDateTime fromDate, LocalDateTime toDate){
        super(desc,ind);
        this.fromDate = fromDate;
        this.fromDateString = this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
        this.toDate = toDate;
        this.toDateString = this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
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
        return(taskString + description + " (from: " + fromDateString + " hrs | to: "+ toDateString + " hrs)");
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
        return(taskString + description + " (from: " + fromDateString + " hrs | to: "+ toDateString + "hrs)");
    }

    public String getToDateString(){
        return toDateString;
    }

    public String getFromDateString(){
        return fromDateString;
    }

    public LocalDateTime getFromDate(){
        return fromDate;
    }

    public LocalDateTime getToDate(){
        return toDate;
    }
}
