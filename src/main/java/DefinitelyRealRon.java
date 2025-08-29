import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class DefinitelyRealRon {
    
    private static final String LINE = "____________________________________________________________";
    public static List<String> itemList = new ArrayList<String>();

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
            if(inputString.equals("list")){
                for(int i=0; i<itemList.size(); i++){
                    printListString = printListString + (i+1) + ". " + itemList.get(i) + "\n";
                }
                System.out.println(LINE);
                System.out.print(printListString);
                System.out.println(LINE);
                printListString="";
            }
            else{
                itemList.add(inputString);
                echo(inputString);
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
