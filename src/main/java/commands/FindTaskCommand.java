package commands;

import static common.Messages.ERROR_NO_MATCHING_TASKS;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindTaskCommand extends Command {

    String targetString;
    TaskList foundTasks = new TaskList();

    public FindTaskCommand(String desc) {
        targetString = desc;
    }

    /**
     * Searches for tasks that contain the given keyword in their description
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(targetString)) {
                foundTasks.add(tasks.get(i));
            }
        }
        if (foundTasks.isEmpty()) {
            ui.showErrorMessage(ERROR_NO_MATCHING_TASKS);
        } else {
            String foundTasksString = "";

            for (int i = 0; i < foundTasks.size(); i += 1) {
                foundTasksString += foundTasks.get(i).getTaskStringWithIndex() + "\n";
            }
            foundTasksString = foundTasksString.substring(0, foundTasksString.length() - 1);
            ui.showFoundTasksMessage(foundTasksString);
        }
    }
}
