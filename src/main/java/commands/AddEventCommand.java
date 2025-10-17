package commands;
import task.Event;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

import storage.Storage;

/**
 * Adds an event task to the TaskList.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime fromDate, toDate;

    public AddEventCommand(String description, LocalDateTime fromDate, LocalDateTime toDate){
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    /**
     * Adds an event task into the TaskList.
     * Displays an AddMessage.
     * Saves new list to storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Event t = new Event(description,tasks.size()+1,fromDate,toDate);
        tasks.addTask(t);
        ui.showAddMessage(t.getTaskString(), tasks.size());
        saveToStorage(tasks, ui, storage);
    }
}
