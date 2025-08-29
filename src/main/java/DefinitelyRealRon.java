import java.util.Scanner;
import java.util.Vector;

public class DefinitelyRealRon {
    
    private static final String LINE = "____________________________________________________________";
    public static Vector<String> itemList = new Vector<String>();

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
        do{
            inputString = in.nextLine();
            if(inputString.equals("list")){

            }
            else{
                echo(inputString);
            }
        }
        while(!inputString.equals("bye"));
    }
    public static void main(String[] args) {
        String welcomeString = " Yo! I'm DefinitelyRealRon. Definitely Real. Definitely Ron.\n How may I help you today?";
        String byeString = " Seeya! Hope I was helpful. I'm Definitely Real!";
        printWithLine(welcomeString);
        readInput();
        System.out.println(byeString);
        System.out.println(LINE);
    }
}
