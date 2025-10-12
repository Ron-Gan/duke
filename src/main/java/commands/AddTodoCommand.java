package commands;
import task.TaskList;
import task.Todo;
import ui.Ui;
import storage.Storage;

public class AddTodoCommand extends Command {
    private String description;
    public AddTodoCommand(String description){
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Todo t = new Todo(description,tasks.size()+1);
        tasks.addTask(t);
        ui.showAddMessage(t.getTaskString(), tasks.size());
        saveToStorage(tasks, ui, storage);
    }
}
