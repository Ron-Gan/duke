import java.util.Scanner;
import classes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DefinitelyRealRon {
    
    private static final String LINE = "____________________________________________________________";
    public static List<Task> taskList = new ArrayList<Task>();
    public static Set<String> commandSet = Set.of(
        "list","mark", "unmark","todo","deadline","event");

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
            try{
                if(!commandSet.contains(firstWord))
                    throw new IllegalArgumentException("Unknown Command");       
                if(!firstWord.equals("list")&&words.length<=1)
                    throw new IndexOutOfBoundsException();
            }
            catch(IllegalArgumentException e){
                printWithLine(" Beep Boop. I don't understand " + inputString + " :(");
                inputString = in.nextLine();
                continue;
            }
            catch(IndexOutOfBoundsException e){
                printWithLine(" Beep Boop. Operation FAILED!\n Description for " + firstWord + " cannot be empty :(");
                inputString = in.nextLine();
                continue;
            }
            
            int targetTaskForStatusChange = -1;
            String taskName = "";
            for(int i=1; i<words.length-1;i+=1)
                taskName+= words[i] + " ";
            taskName += words[words.length-1];
            
            if(firstWord.equals("mark")||firstWord.equals("unmark")){
                    if(taskList.size()<=0){
                        printWithLine(" Beep Boop. Operation FAILED!\n Your list is empty :(");
                        inputString = in.nextLine();
                        continue;
                    }
                    try{
                        targetTaskForStatusChange = Integer.parseInt(words[1])-1;
                        taskList.get(targetTaskForStatusChange).getStatus();
                    }catch(IndexOutOfBoundsException e){
                            printWithLine(" Beep Boop. Operation FAILED!\n You can't " + firstWord 
                            + " task "+ (targetTaskForStatusChange+1)
                            + " as there is only " + taskList.size()+ " tasks in your list :(");
                            inputString = in.nextLine();
                            continue;
                    }catch(NumberFormatException e){
                            printWithLine(" Beep Boop. Operation FAILED!\n"
                            +" Bruh you gotta put input a valid number :(");
                            inputString = in.nextLine();
                            continue;
                    }

                } else{
                    try{
                        if(taskName.isBlank())
                            throw new IllegalArgumentException("Empty Todo Description");
                    }catch(IllegalArgumentException e){
                        printWithLine(" Beep Boop. Operation FAILED\n You did not describe the task :(");
                        inputString = in.nextLine();
                        continue;
                    }
                }
            
            switch (firstWord) {
                case "mark":
                    if(taskList.get(targetTaskForStatusChange).getStatus()){
                        printWithLine(" Task "+taskName+" was already marked.");
                        break;
                    }
                    taskList.get(targetTaskForStatusChange).setStatus(true);
                    System.out.println(LINE);
                    System.out.println(" You're so productive! I've marked this task as done:");
                    taskList.get(targetTaskForStatusChange).printTask();
                    System.out.println(LINE);
                    break;
                    
                case "unmark":
                    if(!taskList.get(targetTaskForStatusChange).getStatus()){
                        printWithLine(" Task "+taskName+" was already unmarked.");
                        break;
                    }
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
                    String deadline;
                    try{
                        deadline = inputString.split("/by ")[1];
                        if(deadline.isBlank())
                            throw new ArrayIndexOutOfBoundsException();
                    }catch(ArrayIndexOutOfBoundsException e){
                        printWithLine(" Beep Boop. OPERATION FAILED.\n"+
                            " Please input a valid /by date"
                            );
                        break;
                    }
                    
                    taskList.add(new Deadline(taskName,index,deadline));
                    System.out.println(LINE);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  [D][ ] "+ taskName  + " (by: " + deadline + ")");
                    System.out.println(" Now you have " + taskList.size() + " tasks in your list.");
                    System.out.println(LINE);
                    index+=1;
                    break;

                case "event":
                    String fromDate="", toDate="";
                    boolean fromExist = (inputString.contains("/from "));
                    boolean toExist = (inputString.contains("/to "));

                    if(!fromExist || !toExist){
                        printWithLine(" Beep Boop. OPERATION FAILED.\n"
                                + " Please input a valid "
                                + (!fromExist ? "/from" : "/to") + " date"
                        );
                        break;
                    }

                    if(inputString.indexOf("/from ")<inputString.indexOf("/to ")){
                        fromDate = inputString.substring(inputString.indexOf("/from ")+6,inputString.indexOf("/to ")-1);
                        toDate = inputString.substring(inputString.indexOf("/to ")+4);
                    }
                    else{
                        toDate=inputString.substring(inputString.indexOf("/to ")+4,inputString.indexOf("/from ")-1);
                        fromDate = inputString.substring(inputString.indexOf("/from ")+6);
                    }                   

                    if(fromDate.isBlank()||toDate.isBlank()){
                        printWithLine(" Beep Boop. OPERATION FAILED.\n"
                                + " Please input a valid "
                                + (fromDate.isBlank() ? "/from" : "/to") + " date"
                        );
                        break;
                    }
                    
                    taskList.add(new Event(taskName,index,fromDate,toDate));
                    System.out.println(LINE);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  [E][ ] "+ taskName  + " (from: " + fromDate + " to: "+ toDate + ")");
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
