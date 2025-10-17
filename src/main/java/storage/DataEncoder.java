package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

public class DataEncoder {
    static Integer index = 1;
    public static String encodeData(TaskList tasks){
        String encodedData = "";
        for(Task task : tasks){
            encodedData+=encodeTask(task);
        }
        return(encodedData);
    }
    private static String encodeTask(Task task){
        String taskString = "";
        if(task instanceof Todo)
            taskString = "T | ";
        else if(task instanceof Deadline)
            taskString = "D | ";
        else if(task instanceof Event)
            taskString = "E | ";

        if(task.isDone)
            taskString += "1 | ";
        else
            taskString += "0 | ";
        

        taskString += task.description;

        if(task instanceof Deadline)
            taskString += " | " + ((Deadline) task).getDeadline();
        else if(task instanceof Event)
            taskString += " | " + ((Event) task).getFromDateRaw() + " | " + ((Event) task).getToDateRaw();

        taskString += "\n";
        return taskString;
    }
}
