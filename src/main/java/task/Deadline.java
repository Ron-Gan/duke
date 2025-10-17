package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadlineDisplayString;
    private LocalDateTime deadline;
    public Deadline(String desc, int ind, LocalDateTime deadline){
        super(desc,ind);
        this.deadline = deadline;
        this.deadlineDisplayString = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
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
        return(taskString + description + " (by: " + deadlineDisplayString + " hrs)");
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
        return(taskString + description + " (by: " + deadlineDisplayString + " hrs)");
    }

    public String getDeadlineString(){
        return deadlineDisplayString;
    }

    public LocalDateTime getDeadline(){
        return deadline;
    }
}
