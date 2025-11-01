package storage;

import java.time.LocalDateTime;
import java.util.List;

import common.Priority;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

public class DataDecoder {

    static Integer index = 1;

    public static TaskList decodeData(List<String> encodedData) {
        TaskList tl = new TaskList();
        for (String encodedTask : encodedData) {
            tl.add(decodeTaskFromString(encodedTask));
        }
        return tl;
    }

    private static Task decodeTaskFromString(String input) {
        Task t = null;
        String[] parts = input.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        Priority priority = null;
        if (!parts[2].equals("NIL")) {
            priority = Priority.valueOf(parts[2]);
        }
        String description = parts[3];
        switch (taskType) {
            case "T":
                t = new Todo(description, index);
                break;
            case "D":
                String deadlineISO = parts[4];
                t = new Deadline(description, index, LocalDateTime.parse(deadlineISO));
                break;
            case "E":
                String fromDateISO = parts[4];
                String toDateISO = parts[5];
                t = new Event(description, index, LocalDateTime.parse(fromDateISO), LocalDateTime.parse(toDateISO));
                break;
            default:
                break;
        }
        if (priority != null) {
            t.setPriority(priority);
        }
        t.setStatus(isDone);
        index += 1;
        return t;
    }
}
