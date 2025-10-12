package commands;
import task.Event;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class AddEventCommand extends Command {
    private String description, fromDate, toDate;

    public AddEventCommand(String description, String fromDate, String toDate){
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Event t = new Event(description,tasks.size()+1,fromDate,toDate);
        tasks.addTask(t);
        ui.showAddMessage(t.getTaskString(), tasks.size());
        saveToStorage(tasks, ui, storage);
    }
}
