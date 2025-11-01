package commands;

import static common.Messages.ERROR_OUT_OF_BOUNDS;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Displays an error message.
 */
public class ErrorCommand extends Command {

    private String description;

    public ErrorCommand(String description) {
        this.description = description;
    }

    @Override
    /**
     * Displays an error message.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description == ERROR_OUT_OF_BOUNDS) {
            ui.showErrorMessage(String.format(description, tasks.size()));
        } else {
            ui.showErrorMessage(description);
        }
    }
}
