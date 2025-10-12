package task;

public class TaskList extends java.util.ArrayList<Task> {
    public TaskList(){
        super();
    }
    public void addTask(Task t){
        this.add(t);
    }

    public void deleteTask(int index){
        this.remove(index);

        //update index of other tasks
        for(int i=index;i<this.size();i++){
            this.get(i).setIndex(index+1);
        }
    }
}