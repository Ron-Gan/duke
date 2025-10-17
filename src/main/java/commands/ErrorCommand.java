package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Displays an error message.
 */
public class ErrorCommand extends Command{
    private String description;
    public ErrorCommand(String description){
        this.description=description;
    }
    @Override
    /** Displays an error message. */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showErrorMessage(description);
    }
}
