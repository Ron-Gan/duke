package ui;

import static common.Messages.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * UI of the application.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";    
    private final Scanner in;
    private final PrintStream out;
    
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public Ui() {
        this(System.in, System.out);
    }
    
    /**
     * Show message(s) to user.
     * Encapsulated with LINEs.
     */
    public void showToUser(String... message){
        out.println(LINE);
        for (String m : message) {
            out.println(m);
        }
        out.println(LINE);
    }

    /** Prints welcome message on the start of the application. */
    public void showWelcomeMessage(){
        showToUser(MESSAGE_WELCOME);
    }

    /** Prints bye message before exit of the application. */
    public void showByeMessage(){
        showToUser(MESSAGE_BYE);
    }

    /** Reads input from user. */
    public String getInput(){
        String input = in.nextLine();
        return input;
    }

    /**
     * Displays messages whenever task has been added.
     * @param taskString details of task; formatted in task objects.
     * @param size size of tasklist.
     */
    public void showAddMessage(String taskString,Integer size){      
        showToUser(
            String.format(MESSAGE_ADDED, taskString, size)
        );
    }

    /**
     * Displays list of tasks.
     * @param taskListString details of tasks; formatted in taskList object.
     */
    public void showTaskListMessage(String taskListString){      
        showToUser(
            MESSAGE_SHOW_TASKLIST,
            taskListString
        );
    }

    /**
     * Displays message to show that task is marked,
     * after mark command has been executed.
     * @param taskString details of task; formatted in task objects.
     */
    public void showMarkMessage(String taskString){      
        showToUser(
            MESSAGE_MARKED,
            taskString
        );
    }

    /**
     * Displays message to show that task is unmarked, 
     * after unmark command has been executed.
     * @param taskString details of task; formatted in task objects.
     */
    public void showUnmarkMessage(String taskString){      
        showToUser(
            MESSAGE_UNMARKED,
            taskString
        );
    }

    /**
     * Displays error message according to which exception has been thrown
     * @param errorMsg describes the error faced.
     */
    public void showErrorMessage(String errorMsg){      
        showToUser(
            MESSAGE_ERROR,
            errorMsg
        );
    }

    /**
     * Displays message to show that task has been deleted, 
     * after delete command has been executed.
     * @param taskString details of task; formatted in task objects.
     * @param size size of tasklist.
     */
    public void showDeleteMessage(String taskString, Integer size){      
        showToUser(
            String.format(MESSAGE_DELETED,taskString,size)
        );
    }
}
