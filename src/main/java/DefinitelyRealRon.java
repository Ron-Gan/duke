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
        int index = 1;

        while(!inputString.equals("bye")){ //does not end until user inputs "bye"
            String[] words = inputString.split(" ");
            String firstWord = words[0];
            int targetTaskForStatusChange = -1;

            switch (firstWord) {
                case "mark":
                    targetTaskForStatusChange = Integer.parseInt(words[1])-1;
                    taskList.get(targetTaskForStatusChange).setStatus(true);
                    System.out.println(LINE);
                    System.out.println(" You're so productive! I've marked this task as done:");
                    taskList.get(targetTaskForStatusChange).printTask();
                    System.out.println(LINE);
                    break;
                    
                case "unmark":
                    targetTaskForStatusChange = Integer.parseInt(words[1])-1;
                    taskList.get(targetTaskForStatusChange).setStatus(false);
                    System.out.println(LINE);
                    System.out.println(" L! I've marked this task as not done yet:");
                    taskList.get(targetTaskForStatusChange).printTask();
                    System.out.println(LINE);
                    break;

                case "list":
                    if(taskList.size()==0){
                        printWithLine(" Your list is empty!");
                        inputString = in.nextLine();
                        continue;
                    }
                    System.out.println(LINE);
                    System.out.println(" Here are the tasks in your list:");
                    for(int i=0; i<taskList.size(); i++){
                        taskList.get(i).printTask();
                    }
                    System.out.println(LINE);
                    break;

                default:
                    taskList.add(new Task(inputString,index));
                    index += 1;
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
