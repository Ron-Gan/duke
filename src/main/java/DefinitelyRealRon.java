import java.util.Scanner;
import classes.*;
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
            String[] dates = inputString.split("/");
            String[] words = dates[0].split(" ");
            String firstWord = words[0];
            int targetTaskForStatusChange = -1;
            String taskName = "";
            for(int i=1; i<words.length-1;i+=1)
                taskName+= words[i] + " ";
            taskName += words[words.length-1]; 
            
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

                case "todo":
                    taskList.add(new Todo(taskName,index));
                    System.out.println(LINE);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  [T][ ] "+ taskName);
                    System.out.println(" Now you have " + taskList.size() + " tasks in your list.");
                    System.out.println(LINE);
                    index+=1;
                    break;

                case "deadline":
                    String deadline = inputString.split("/by ")[1];
                    taskList.add(new Deadline(taskName,index,deadline));
                    System.out.println(LINE);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  [D][ ] "+ taskName  + " (by: " + deadline + ")");
                    System.out.println(" Now you have " + taskList.size() + " tasks in your list.");
                    System.out.println(LINE);
                    index+=1;
                    break;

                case "event":
                    //Only accounted for /from before /to
                    String fromToString = inputString.split("/from ")[1];
                    String fromDate = fromToString.split("/to ")[0];
                    String toDate = fromToString.split("/to ")[1];
                    taskList.add(new Event(taskName,index,fromDate,toDate));
                    System.out.println(LINE);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  [E][ ] "+ taskName  + " (from: " + fromDate + "to: "+ toDate + ")");
                    System.out.println(" Now you have " + taskList.size() + " tasks in your list.");
                    System.out.println(LINE);
                    index+=1;
                    break;

                default:
                    if(words.length>1)
                        taskName = words[0] + " " + taskName;
                    taskList.add(new Task(taskName,index));
                    index += 1;
                    echo(taskName);
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
