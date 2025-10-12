package commands;
import task.TaskList;
import ui.Ui;

import static common.Messages.ERROR_STORE_FAILED;

import java.io.IOException;

import storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public static void saveToStorage(TaskList tasks, Ui ui, Storage storage){
        try{
            storage.save(tasks);
        } catch(IOException e){
            ui.showErrorMessage(ERROR_STORE_FAILED);
        }
    };
}
