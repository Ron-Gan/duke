package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadlineRaw, deadlineDisplayString;
    private LocalDate deadlineDate;
    public Deadline(String desc, int ind, String deadline){
        super(desc,ind);
        this.deadlineRaw = deadline;
        this.deadlineDate = LocalDate.parse(deadline);
        this.deadlineDisplayString = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
        return(taskString + description + " (by: " + deadlineDisplayString + ")");
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
        return(taskString + description + " (by: " + deadlineDisplayString + ")");
    }

    public String getDeadlineString(){
        return deadlineDisplayString;
    }

    public String getDeadlineRaw(){
        return deadlineRaw;
    }
}
