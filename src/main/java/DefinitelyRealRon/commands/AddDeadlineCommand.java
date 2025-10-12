package commands;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import storage.Storage;

public class AddDeadlineCommand extends Command {
    private String description, deadline;

    public AddDeadlineCommand(String description, String deadline){
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Deadline t = new Deadline(description,tasks.size()+1,deadline);
        tasks.addTask(t);
        ui.showAddMessage(t.getTaskString(), tasks.size());
        saveToStorage(tasks, ui, storage);
    }
}
