package storage;

import java.util.List;
import task.TaskList;
import task.Todo;
import task.Deadline;
import task.Event;
import task.Task;

public class DataDecoder {
    static Integer index = 1;
    public static TaskList decodeData(List<String> encodedData){
        TaskList tl = new TaskList();
        for(String encodedTask : encodedData){
            tl.add(decodeTaskFromString(encodedTask));
        }
        return tl;
    }
    private static Task decodeTaskFromString(String input){
        Task t = null;
        String[] parts = input.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        switch (taskType) {
            case "T":
                t = new Todo(description,index);
                t.setStatus(isDone);
                index+=1;
                return t;
            case "D":
                String deadline = parts[3];
                t = new Deadline(description,index,deadline);
                t.setStatus(isDone);
                index+=1;
                return t;
            case "E":
                String fromDate = parts[3];
                String toDate = parts[4];
                t = new Event(description,index,fromDate,toDate);
                t.setStatus(isDone);
                index+=1;
                return t;
            default:
                break;
        }
        return t;
    }
}
