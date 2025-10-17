package storage;

import java.util.List;
import task.TaskList;
import task.Todo;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDateTime;

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
                String deadlineISO = parts[3];
                t = new Deadline(description,index,LocalDateTime.parse(deadlineISO));
                t.setStatus(isDone);
                index+=1;
                return t;
            case "E":
                String fromDateISO = parts[3];
                String toDateISO = parts[4];
                t = new Event(description,index,LocalDateTime.parse(fromDateISO),LocalDateTime.parse(toDateISO));
                t.setStatus(isDone);
                index+=1;
                return t;
            default:
                break;
        }
        return t;
    }
}
