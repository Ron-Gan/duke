package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String toDateRaw, fromDateRaw,toDateString,fromDateString;
    private LocalDate toDate,fromDate;

    public Event(String desc, int ind, String fromDate, String toDate){
        super(desc,ind);
        this.fromDateRaw = fromDate;
        this.toDateRaw = toDate;
        this.fromDate = LocalDate.parse(fromDate);
        this.fromDateString = this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.toDate = LocalDate.parse(toDate);
        this.toDateString = this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
        return(taskString + description + " (from: " + fromDateString + " to: "+ toDateString + ")");
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
        return(taskString + description + " (from: " + fromDateString + " to: "+ toDateString + ")");
    }

    public String getToDateString(){
        return toDateString;
    }

    public String getFromDateString(){
        return fromDateString;
    }

    public String getToDateRaw(){
        return toDateRaw;
    }

    public String getFromDateRaw(){
        return fromDateRaw;
    }
}
