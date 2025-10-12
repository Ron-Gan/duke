package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command{
    Integer index;
    public DeleteCommand(Integer index){
        this.index=index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showDeleteMessage(tasks.get(index).getTaskString(),tasks.size()-1);
        tasks.deleteTask(index);
        saveToStorage(tasks, ui, storage);
    }
}
