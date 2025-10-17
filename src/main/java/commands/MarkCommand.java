package commands;

import static common.Messages.ERROR_ALREADY_MARKED;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Changes the status of a task to completed.
 */
public class MarkCommand extends Command{
    Integer index;
    public MarkCommand(Integer index){
        this.index=index;
    }

    @Override
    /**
     * Changes the status of a task to completed.
     * Displays a message when successfully marked.
     * Displays an error if task was already completed.
     */
    public void execute(TaskList tasks, Ui ui,Storage storage){
        if(!tasks.get(index).isDone){
            tasks.get(index).setStatus(true);
            ui.showMarkMessage(tasks.get(index).getTaskString());
            saveToStorage(tasks, ui, storage);
        }
        else
            ui.showErrorMessage(String.format(ERROR_ALREADY_MARKED, tasks.get(index).description));
    }
}
