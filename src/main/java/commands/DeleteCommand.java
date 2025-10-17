package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Deletes a task from the TaskList.
 */
public class DeleteCommand extends Command{
    Integer index;
    public DeleteCommand(Integer index){
        this.index=index;
    }

    @Override
    /**
     * Shows the deletion message.
     * Deletes the targetted index task.
     * Saves new list to storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showDeleteMessage(tasks.get(index).getTaskString(),tasks.size()-1);
        tasks.deleteTask(index);
        saveToStorage(tasks, ui, storage);
    }
}
