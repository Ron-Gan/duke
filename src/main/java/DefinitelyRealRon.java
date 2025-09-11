import java.util.Scanner;
import classes.Task;
import java.util.ArrayList;
import java.util.List;

public class DefinitelyRealRon {
    
    private static final String LINE = "____________________________________________________________";
    public static List<Task> taskList = new ArrayList<Task>();

    public static void printWithLine(String inputString){
            System.out.println(LINE);
            System.out.println(inputString);
            System.out.println(LINE);
    }
     
    public static void echo(String inputString){
        printWithLine(" added: "+inputString);
    }

    public static void readInput(){
        Scanner in = new Scanner(System.in);
        String inputString;
        inputString = in.nextLine();
        String printListString = "";

        while(!inputString.equals("bye")){ //does not end until user inputs "bye"
            String[] words = inputString.split(" ", 2);
            String firstWord = words[0];
            int targetTaskForStatusChange = -1;

            switch (firstWord) {
                case "mark":
                    targetTaskForStatusChange = Integer.parseInt(words[1])-1;
                    taskList.get(targetTaskForStatusChange).setStatus(true);
                    printWithLine(" You're so productive! I've marked this task as done:\n " 
                                + taskList.get(targetTaskForStatusChange).printTask());
                    break;
                    
                case "unmark":
                    targetTaskForStatusChange = Integer.parseInt(words[1])-1;
                    taskList.get(targetTaskForStatusChange).setStatus(false);
                    printWithLine(" L! I've marked this task as not done yet:\n " 
                                + taskList.get(targetTaskForStatusChange).printTask());
                    break;

                case "list":
                    if(taskList.size()==0){
                        printWithLine(" Your list is empty!");
                        inputString = in.nextLine();
                        continue;
                    }
                    System.out.println(LINE);
                    System.out.print(" Here are the tasks in your list:\n ");
                    for(int i=0; i<taskList.size()-1; i++){
                        printListString = printListString + (i+1) + ". " + taskList.get(i).printTask() + "\n ";
                    }
                    printListString += (taskList.size()) + ". " + taskList.get(taskList.size()-1).printTask() + "\n";
                    System.out.print(printListString);
                    System.out.println(LINE);
                    printListString="";
                    break;

                default:
                    taskList.add(new Task(inputString));
                    echo(inputString);
                    break;
            }

            inputString = in.nextLine();
        }
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        String welcomeString = " Yo! I'm DefinitelyRealRon. Definitely Real. Definitely Ron.\n How may I help you today?";
        String byeString = " Seeya! Hope I was helpful. I'm DefinitelyRealRon!";
        printWithLine(welcomeString);
        readInput();
        System.out.println(byeString);
        System.out.println(LINE);
    }
}
