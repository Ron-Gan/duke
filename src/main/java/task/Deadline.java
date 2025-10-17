package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadlineString;
    private LocalDate deadlineDate;
    public Deadline(String desc, int ind, String deadline){
        super(desc,ind);
        this.deadlineDate = LocalDate.parse(deadline);
        this.deadlineString = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
        return(taskString + description + " (by: " + deadlineString + ")");
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
        return(taskString + description + " (by: " + deadlineString + ")");
    }

    public String getDeadlineString(){
        return deadlineString;
    }
}
