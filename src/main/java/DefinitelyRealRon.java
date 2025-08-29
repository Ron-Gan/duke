import java.util.Scanner;

public class DefinitelyRealRon {
    
    private static final String LINE = "____________________________________________________________";

    public static void printWithLine(String inputString){
            System.out.println(LINE);
            System.out.println(inputString);
            System.out.println(LINE);
    }
     
    public static void echo(){
        Scanner in = new Scanner(System.in);
        String inputString;
        do{
            inputString = in.nextLine();
            printWithLine(" "+inputString);
        }
        while(!inputString.equals("bye"));
        in.close();
        return;
    }
    public static void main(String[] args) {
        String welcomeString = " Yo! I'm DefinitelyRealRon. Definitely Real. Definitely Ron.\n How may I help you today?";
        String byeString = " Seeya! Hope I was helpful. I'm Definitely Real!";
        printWithLine(welcomeString);
        echo();
        System.out.println(byeString);
        System.out.println(LINE);
    }
}
