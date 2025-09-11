package classes;

public class Todo extends Task {
    public Todo(String desc, int ind){
        super(desc,ind);
    }

    @Override
    public void printTask(){
        String taskString;
        if(isDone){
            taskString = " " + index + ". [T][X] ";
        }
        else{
            taskString = " " + index + ". [T][ ] ";
        }
        System.out.println(taskString + description);
    }
}
