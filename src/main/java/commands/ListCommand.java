package commands;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        String taskListString ="";
        
        for(int i=0; i<tasks.size();i+=1){
            taskListString += tasks.get(i).getTaskStringWithIndex()+"\n";
        }
        taskListString = taskListString.substring(0,taskListString.length()-1);
        ui.showTaskListMessage(taskListString);
    }
}
