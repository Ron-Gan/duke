package classes;

public class Deadline extends Task {
    public String deadline;
    public Deadline(String desc, int ind, String deadline){
        super(desc,ind);
        this.deadline = deadline;
    }

    @Override
    public void printTask(){
        String taskString;
        if(isDone){
            taskString = "   [D][X] ";
        }
        else{
            taskString = "   [D][ ] ";
        }
        System.out.println(taskString + description + " (by: " + deadline + ")");
    }

    @Override
    public void printTaskWithIndex(){
        String taskString;
        if(isDone){
            taskString = " " + index + ". [D][X] ";
        }
        else{
            taskString = " " + index + ". [D][ ] ";
        }
        System.out.println(taskString + description + " (by: " + deadline + ")");
    }
}
