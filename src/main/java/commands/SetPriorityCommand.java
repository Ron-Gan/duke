package commands;

import common.Priority;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Sets the priority of a task.
 */
public class SetPriorityCommand extends Command {

    Integer index;
    Priority priority;

    public SetPriorityCommand(Integer index, Priority priority) {
        this.index = index;
        this.priority = priority;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(index).setPriority(priority);
        ui.showPriorityMessage(tasks.get(index).getTaskString());
        saveToStorage(tasks, ui, storage);
    }
}
