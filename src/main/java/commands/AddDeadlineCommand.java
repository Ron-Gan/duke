package commands;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

import storage.Storage;

/**
 * Adds a deadline task into the TaskList.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;

    public AddDeadlineCommand(String description, LocalDateTime deadline){
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    /**
     * Adds a deadline task into the TaskList.
     * Displays an AddMessage.
     * Saves new list to storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Deadline t = new Deadline(description,tasks.size()+1,deadline);
        tasks.addTask(t);
        ui.showAddMessage(t.getTaskString(), tasks.size());
        saveToStorage(tasks, ui, storage);
    }
}
