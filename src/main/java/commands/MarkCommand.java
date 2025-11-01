package commands;

import static common.Messages.ERROR_ALREADY_MARKED;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Changes the status of a task to completed.
 */
public class MarkCommand extends Command {
    private Integer index;
    public MarkCommand(Integer index) {
        this.index = index;
    }

    /**
     * Changes the status of a task to incomplete.
     * Displays a message when successfully marked.
     * Displays an error if task was already completed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.get(index).getStatus()) {
            tasks.get(index).setStatus(true);
            ui.showMarkMessage(tasks.get(index).getTaskString());
            saveToStorage(tasks, ui, storage);
        } else {
            ui.showErrorMessage(String.format(ERROR_ALREADY_MARKED, tasks.get(index).getDescription()));
        }
    }
}
