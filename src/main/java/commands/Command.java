package commands;
import task.TaskList;
import ui.Ui;

import static common.Messages.ERROR_STORE_FAILED;

import java.io.IOException;

import storage.Storage;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Runs the logic of the execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Sends the taskList to be saved in the storage.
     */
    public static void saveToStorage(TaskList tasks, Ui ui, Storage storage){
        try{
            storage.save(tasks);
        } catch(IOException e){
            ui.showErrorMessage(ERROR_STORE_FAILED);
        }
    };
}
