package ui;

import static common.Messages.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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

    public void showToUser(String... message){
        out.println(LINE);
        for (String m : message) {
            out.println(m);
        }
        out.println(LINE);
    }

    public void showWelcomeMessage(){
        showToUser(MESSAGE_WELCOME);
    }

    public void showByeMessage(){
        showToUser(MESSAGE_BYE);
    }

    public String getInput(){
        String input = in.nextLine();
        return input;
    }

    public void showAddMessage(String taskString,Integer size){      
        showToUser(
            String.format(MESSAGE_ADDED, taskString, size)
        );
    }

    public void showTaskListMessage(String taskListString){      
        showToUser(
            MESSAGE_SHOW_TASKLIST,
            taskListString
        );
    }

    public void showMarkMessage(String taskString){      
        showToUser(
            MESSAGE_MARKED,
            taskString
        );
    }

    public void showUnmarkMessage(String taskString){      
        showToUser(
            MESSAGE_UNMARKED,
            taskString
        );
    }

    public void showErrorMessage(String errorMsg){      
        showToUser(
            MESSAGE_ERROR,
            errorMsg
        );
    }

    public void showDeleteMessage(String taskString, Integer size){      
        showToUser(
            String.format(MESSAGE_DELETED,taskString,size)
        );
    }
}
