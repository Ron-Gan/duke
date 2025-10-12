package commands;

import static common.Messages.ERROR_ALREADY_UNMARKED;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class UnmarkCommand extends Command{
    Integer index;
    public UnmarkCommand(Integer index){
        this.index=index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
       if(tasks.get(index).isDone){
            tasks.get(index).setStatus(false);
            ui.showUnmarkMessage(tasks.get(index).getTaskString());
            saveToStorage(tasks, ui, storage);
        }
        else
            ui.showErrorMessage(String.format(ERROR_ALREADY_UNMARKED, tasks.get(index).description));
    }
}
